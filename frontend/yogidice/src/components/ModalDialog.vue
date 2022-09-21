<template>
  <div class="modal-back">
    <div class="dialog">
      <modal-content>
        <template #header>
          {{ header }}
        </template>
        <template #body>
          {{ body }}
        </template>
        <template #footer>
          여기가 푸터
        </template>
      </modal-content>
    </div>
  </div>
</template>

<script>
import { toRefs } from '@vue/runtime-core'
import ModalContent from './ModalContent.vue'

export default {
  components: { 
    ModalContent 
  },
  props: {
    contents: Object,
  },
  setup(props) {
    const { header } = toRefs(props.contents)
    const { body } = toRefs(props.contents)
    return {
      header,
      body
    }
  }
}
</script>

<style>
.modal-back {
  position: relative;
  width: 100vw;
  height: 100vh;
  background-color: var(--color-bg-modal);
}

</style>