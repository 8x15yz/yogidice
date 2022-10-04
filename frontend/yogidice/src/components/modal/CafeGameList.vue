<template>
  <div class="cafe-games-box">
    <div>의 보유 게임</div>
    <hr>
    <search-bar></search-bar>
    <div>총 개의 게임</div>
    <div>
      <small-card-list></small-card-list>
    </div>
  </div>
</template>

<script>
// import SearchBar from '../SearchBar.vue'
// import SmallCardLists from '@/components/card/SmallCardList.vue'

export default {
  components: { 
    // SearchBar,
    // SmallCardLists
  },
  setup(){
    let cafeName = ''

    return {
      cafeName
    }
  }

}
</script>

<style>
.cafe-games-box {
  width: 90vw;
  height: 90vw;
  z-index: 1;
  background-color: var(--color-bg-base);
}
</style>