<template>
  <div>
    <div id="register-nickname">
      <label for="nickname-input" class="text-subtitle-2"
        >사용할 닉네임을 입력하세요</label
      >
      <input id="nickname-input" v-model="nickNameValue" type="text" />
      <div>
        <button @click="registNickname" class="button-small-blue text-button">
          계속
        </button>
      </div>
    </div>
    <modal-dialog v-show="showModal" :contents="contents"></modal-dialog>
  </div>
</template>

<script>
import { computed, reactive, ref } from "vue";
import { useStore } from "vuex";
import ModalDialog from "@/components/ModalDialog.vue";

export default {
  components: {
    ModalDialog,
  },
  props: {
    nickName: String,
  },
  setup(props) {
    const store = useStore();
    // 닉네임 중복 여부
    let nickNameCheck = true;

    // 모달을 만들고 싶다면?
    // 1. 모달을 열고 닫게 할 showModal 가져오기
    // 2. 모달에 적을 것 빈칸으로 만들어놓기
    // 2. 모달 버튼 눌렀을 때, 모달에 들어갈 내용 입력해주고, 띄우기
    let showModal = computed(() => store.state.modal.showModal);
    let contents = reactive({
      modalType: "",
      nextPage: "",
      header: "",
      body: "",
      footer1: "",
      footer2: "",
    });
    let nickNameValue = ref("");
    const initNickName = () => {
      nickNameValue.value = props.nickName;
    };
    initNickName();

    const registNickname = function () {
      contents.modalType = "onlyContent";
      contents.header = "";
      contents.nextPage = "InitChoice";
      // 만약 닉네임이 중복이 아니라면
      if (nickNameCheck) {
        contents.body = `${nickNameValue.value} 으로 등록하시겠습니까?`;
        contents.footer1 = "계속";
        contents.footer2 = "취소";
      } else {
        // 만약 닉네임이 중복이라면
        contents.body = `이미 사용중인 닉네임입니다`;
        contents.footer1 = "";
        contents.footer2 = "닫기";
      }
      store.commit("changeModal");
    };
    return {
      showModal,
      nickNameValue,
      registNickname,
      contents,
    };
  },
};
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
