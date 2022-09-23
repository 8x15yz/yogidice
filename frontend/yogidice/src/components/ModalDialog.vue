<template>
  <div class="modal-back">
    <div class="dialog">
      <modal-content :typeAndTarget="typeAndTarget">
        <template #header>
          {{ header }}
        </template>
        <template #body>
          {{ body }}
        </template>
        <template #footer1>
          {{ footer1 }}
        </template>
        <template #footer2>
          {{ footer2 }}
        </template>
      </modal-content>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "@vue/runtime-core";
import { useStore } from "vuex";
import ModalContent from "./ModalContent.vue";

export default {
  components: {
    ModalContent,
  },
  props: {
    contents: Object,
  },
  setup(props) {
    const store = useStore();
    const { modalType } = toRefs(props.contents);
    const { header } = toRefs(props.contents);
    const { body } = toRefs(props.contents);
    const { footer1 } = toRefs(props.contents);
    const { footer2 } = toRefs(props.contents);
    const { nextPage } = toRefs(props.contents);
    const typeAndTarget = reactive({
      modalType: modalType,
      nextPage: nextPage,
    });

    onMounted(() => {
      const modalBack = document.querySelector(".modal-back");
      window.addEventListener("click", (e) => {
        if (e.target === modalBack) {
          store.commit("changeModal");
        }
      });
    });

    return {
      typeAndTarget,
      header,
      body,
      footer1,
      footer2,
    };
  },
};
</script>

<style>
.modal-back {
  position: fixed;
  width: 100vw;
  height: 100vh;
  top: 0px;
  background-color: var(--color-bg-modal);
}
</style>
