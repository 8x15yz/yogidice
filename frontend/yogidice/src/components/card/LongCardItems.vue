<template>
  <img class="long-img" :src='thumbUrl' alt="">
  <div class="game-info">
    <div class="game-title text-subtitle-1">{{ titleKr }}</div>
    <div class="chip-rating">
      <div class="text-subtitle-1 rating">★{{ ratingUser }}</div>
      <div class="game-chip-container">
        <div>{{ `${minPlayers}~${maxPlayers}인` }}</div>
        <div>{{ playingTime }}</div>
        <div>{{ difficulty }}</div>
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
import { toRefs,ref } from "vue"

export default {
  props: {
    lg: Object
  },
  setup(props) {
  
    let checkedMark = ref(false)
  
    let { titleKr } = toRefs(props.lg)
    let { thumbUrl } = toRefs(props.lg)
    let { id } = toRefs(props.lg)
    let { maxPlayers } = toRefs(props.lg)
    let { minPlayers } = toRefs(props.lg)
    let { playingTime } = toRefs(props.lg)
    let { ratingUser } = toRefs(props.lg)
    let { difficulty } = toRefs(props.lg)
  

    let bookmarkBorder = ref(true)
    let bookmarkFilled = ref(false)


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
    titleKr,
    id,
    thumbUrl,
    checkedMark,
    registerBookMark,
    bookmarkBorder,
    bookmarkFilled,
    showRegister,
    maxPlayers,
    minPlayers,
    playingTime,
    ratingUser,
    difficulty
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