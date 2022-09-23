<template>
  <div class="modal-back">
    <div class="dialog">
<<<<<<< HEAD
      <modal-content :typeAndTarget='typeAndTarget'>
=======
      <modal-content :typeAndTarget="typeAndTarget">
>>>>>>> 414af86c8131039a23ba20213aab430ff1826ef4
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
<<<<<<< HEAD
import { onMounted, reactive, toRefs } from '@vue/runtime-core'
import { useStore } from 'vuex'
import ModalContent from './ModalContent.vue'
=======
import { onMounted, reactive, toRefs } from "@vue/runtime-core";
import { useStore } from "vuex";
import ModalContent from "./ModalContent.vue";
>>>>>>> 414af86c8131039a23ba20213aab430ff1826ef4

export default {
  components: {
    ModalContent,
  },
  props: {
    contents: Object,
  },
  setup(props) {
<<<<<<< HEAD
    const store = useStore()
    const { modalType } = toRefs(props.contents)
    const { header } = toRefs(props.contents)
    const { body } = toRefs(props.contents)
    const { footer1 } = toRefs(props.contents)
    const { footer2 } = toRefs(props.contents)
    const { nextPage } = toRefs(props.contents)
    const typeAndTarget = reactive({ 'modalType': modalType, 'nextPage': nextPage})

    onMounted(() => { 
      const modalBack = document.querySelector(".modal-back")
      window.addEventListener("click", (e) => {
      if (e.target === modalBack) {
        store.commit('changeModal')
      }
      })  
    })
=======
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
>>>>>>> 414af86c8131039a23ba20213aab430ff1826ef4

    return {
      typeAndTarget,
      header,
      body,
      footer1,
<<<<<<< HEAD
      footer2
      
    }
  }
}
=======
      footer2,
    };
  },
};
>>>>>>> 414af86c8131039a23ba20213aab430ff1826ef4
</script>

<style>
.modal-back {
  position: relative;
  width: 100vw;
  height: 100vh;
  background-color: var(--color-bg-modal);
}
</style>
