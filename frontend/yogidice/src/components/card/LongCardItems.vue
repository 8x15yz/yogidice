<template>
  <img class="long-img" :src='imgUrl' alt="">
  <div class="game-info">
    <div class="game-title text-subtitle-1">{{ oneGame.title_kr }}</div>
    <div class="chip-rating">
      <div class="text-subtitle-1 rating">★{{ oneGame.rating }}</div>
      <div class="game-chip-container">
        <div>{{ `${oneGame.minPlayers}~${oneGame.maxPlayers}인` }}</div>
        <div>{{ oneGame.playTimes }}</div>
        <div>{{ oneGame.playLevel }}</div>
      </div>
    </div>
  </div>
  <div class="bookmark-alert text-subtitle-1" v-show="showRegister">
    북마크가 등록되었습니다!<span class="material-icons">check_circle</span>
  </div>
  <span class="material-icons bookmark-icon" v-show="bookmarkBorder" @click="registerBookMark">bookmark_border</span>
  <span class="material-icons bookmark-icon bookmarked" v-show="bookmarkFilled" @click="registerBookMark">bookmark</span>
</template>

<script>
import { computed,toRefs,ref } from "vue"

export default {
  props: {
    game: Object
  },
  setup(props) {
    let oneGame = toRefs(props.game)

    let checkedMark = ref(false)

    let bookmarkBorder = ref(true)
    let bookmarkFilled = ref(false)

    let imgUrl = computed(() => `https://boardlife.co.kr/${oneGame.thumburl.value}`)

    let showRegister = ref(false)
    const registerBookMark = function () {
      if (bookmarkBorder.value === false) {
        bookmarkBorder.value = true
        bookmarkFilled.value = false
      } else {
        bookmarkBorder.value = false
        bookmarkFilled.value = true
        
        showRegister.value = true
        setTimeout(()=>showRegister.value=false,3000)        
      }      
    }

  return {
    oneGame,
    // key,
    // title_kr,
    imgUrl,
    checkedMark,
    // maxPlayers,
    // minPlayers,
    // playTimes,
    // playLevel,
    // rating,
    registerBookMark,
    bookmarkBorder,
    bookmarkFilled,
    showRegister
  }}


}
</script>

<style scoped>

.bookmark-alert{
  width: 92vw;
  height: 20vw;
  background-color: var(--color-bg-modal);
  color: white;
  position: absolute;
  text-align: center;
  line-height: 20vw;
}
.game-title {
  width: 80%;
  text-align:left;
}
.long-img {
  width: 20vw;
  height: 20vw;
}
.game-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  width: 68vw;
  gap: 1vh;
  padding: 4px 0 4px 2vw;
}
.chip-rating {
  display: flex;
  width: 100%;
  height: 4vh;
}
.rating {
  text-align: start;
  align-self: center;
}
.game-chip-container {
  margin: 0px;
  align-self:center
}

.bookmark-icon {
  position: absolute;
  top:-8px;
  right: 8px;
  font-size: 8vw;
}

</style>