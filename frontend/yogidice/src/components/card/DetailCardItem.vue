<template>
  <div class="detail-card-container" :style="backImgUrl">
    <div class="detail-game-info">
      <div class="chip-rating">
        <div class="detail-game-chip-container">
          <div>'2인~3인'</div>
          <div>{{gameInfo.playTimes}}</div>
          <div>{{gameInfo.playLevel}}</div>
        </div>
        <div class="text-subtitle-1 rating">★{{gameInfo.rating}}</div>
      </div>
      <div class="detail-game-title text-headline-6">{{ gameInfo.title_kr }}</div>
      <hr style="width:100%">
      <button class="button-long-blue text-button">게임하러 가기</button>
    </div>
  </div>
</template>

<script>
import { computed,toRefs } from '@vue/runtime-core'
export default {
  props: {
    game:Object
  },
  setup(props) {
    let gameInfo = toRefs(props.game)
    // router 동적 변수로 게임 번호 가져오고 그거로 axios 요청해서 데이터 받아오기
    let backImgUrl = computed(()=>{
      return {
        "background-image": `url(https://boardlife.co.kr/${gameInfo.thumburl.value})`
      }
    })
    
    return {
      gameInfo,
      backImgUrl
    }
  }

}
</script>

<style scoped>
button {
  height: 26px;
}
.rating {
  align-self: center;
  color: yellow;
}
.chip-rating {
  width: 100%;
  gap: 2vw;

}
.detail-game-title {
  white-space: nowrap;
  overflow:hidden;
  text-overflow: ellipsis;
}
.detail-card-container {
  width: 96vw;
  margin-top: 5vw;
  margin-left: 2vw;
  margin-right: 2vw;
  /* border-radius: 2px; */
  /* object-fit: fill; */
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

</style>