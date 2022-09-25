<template>
  <img class="long-img" :src='imgUrl' alt="">
  <div class="game-info">
    <div class="game-title text-subtitle-1">{{ title_kr }}</div>
    <div class="chip-rating">
      <div class="text-subtitle-1 rating">★{{ rating }}</div>
      <div class="game-chip-container">
        <div>{{ `${minPlayers}~${maxPlayers}인` }}</div>
        <div>{{ playTimes }}</div>
        <div>{{ playLevel }}</div>
      </div>
    </div>
  </div>
  <div class="bookmark-alert text-subtitle-1" v-show="test">
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

    let { key } = toRefs(props.game)
    let { title_kr } = toRefs(props.game)
    let { thumburl } = toRefs(props.game)
    let { maxPlayers } = toRefs(props.game)
    let { minPlayers } = toRefs(props.game)
    let { playTimes } = toRefs(props.game)
    let { playLevel } = toRefs(props.game)
    let { rating } = toRefs(props.game)
    let checkedMark = ref(false)

    let bookmarkBorder = ref(true)
    let bookmarkFilled = ref(false)

    let imgUrl = computed(() => `https://boardlife.co.kr/${thumburl.value}`)

    let test = ref(false)
    const registerBookMark = function () {
      if (bookmarkBorder.value === false) {
        bookmarkBorder.value = true
        bookmarkFilled.value = false
      } else {
        bookmarkBorder.value = false
        bookmarkFilled.value = true
        
        test.value = true
        setTimeout(()=>test.value=false,3000)        
      }      
    }

  return {
    key,
    title_kr,
    imgUrl,
    checkedMark,
    maxPlayers,
    minPlayers,
    playTimes,
    playLevel,
    rating,
    registerBookMark,
    bookmarkBorder,
    bookmarkFilled,
    test
  }}


}
</script>

<style>

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