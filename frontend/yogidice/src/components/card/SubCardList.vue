<template>
  <div id="main-game-card-container">
    <div
      v-for="game in subGameLists"
      :key="game.gameId"
      id="main-card"
      @move-game-detail="showDetail(game)"
      @click="showDetail(game)"
    >
      <main-card-items :game="game"> </main-card-items>
    </div>
  </div>
</template>

<script>
import MainCardItems from "@/components/card/MainCardItems.vue";
import { onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useStore } from "vuex";

export default {
  components: {
    MainCardItems,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const route = useRoute();

    const showDetail = function (n) {
      router.push({
        name: "GameDetail",
        query: { gameId: n.gameId, title: n.title_kr },
      });
    };
    let subGameLists = computed(() => store.state.games.subGames);
    let gameId = route.query.gameId;

    onMounted(() => {
      store.dispatch("games/resetSubGames");
      store.dispatch("games/changeSubGames", gameId);
    });

    return {
      subGameLists,
      showDetail,
    };
  },
};
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
  width: 56vw;
  box-shadow: var(--shadow-card);
  background-color: var(--color-white);
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
</style>
