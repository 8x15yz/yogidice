<template>
  <div class="modal-wrap">
    <div class="modal-header">
      <slot name="header"></slot>
    </div>
    <div class="modal-body">
      <slot name="body"></slot>
    </div>
    <div class="modal-footer">
      <button class="button-small-nonbg-blue text-button">
        <slot name="footer1"></slot>
      </button>
      <button class="button-small-nonbg-blue text-button">
        <slot name="footer2"></slot>
      </button>
    </div>
  </div>
</template>

<script>
import { onMounted, computed, watch } from "@vue/runtime-core";
import { useStore } from "vuex";

export default {

  setup() {
    const store = useStore();

    let isShowModal = computed(() => store.state.modal.showModal)

    let info = computed(() => store.state.modal.contents.info)

    // 이벤트 리스너 중복 방지를 위한 함수 정의
    const registerToInit = function () {
          store.dispatch("user/kakaoRegist", {'kakaoId':info.value.content.kakaoId, "nickname":info.value.content.nickname});
        }
    const registBookMark = function () {
          store.dispatch("user/registBookMark", {'gameList':info.value.content});
        }
    const closeModal = function () { 
          store.dispatch("modal/closeModal")
        }

    onMounted( function () {
      const titleBox = document.querySelector(".modal-header")
      const bodyBox = document.querySelector(".modal-body");
      const firstButton = document.querySelector(
        ".modal-footer button:nth-child(1)",
      );
      const secondButton = document.querySelector(
        ".modal-footer button:nth-child(2)",
      );
      titleBox.classList.add("text-subtitle-1")
      bodyBox.classList.add("text-subtitle-2");
      bodyBox.style.color = "var(--color-grey-5)";

      watch(isShowModal, (newValue) => {
        if (newValue === true && info.value.from === "registNickName") {
          console.log(firstButton)
          firstButton.addEventListener("click", registerToInit);
          secondButton.addEventListener("click", closeModal)    
        } else if (newValue === true && info.value.from === "initChoice") {
          firstButton.addEventListener("click", registBookMark)
          secondButton.addEventListener("click", closeModal)
        }
      })
    })
  }
 }

</script>

<style>
.modal-wrap {
  width: 100%;
}
.modal-header {
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  padding: 0px 16px 0px 16px;
  /* Inside auto layout */
}
.modal-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  margin: 20px 0px;
  padding: 0px 16px 12px 16px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  padding: 0px;
}
</style>
