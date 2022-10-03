<template>
  <div class="introduce-box">
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6">게임 유형</div>
      <ber-chart
      :lengamecategory="lengamecategory" >
      </ber-chart>
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6"> 게임 설명 </div>
      <div class="game-description"> Lorem ipsum dolor sit amet consectetur adipisicing elit. Inventore, iure! </div>
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6"> 게임 방식 </div>
      <ul>
        <li class="play-type text-body-2" @click="describeType"> Roll/Spin and Move <span class="material-icons-outlined">info</span> </li>
      </ul>
    </div>
    <div class="subdetail-box">
      <div class="subdetail-title text-headline-6"> 관련 영상 </div>
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
  let lengamecategory = computed(()=>store.state.gamedetail.lengamecategory)
  
  
  return {
    showModal,
    contents,
    describeType,
    lengamecategory,
  }
  
}

}
</script>

<style>
.play-type {
  height: 20px;;
  text-align: left;
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
  padding-left: 3vw;
}

</style>