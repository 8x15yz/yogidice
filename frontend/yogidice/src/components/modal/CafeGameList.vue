<template>
  <div class="cafe-games-box">
    <div @click="closeCafeGame" class="close-button"><span class="material-icons-outlined">close</span></div>
    <div class="cafe-games-title text-headline-6">{{ cafeName }}의 보유 게임</div>
    <hr class="line-cafe-game">
    <div class="search-box">
      <search-bar></search-bar>
    </div>
    <br>
    <div class="cafe-games-count text-body-1">총 {{ count }}개의 게임</div>
    <div>
      <small-card-list></small-card-list>
    </div>
  </div>
</template>

<script>
import SearchBar from '../SearchBar.vue'
import SmallCardList from '@/components/card/SmallCardList.vue'
import { toRefs } from '@vue/reactivity'
import { useStore } from "vuex"
import { computed } from '@vue/runtime-core'

export default {
  components: { 
    SearchBar,
    SmallCardList
  },
  props: {
    name: String
  },
  setup(props,{emit}){
    const store = useStore()
    let cafeName = toRefs(props).name
    let count = computed(()=>store.getters.games.getCountSmallGames)
    const closeCafeGame = function () {
      emit("closeCafeModal")
    }
    return {
      cafeName,
      closeCafeGame,
      count
    }
  }

}
</script>

<style>
.cafe-games-box {
  display: flex;
  flex-direction: column;
  width: 94vw;
  height: 90vh;
  z-index: 999;
  background-color: var(--color-bg-modal);
  position: absolute;
  top: 5vh;
  left: 3vw;
  border-radius: 4px;
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
.line-cafe-game{
  width:88vw; 
  background-color:white;
  margin: 2vh 0;
}

</style>