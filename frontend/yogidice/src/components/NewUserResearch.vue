<template>
  <div>
    <div id="register-nickname">
      <label for="nickname-input" class="text-subtitle-2">사용할 닉네임을 입력하세요</label>
      <input id="nickname-input" v-model="nickNameValue" type="text" placeholder="카카오닉네임기본값">
      <div>
        <button @click="registNickname" class="button-small-blue text-button">계속</button>
      </div>
    </div>
    <modal-dialog v-show="showModal" :contents='contents'></modal-dialog>
  </div>

  
</template>

<script>
import {reactive, ref} from 'vue';
import ModalDialog from "@/components/ModalDialog.vue";

export default {
  components: {
    ModalDialog
  },
  setup() {
    let showModal = ref(false)
    let contents = reactive({
      'header':'',
      'body':''
    })
    let nickNameValue = ref('')
    const registNickname = function () {
      // if 로그인 성공이면
      contents.header = '로그인 성공'
      contents.body = `${ nickNameValue.value } 으로 등록하시겠습니까?`
      console.log(contents)

      showModal.value = true
    }
    return {
      showModal,
      nickNameValue,
      registNickname,
      contents
    }
  }

}
</script>

<style>
#register-nickname {
  display: flex;
  flex-direction: column;
  gap: 10px;
  position: absolute;
  top: 30vh;
  margin: 0 15vw;

}
#nickname-input {
  box-sizing: border-box;
  width: 70vw;
  height: 40px;
  border: 1px solid #000000;
  border-radius: 4px;
  padding: 5px 10px;
}
#register-nickname > div {
  display: flex;
  justify-content: flex-end;

}


</style>