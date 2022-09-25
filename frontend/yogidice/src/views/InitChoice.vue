<template>
  <div>
    <div v-show='initMessage' id="choiceMessage" class='text-headline-6'>여기다이스에서 내 취향에 맞는 <br> 보드게임을 추천 받으세요!</div>
    <div v-show='!initMessage' id="choiceBox">
      <div class='text-headline-6'>
        <div> 재미있게 플레이했던 게임을 선택해주세요! </div> 
      </div>
      <search-bar></search-bar> 
      <small-card-list></small-card-list>
      <button @click='registInitGame' class="button-long-dark init-select-btn text-button">아직 플레이해본 게임이 없다면?</button>
    </div>
    <modal-dialog v-show="showModal"></modal-dialog>
  </div>
</template>

<script>
import { onMounted, ref, computed, reactive, getCurrentInstance } from 'vue'
import { useStore } from 'vuex';
import SearchBar from '@/components/SearchBar.vue';
import ModalDialog from "@/components/modal/ModalDialog.vue";
import SmallCardList from "@/components/card/SmallCardList.vue";


export default {
  components: {
    SearchBar,
    SmallCardList,
    ModalDialog,
  },
  setup() {
    const store = useStore()
    let showModal = computed(()=>store.state.modal.showModal)
    let contents = reactive({
      'info':{'from':'','content':''},
      'header':'',
      'body':'',
      'footer1': '',
      'footer2': '',
    })
    let selectCnt = ref("")

    const registInitGame = function (e) {
      contents.info.from = 'initChoice'
      contents.header = ''
      if (e.target.className.includes('dark')) {
        contents.body = '게임을 선택하지 않으셨습니다. 계속 진행하시겠습니까?'
        contents.footer1 = '계속'
        contents.footer2 = '취소'
      } else { 
        contents.body = `${selectCnt.value}개의 게임을 제출하시겠습니까?`
        contents.footer1 = '계속'
        contents.footer2 = '취소'
      }
      store.dispatch("openModal")
    }




    const internalInstance = getCurrentInstance()
    const emitter = internalInstance.appContext.config.globalProperties.emitter
    
    let initMessage = ref(true)

    onMounted(() => {
      const initBtn = document.querySelector(".init-select-btn")
      emitter.on("selectCnt",(data) => {
        if (data.value>0) {
          initBtn.setAttribute("class","button-long-blue init-select-btn text-button")
          initBtn.innerText = "게임 선택"
        } else {
          initBtn.setAttribute("class","button-long-dark init-select-btn text-button")
          initBtn.innerText = "아직 플레이해본 게임이 없다면?"
        }
        selectCnt.value = data.value
      })
      setTimeout(function()
      { initMessage.value = !initMessage.value },
    3000)})


    return {
      initMessage,
      registInitGame,
      showModal,
      contents
    }
  }
}
</script>

<style>
#choiceMessage {
  position: relative;
  top: 40vh;
}
#choiceBox {
  display: flex;
  flex-direction: column;
  gap: 36px;
  justify-content: center;
  align-items: center;
  padding: 8vh 3vw;
}
.init-select-btn {
  position: fixed;
  top: 95vh
}

</style>