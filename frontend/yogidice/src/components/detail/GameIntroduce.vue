<template>
  <div class="introduce-box">
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6">게임 유형</div>
      <ber-chart
      :lengamecategory="lengamecategory" >
      </ber-chart>
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6" style="margin-bottom: 10px;"> 게임 테마 </div>
      <span v-for="data in gametheme" :key="data.key">
        <span class="game-description">{{data.categoryName}}</span>
      </span>
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6"> 게임 방식 </div>
      <div v-for="data in gamemec" :key="data.key">
        <div class="play-type text-body-2" @click="describeType">{{data.mechanismName}}<span class="material-icons-outlined" style="margin-left: 5px; font-size: 17px;">info</span> </div>
      </div>
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6"> 관련 영상 </div>
      <!-- <iframe width="560" height="315" src="https://youtu.be/NLcdpBr3RgM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe> -->
      <!-- <div>{{youtubeUrl}}</div> -->
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6"> 해당 게임 보유 매장 </div>
    </div>
  </div>
  <modal-dialog v-show="showModal" :contents='contents'></modal-dialog>
</template>

<script>
import BerChart from '../BerChart.vue'
import ModalDialog from '@/components/modal/ModalDialog.vue'
import { useStore } from 'vuex'
import { useRoute } from "vue-router"
import { computed, reactive } from 'vue'

export default {
  components: { 
    BerChart,
    ModalDialog
  },
  setup() {
  const route = useRoute()
  const store = useStore()
  let showModal = computed(()=>store.state.modal.showModal)
  let contents = reactive({
    'modalType':'',
    'nextPage': '',
    'header':'',
    'body':'',
    'footer1': '',
    'footer2': '',
  })
  const describeType = function (event) {
    contents.modalType = 'onlyContent'
    contents.header = event.target.innerText
    contents.body = "게임 타입에 대한 설명 주룩주룩"
    store.commit("changeModal") 
  }
  let gameId = route.query.gameId
  store.dispatch("gamedetail/getLengames", gameId)
  store.dispatch("games/getDetails", gameId)
  let lengamecategory = computed(()=>store.state.gamedetail.lengamecategory)
  let detail = computed(()=>store.state.games.detail)

  // let yotu = detail.value.youtubeUrl.slice(17)
  // console.log('https://www.youtube.com/embed/' + yotu)

  let gametheme = detail.value.categoryGroupResponses
  let gamemec = detail.value.mechanismGroupResponses

  
  
  return {
    showModal,
    contents,
    describeType,
    lengamecategory,
    gametheme,
    gamemec,
    // yotu
  }
  
}

}
</script>

<style>
.play-type {
  height: 20px;
  font-size: 17px;
  margin-top: 10px;
}
.introduce-box div {
  width: 90vw;
  text-align: left;

}
.subdetail-box {
  background-color: white;
  box-shadow: var(--shadow-card);
  padding: 3vh 5vw;
  margin-bottom: 2vh;
}
.game-description {
  margin-top: 2vh;
  margin-right: 1vh;
}

</style>