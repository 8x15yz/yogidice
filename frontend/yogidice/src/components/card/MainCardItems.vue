<template>
  <img class="main-card-img" @click="moveGameDetail" :src='imgUrl' alt="">
  <div class="title-rating">
    <div class="game-title text-subtitle-1">{{ title_kr }}</div>
    <div class="text-subtitle-1 rating">★{{ rating }}</div>
  </div>
  <div class="game-chip-container">
    <div>{{ `${minPlayers}~${maxPlayers}인` }}</div>
    <div>{{ playTimes }}분 소요</div>
    <div>{{ playLevel }}</div>
  </div>
</template>

<script>
import { computed,toRefs,reactive,ref } from "vue"

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

    let imgUrl = computed(() => `https://boardlife.co.kr/${thumburl.value}`)
    const userSelection = reactive([])
    

  return {
    key,
    title_kr,
    imgUrl,
    maxPlayers,
    minPlayers,
    playTimes,
    playLevel,
    userSelection,
    checkedMark,
    rating
    }}


}
</script>

<style scoped>
.main-card-img {
  width: 52vw;
  height: 52vw;
  object-fit: fill;
  border-bottom: 1px solid black;
  border-radius: 4px 4px 0 0;
}
.title-rating {
  display: flex;
  justify-content: space-between;
  width: 90%;
  margin: 2vw 2vw 0vw 2vw;
  align-items: flex-start;
}
.game-title{ 
  white-space: nowrap;
  overflow:hidden;
  text-overflow: ellipsis;
}
.rating {
  color: var(--color-mint);
  width: 30%;
}
.game-chip-container {
  display: flex;
  justify-content: flex-start;
  margin: 2vw 2vw 2vw 2vw;
  gap: 1vw;
  align-self: flex-start;

  font-family: 'Pretendard';
  font-style: normal;
  font-weight: 500;
}
.game-chip-container > div {
  border-radius: 40px;
  padding: 2px 8px 2px 8px; 
  font-size: 8px;
  font-weight: bold;
}
.game-chip-container div:nth-child(1) {
  background-color: var(--color-chip-bg-blue);
  color: var(--color-chip-letter-blue);
}
.game-chip-container div:nth-child(2) {
  background-color: var(--color-chip-bg-beige);
  color: var(--color-chip-letter-beige);
}
.game-chip-container div:nth-child(3) {
  background-color: var(--color-chip-bg-purple);
  color: var(--color-chip-letter-purple);
}

</style>