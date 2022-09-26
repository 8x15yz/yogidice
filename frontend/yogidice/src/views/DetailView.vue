<template>
  <div>
    <detail-card-item :game='game'></detail-card-item>
    <div class="game-chemi">
      <div class="text-subtitle-1">현주님과 {{game.title_kr}}의 케미는 70점입니다 </div>
      <div class="progress">
        <div class="progress-value"></div>
      </div>
    </div>
    <div class="detail-menu-bar">
      <div id="game-introduce" class="text-subtitle-2 isactive"> 게임소개 </div>
      <div id="game-review" class="text-subtitle-2"> 사용자평가 </div>
      <div id="game-related" class="text-subtitle-2"> 연관게임 </div>
    </div>
    <div class="detail-page">
      <game-introduce></game-introduce>
      <game-review></game-review>
      <game-related></game-related>
    </div>
  </div>
</template>

<script>
import DetailCardItem from "@/components/card/DetailCardItem.vue"
import { onMounted } from '@vue/runtime-core'
import GameIntroduce from '@/components/detail/GameIntroduce.vue'
import GameReview from '@/components/detail/GameReview.vue'
import GameRelated from '@/components/detail/GameRelated.vue'
export default {
  components: {
    DetailCardItem,
    GameIntroduce,
    GameReview,
    GameRelated,
  },
  setup() {

    let detailMenus
    onMounted(()=>{
      detailMenus = document.querySelectorAll(".detail-menu-bar div")
      const detailPage = document.querySelector(".detail-page")
      const coverPage = document.querySelector("#wrap2")
      for (let detailMenu of detailMenus) {
        detailMenu.addEventListener("click", (e) => {
          for (let detailMenu of detailMenus) {
            if (detailMenu.getAttribute("id") === e.target.id) {
              detailMenu.classList.add("isactive")
              if (e.target.id === "game-introduce") {
                detailPage.style.transform = "translateX(0vw)"
                coverPage.style.overflowY = "scroll"
              } else if (e.target.id === "game-review") {
                detailPage.style.transform = "translateX(-100vw)"
                coverPage.style.overflowY = "visible"
              } else { 
                detailPage.style.transform = "translateX(-200vw)"
                coverPage.style.overflowY = "hidden"
              }
            } else {
              detailMenu.classList.remove("isactive")
            }
          }
        })
      }
    })
    let game ={'key': 1, 'title_kr': '쓰루 디 에이지스: 문명에 관한 새로운 이야기', 'thumburl': 'wys2/swf_upload/2022/02/24/1645643684643042_lg.jpg','rating': 4.5,
    'maxPlayers': 4,'minPlayers': 2,'playTimes' : 180, 'playLevel' : "매우 어려움"}

    return {
      game
    }
  }

}
</script>

<style>
.game-chemi {
  margin: 3vh 4vw 3vh 4vw;
  white-space: nowrap;
  overflow:hidden;
  text-overflow: ellipsis;
}
.progress {
  background: var(--color-grey-3);
  justify-content: flex-start;
  border-radius: 100px;
  align-items: center;
  position: relative;
  padding: 0 1vw;
  display: flex;
  height: 3vh;
  width: 90vw;
  margin: 1vh 0vh 1vh 0vh;
}

.progress-value {
  animation: load 3s normal forwards;
  border-radius: 100px;
  background: linear-gradient(to right, #FFEEEE 0%, #FF6E6E 100%);
  height: 2vh;
  /* width: 90vw; */
}

@keyframes load {
  0% { width: 0; }
  100% { width: 68%; }
}

.detail-menu-bar {
  display: flex;
  justify-content: space-evenly;
  padding: 0vw 4vw;
  border-bottom: 2px solid var(--color-grey-opacity-9)
}
.detail-menu-bar div {
  width: 28vw;
  height: 2vh;
  padding: 1.5vh 0vh 1.5vh 0vh;
  color: var(--color-grey-opacity-9);
  text-align: center;
}
.detail-menu-bar div.isactive {
  color: black;
  font-weight: bold;
  border-top: 3px solid var(--color-point)
}
.detail-page {
  width: 300vw;
  display: flex;
  flex-wrap: nowrap;
  overflow-x: hidden;
  transition: all 0.5s;

  
}
</style>