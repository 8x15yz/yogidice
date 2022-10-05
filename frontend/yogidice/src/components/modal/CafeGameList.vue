<template>
  <div class="cafe-games-box">
    <div @click="closeCafeGame" class="close-button"><span class="material-icons-outlined">close</span></div>
    <div class="cafe-games-title text-headeline-6">{{ cafeName }}의 보유 게임</div>
    <hr>
    <div class="search-box">
      <search-bar></search-bar>
    </div>
    <br>
    <div class="cafe-games-count text-body-1">총 개의 게임</div>
    <div>
      <small-card-list></small-card-list>
    </div>
  </div>
</template>

<script>
import SearchBar from '../SearchBar.vue'
import SmallCardList from '@/components/card/SmallCardList.vue'
import { toRef } from '@vue/reactivity'

export default {
  components: { 
    SearchBar,
    SmallCardList
  },
  props: {
    name: String
  },
  setup(props,{emit}){
    let cafeName = toRef(props).name
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
  width: 94vw;
  height: 80vh;
  z-index: 999;
  background-color: var(--color-bg-modal);
  position: absolute;
  top: 12vh;
  left: 3vw;
}
.search-box{
  width: 94vw;
  padding: 2vh 2vw;
}
.cafe-games-title{
  color:white
}
.cafe-games-count{
  color: white
}

</style>