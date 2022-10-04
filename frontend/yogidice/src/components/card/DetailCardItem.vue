<template>
  <div class="detail-card-container" :style="backImgUrl">
    <div class="detail-game-info">
      <div class="chip-rating">
        <div class="detail-game-chip-container">
          <div>{{`${gameInfo.minPlayers} ~ ${gameInfo.maxPlayers}인`}}</div>
          <div>{{`${gameInfo.playingTime}분 소요`}}</div>
          <div>{{ level }}</div>
        </div>
        <div class="text-subtitle-1 rating">★{{ rate }}</div>
      </div>
      <div class="detail-game-title text-headline-6">{{ gameInfo.titleKr }}</div>
      <hr style="width:100%">
      <button class="button-long-blue text-button">게임하러 가기</button>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from '@vue/runtime-core'
import { useStore } from 'vuex'
export default {

  setup() {
    const store = useStore()
    let rate = ref(0)
    let gameInfo = computed(()=>store.state.games.detail)
    let backImgUrl = computed(()=>{
      return {
        "background-image": `url(${gameInfo.value.thumbUrl})`
      }
    })
    onMounted(() => {
      console.log(rate)
      rate.value = computed(()=>Number(gameInfo.value.ratingUser.toFixed(2)))
    })
    
    let difficulty = gameInfo.value.difficulty
    let level = computed(()=>{
      if (difficulty <= 1) {
        return "매우 쉬움"
      } 
      else if (difficulty <= 2) {
        return "쉬움"
      } 
      else if (difficulty <= 3) {
        return "보통"
      } 
      else if (difficulty <= 4) {
        return "어려움"
      } 
      else {
        return "매우 어려움"
      } 
    })
    
    return {
      gameInfo,
      backImgUrl,
      level,
      rate
    }
  }

}
</script>

<style scoped>
button {
  width:90vw;
  height: 26px;
  align-self: center;
}
.rating {
  font-size: 18px;
  line-height: normal;
  align-self: flex-start;
  color: yellow;
  text-align: left;
}
.chip-rating {
  width: 100%;
  gap: 2vw;
  display: flex;
  margin-bottom: 0vh;
}

.detail-game-title {
  white-space: nowrap;
  overflow:hidden;
  text-overflow: ellipsis;
  text-align: left;
}
.detail-card-container {
  width: 96vw;
  padding-top: 5vw;
  margin-left: 2vw;
  margin-right: 2vw;

  background-size: 50vw 50vw;
  background-repeat: no-repeat;
  background-position: center;
}
.detail-game-info {
  background-color: var(--color-bg-modal);
  width: 90vw;
  height: 26vh;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 3vw;
  color: white;
  box-shadow: var(--shadow-card);
}
.detail-game-chip-container {
  display: flex;
  justify-content: flex-start;
  gap: 2vw;
  align-self: flex-start;

  font-family: 'Pretendard';
  font-style: normal;
  font-weight: 500;
}
.detail-game-chip-container > div {
  border-radius: 40px;
  padding: 4px 8px 4px 8px; 
  font-size: 12px;
}
.detail-game-chip-container div:nth-child(1) {
  background-color: var(--color-chip-letter-blue);
}
.detail-game-chip-container div:nth-child(2) {
  background-color: var(--color-chip-letter-beige);
}
.detail-game-chip-container div:nth-child(3) {
  background-color: var(--color-chip-letter-purple);
}
hr {
  margin: 2vh 0vw;
}

</style>