<template>
  <div class="search-bar-black-bg">
    <search-bar></search-bar>
  </div>
  <div class="search-res-bg" v-show="showSearchResult">
    <long-card-list></long-card-list>
  </div>
  <div id="wrap2-back">
    <div class="text-headline-6 main-message">유저님에게 맞는 <br> 보드게임을 추천해드려요</div>
    <!-- 추천 게임 타입 선택 -->
    <div class="cardlist-type">
      <div @click="changeActive" class="text-body-1 isactive">추천</div>
      <div @click="changeActive" class="text-body-1">TOP10</div>
      <div @click="changeActive" class="text-body-1">최신게임</div>
      <div @click="changeActive" class="text-body-1">리뷰많은순</div>
    </div>
    <!-- 추천 게임 목록 -->
    <main-card-list></main-card-list>
    <!-- 부가기능 버튼 3개 -->
    <div class="text-subtitle-1 main-message">요기 다이스의 부가 기능을 살펴보세요!</div>
    <div class="add-ons">
      <div class="left-one">
        <div class="left-title text-subtitle-1">내 취향저격<br> 게임</div>
        <div class="left-content text-body-1">지금 내가 할 수 있는<br> 최고의 게임 찾기!</div>
        <img class="left-img" src="../static/Checklist.png" width="100%" alt="">
      </div>
      <div class="right-two">
        <div class="right-top">
          <div class="left-title text-subtitle-1">아이템 창고</div>
          <img src="../static/Slot_machine.png" width="100%" alt="">
        </div>
        <div class="right-bottom">
          <div class="left-title text-subtitle-1">근처 보드카페 찾기</div>
          <img src="../static/Map.png" width="100%" alt="">
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { onMounted,getCurrentInstance,ref,computed,watch } from '@vue/runtime-core'
import MainCardList from '@/components/card/MainCardList.vue'
import LongCardList from '@/components/card/LongCardList.vue'
import SearchBar from '@/components/SearchBar.vue'

export default {
  components: {
    MainCardList,
    LongCardList,
    SearchBar
  },
  setup() {
    // mitt 쓰기
    const internalInstance = getCurrentInstance()
    const emitter = internalInstance.appContext.config.globalProperties.emitter

    let showSearchResult = ref(false)
    let inputValue = ref("")
    // 데이터 받기 (SearchBar로부터 받음)
    emitter.on('inputValue', (data) => { 
      inputValue.value = data
    })

    watch(() => inputValue.value, (newValue) => {
      if (newValue !== '') {
        showSearchResult.value = true
      } else {
        showSearchResult.value = false
      }
    })

    // 전체게임
    let games = [{'key': 1, 'title_kr': '쓰루 디 에이지스: 문명에 관한 새로운 이야기', 'thumburl': 'wys2/swf_upload/2022/02/24/1645643684643042_lg.jpg'}, {'key': 2, 'title_kr': '가이아 프로젝트', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615274670-490381.jpg'}, {'key': 3, 'title_kr': '황혼의 투쟁', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344501-765004.jpg'}, {'key': 4, 'title_kr': '푸에르토 리코', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640328882-556458.jpg'}, {'key': 5, 'title_kr': '팬데믹 레거시: 시즌 1', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818479-857227.png'}, {'key': 6, 'title_kr': '7 원더스: 대결', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615276187-302156.png'}, {'key': 7, 'title_kr': '아그리콜라', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329018-317029.png'}, {'key': 8, 'title_kr': '버건디의 성', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818831-183512.jpg'}, {'key': 9, 'title_kr': '테라 미스티카 빅 박스', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329073-893153.jpg'}, {'key': 10, 'title_kr': '브라스: 버밍엄', 'thumburl': 'wys2/swf_upload/2022/02/13/1644738474034685_lg.jpg'}, {'key': 11, 'title_kr': '안드로이드: 넷러너', 'thumburl': 'wys2/swf_upload/2022/04/01/1648822880778532_lg.jpg'}, {'key': 12, 'title_kr': '메이지 나이트: 얼티밋 에디션', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329191-62264.jpg'}, {'key': 13, 'title_kr': '촐킨: 마야의 달력', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612346336-782738.jpg'}, {'key': 14, 'title_kr': '도미니언', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344032-986124.png'}, {'key': 15, 'title_kr': '아컴 호러: 카드 게임', 'thumburl': 'wys2/swf_upload/2022/07/18/1658093520711797_lg.jpg'}, {'key': 16, 'title_kr': '버라지', 'thumburl': 'wys2/swf_upload/2022/04/26/1650902133768524_lg.jpg'}, {'key': 17, 'title_kr': '더 갤러리스트', 'thumburl': 'wys2/swf_upload/2022/01/16/1642264092333367_lg.jpg'}, {'key': 18, 'title_kr': '르아브르', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329356-468430.png'}, {'key': 19, 'title_kr': '브래스', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329424-901592.jpg'}, {'key': 20, 'title_kr': '테라 미스티카', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329478-974134.jpg'}, {'key': 21, 'title_kr': '오딘을 위하여', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329574-996804.png'}, {'key': 22, 'title_kr': '트라야누스', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329639-798299.png'}, {'key': 23, 'title_kr': '스플렌더', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329799-345514.png'}, {'key': 24, 'title_kr': '레지스탕스: 아발론', 'thumburl': 'data/boardgame_strategy/2022/01/02/1641052726-191444.png'}, {'key': 25, 'title_kr': '티츄', 'thumburl': 'data/boardgame_strategy/2022/01/02/1641052786-66356.jpg'}]
    // 받은 검색결과로 필터링
    let filteredGames = computed(()=>games.filter(eachGame => eachGame.title_kr.includes(inputValue.value)))

    let cardListTypes
    onMounted(()=>{
      cardListTypes = document.querySelectorAll('.cardlist-type div')
    })
    const searchMain = (e) => {
      console.log(e.target.value)
    }
    const changeActive = (e) => {
      for (let cardListType of cardListTypes) {
        if (cardListType.classList.contains("isactive")) {
          cardListType.classList.remove("isactive")
        }
      e.target.classList.add("isactive")
      }
    }
    return {
      changeActive,
      searchMain,
      filteredGames,
      showSearchResult,
    }
  }
}
</script>

<style>
#wrap2-back {
  width: 90vw;
  height: 90vh;
  padding: 4vh 5vw 1vh 5vw;
  background-color: var(--color-bg-base);
  overflow: scroll;
}
.main-message {
  text-align: left;
}
.cardlist-type {
  display: flex;
  gap: 2vw;
  margin: 4vh 0vh 2vh 0vh;
}
.cardlist-type .isactive {
  font-weight: bold;
}
.add-ons{
  display: flex;
  justify-content: space-between;
  margin-top: 2vh;
  margin-bottom: 10vh;
  gap:4vw;
}
.left-one{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  width:40vw;
  padding: 2vh 0 0 2vh;
  background-color: white;
  border-radius: 10px;
  box-shadow: var(--shadow-card);
}
.left-title{
  font-family: 'Pretendard';
  font-style: normal;
  font-weight: bolder;
  font-size: 16px;
  line-height: 19px;
  text-align: left;
}
.left-content {
  font-family: 'Pretendard';
  font-style: normal;
  font-weight: 600;
  font-size: 10px;
  line-height: 12px;
  text-align: left;
}
.left-img {
  align-self: flex-end;
}
.right-two {
  display: flex;
  flex-direction: column;
  gap: 4vw;
}
.right-top{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 40vw;
  padding: 2vh 0 0 2vh;
  background-color: white;
  border-radius: 10px;
  box-shadow: var(--shadow-card);
}
.right-bottom{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 40vw;
  padding: 2vh 0 0 2vh;
  background-color: white;
  border-radius: 10px;
  box-shadow: var(--shadow-card);
}
.search-bar-black-bg {
  position: sticky;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: black;
  height: 8vh;
  width: 100vw;
}
.search-res-bg {
  position: absolute;
  width: 100vw;
  height: 90vh;
  background-color: var(--color-bg-modal)
}

</style>