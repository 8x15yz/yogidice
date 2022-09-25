<template>
  <div id="more-game-card-container">
    <div
      v-for="game in games"
      :key="game.key"
      id="more-card" 
    >
      <div class="border_bottom">
        <long-card-items
        :game="game"></long-card-items>
      </div>
    </div>
  </div>
</template>

<script>
import LongCardItems from "@/components/card/LongCardItems.vue"

export default {
  components: {
    LongCardItems
  },
  setup() {

    // 나중엔 store에서 받아올듯?
    let games = [{'key': 1, 'title_kr': '쓰루 디 에이지스: 문명에 관한 새로운 이야기', 'thumburl': 'wys2/swf_upload/2022/02/24/1645643684643042_lg.jpg','rating': 4.5,
    'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180, 'playLevel' : "매우 어려움"}, {'key': 2, 'title_kr': '가이아 프로젝트', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615274670-490381.jpg','rating': 4.5,'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180,'playLevel' : "매우 어려움"}, {'key': 3, 'title_kr': '황혼의 투쟁', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344501-765004.jpg','rating': 4.5,'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180,'playLevel' : "매우 어려움"}, {'key': 4, 'title_kr': '푸에르토 리코', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640328882-556458.jpg'}, {'key': 5, 'title_kr': '팬데믹 레거시: 시즌 1', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818479-857227.png'}]
    // 받은 검색결과로 필터링
    return {
      games,
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
  height: 80vh;
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