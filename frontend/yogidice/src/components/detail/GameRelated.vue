<template>
  <div class="related-game-container">
    <main-card-list></main-card-list>
  </div>
</template>

<script>
import MainCardList from '../card/MainCardList.vue'
export default {
  components: {
    MainCardList
  }

}
</script>

<style>
.related-game-container {
  margin: 2vh 2vw;
  overflow: scroll;
}
</style>