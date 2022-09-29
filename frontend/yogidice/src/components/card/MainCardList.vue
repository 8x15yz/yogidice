<template>
  <div id="main-game-card-container">
    <div
      v-for="game in gameLists"
      :key="game.gameId"
      id="main-card"
      @click="showDetail(game)"
    >
      <main-card-items
      :game="game">
      </main-card-items>
    </div>
    <div class="card-more">
      <span class="material-icons">arrow_forward_ios</span>
      <div class="text-subtitle-2">더보기</div>
    </div>
  </div>
</template>

<script>
import MainCardItems from "@/components/card/MainCardItems.vue"
import { onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from "vuex"

export default {
  components: {
    MainCardItems
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const showDetail = function(n) {
      router.push({name:"GameDetail", query:{"gameId":n.gameId, "title":n.title_kr}})
    }
    let gameLists = computed(()=>store.state.games.mainGames)
    let gameType = computed(()=>store.state.games.presentType)

    onMounted(()=>{
      const moreBtn = document.querySelector(".card-more")
      moreBtn.addEventListener("click", () =>
      router.push({name:"MoreList", params:{"type":gameType.value}})
    )})
    
  return {
    gameLists,
    showDetail
  }
}


}
</script>

<style>
#main-game-card-container {
  display: flex;
  justify-content: flex-start;
  align-content: flex-start;
  flex-wrap: nowrap;
  overflow: scroll;
  gap: 4vw;
  height: auto;
  padding-bottom: 10px;
  margin-bottom: 4vh;
  align-self: flex-start;
}

#main-card {
  width: 52vw;
  box-shadow: var(--shadow-card);
  background-color: var(--color-white);
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}

.card-more {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 30px;
}
.card-more>div {
  width: 20vw;
  margin-top: 10px;
  text-align: center;
}
</style>