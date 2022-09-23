<template>
  <div id="main-game-card-container">
    <div
      v-for="game in games"
      :key="game.key"
      id="main-card" 
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
import MainCardItems from "@/components/MainCardItems.vue"

export default {
  components: {
    MainCardItems
  },
  setup() {
    // 나중엔 store에서 받아올듯?
    let games = [{'key': 1, 'title_kr': '쓰루 디 에이지스: 문명에 관한 새로운 이야기', 'thumburl': 'wys2/swf_upload/2022/02/24/1645643684643042_lg.jpg','rating': 4.5,
    'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180, 'playLevel' : "매우 어려움"}, {'key': 2, 'title_kr': '가이아 프로젝트', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615274670-490381.jpg','rating': 4.5,'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180,'playLevel' : "매우 어려움"}, {'key': 3, 'title_kr': '황혼의 투쟁', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344501-765004.jpg','rating': 4.5,'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180,'playLevel' : "매우 어려움"}, {'key': 4, 'title_kr': '푸에르토 리코', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640328882-556458.jpg'}, {'key': 5, 'title_kr': '팬데믹 레거시: 시즌 1', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818479-857227.png'}]
    return {
      games,
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