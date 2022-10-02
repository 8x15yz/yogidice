<template>
  <div id="more-game-card-container">
    <div
      v-for="lg in games"
      :key="lg.id"
      id="more-card" 
      @click="showDetail(lg)"
    >
      <div class="border_bottom">
        <long-card-items
        :lg="lg"></long-card-items>
      </div>
    </div>
  </div>
</template>

<script>
import LongCardItems from "@/components/card/LongCardItems.vue"
import { useRouter } from "vue-router"
import { useStore } from "vuex"
import { computed } from "vue"

export default {
  components: {
    LongCardItems
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const showDetail = function(game) {
      router.push({name:"GameDetail", query:{"gameId":game.id, "title":game.titleKr}})
    }
    // 나중엔 store에서 받아올듯?
    let games = computed(()=>store.state.games.longGames)
    return {
      games,
      showDetail
     }
  }


}
</script>

<style>
.border_bottom {
  display: flex;
  width: 100vw;
  padding: 2vw 4vw 2vw 4vw;
  border-bottom: 2px solid var(--color-grey-3);
}
#more-game-card-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;
  flex-wrap: nowrap;
  overflow: scroll;
  width: 100vw;
  overflow: hidden;
  background-color: white;
}

#more-card {
  position: relative;
  display: flex;
  width: 100vw;
  /* border-bottom: 1px black solid; */
  background-color: white;
  overflow: hidden;
}

</style>