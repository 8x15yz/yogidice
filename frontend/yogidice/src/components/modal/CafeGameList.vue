<template>
  <div class="cafe-games-box">
    <div @click="closeCafeGame" class="close-button"><span class="material-icons-outlined">close</span></div>
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
import SearchBar from '../SearchBar.vue'
import SmallCardList from '@/components/card/SmallCardList.vue'

export default {
  components: { 
    SearchBar,
    SmallCardList
  },
  setup(props,{emit}){
    let cafeName = ''
    const closeCafeGame = function () {
      emit("closeCafeModal")
    }
    return {
      cafeName,
      closeCafeGame
    }
  }

}
</script>

<style>
.cafe-games-box {
  display: flex;
  flex-direction: column;
  width: 90vw;
  height: 90vw;
  z-index: 999;
  background-color: var(--color-point);
  position: absolute;
}
</style>