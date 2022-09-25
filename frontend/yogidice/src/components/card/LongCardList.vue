<template>
  <div id="long-game-card-container">
    <div class="searchMsg text-subtitle-1" v-show="showFailMsg">찾으시는 게임이 없습니다😥</div>
    <div
      v-for="game in filteredGames"
      :key="game.key"
      id="long-card" 
    >
      <long-card-items
      :game="game"></long-card-items>
    </div>
  </div>
</template>

<script>
import { ref,getCurrentInstance,computed } from 'vue'
import LongCardItems from "@/components/card/LongCardItems.vue"

export default {
  components: {
    LongCardItems
  },
  setup() {
    // mitt 쓰기
    const internalInstance = getCurrentInstance()
    const emitter = internalInstance.appContext.config.globalProperties.emitter

    let inputValue = ref("")
    // 데이터 받기 (SearchBar로부터 받음)
    emitter.on('inputValue', (data) => {
      inputValue.value = data
      searchFailCheck()      
  })

    // 나중엔 store에서 받아올듯?
    let games = [{'key': 1, 'title_kr': '쓰루 디 에이지스: 문명에 관한 새로운 이야기', 'thumburl': 'wys2/swf_upload/2022/02/24/1645643684643042_lg.jpg','rating': 4.5,
    'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180, 'playLevel' : "매우 어려움"}, {'key': 2, 'title_kr': '가이아 프로젝트', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615274670-490381.jpg','rating': 4.5,'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180,'playLevel' : "매우 어려움"}, {'key': 3, 'title_kr': '황혼의 투쟁', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344501-765004.jpg','rating': 4.5,'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180,'playLevel' : "매우 어려움"}, {'key': 4, 'title_kr': '푸에르토 리코', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640328882-556458.jpg'}, {'key': 5, 'title_kr': '팬데믹 레거시: 시즌 1', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818479-857227.png'}]
    // 받은 검색결과로 필터링
    let filteredGames = computed(()=>games.filter(eachGame => eachGame.title_kr.includes(inputValue.value)))
    let showFailMsg = ref(false)
    let searchFailCheck = function () {
      if (filteredGames.value.length===0) {
        showFailMsg.value = true
      } else { showFailMsg.value = false }
    }

    
    return {
      games,
      inputValue,
      filteredGames,
      showFailMsg,
      searchFailCheck
    }
  }


}
</script>

<style>
.searchMsg {
  color: white;
  text-align: center;
  width: 90vw;
  margin-top: 20px;
  font-size: 20px;
}
#long-game-card-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;
  flex-wrap: nowrap;
  overflow: scroll;
  gap: 2vw;
  width: 90vw;
  height: 80vh;
  padding: 5vw;
  overflow: scroll;
}

#long-card {
  position: relative;
  display: flex;
  width: 90vw;
  height: 20vw;
  box-shadow: var(--shadow-card);
  border-radius: 4px;
  background-color: white;
  overflow: hidden;
}

</style>