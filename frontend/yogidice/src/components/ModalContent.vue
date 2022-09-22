<template>
  <div class="modal-wrap">
    <div class="modal-header">
      <slot name="header"></slot>
    </div>
    <div class="modal-body">
      <slot name="body"></slot>
    </div>
    <div class="modal-footer">
      <button class="button-small-nonbg-blue text-button"><slot name="footer1"></slot></button>
      <button class="button-small-nonbg-blue text-button"><slot name="footer2"></slot></button>      
    </div>
  </div>
</template>

<script>
import { onMounted, toRefs, watch } from '@vue/runtime-core'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  props: { typeAndTarget: Object },

  setup(props) {
    const store = useStore()
    const router = useRouter()

    const { modalType } = toRefs(props.typeAndTarget);
    const { nextPage } = toRefs(props.typeAndTarget);
    
    onMounted(function() {
      const bodyBox = document.querySelector(".modal-body")
      const firstButton = document.querySelector(".modal-footer button:nth-child(1)")
      const secondButton = document.querySelector(".modal-footer button:nth-child(2)")
     
     watch(modalType, ( newValue, oldValue ) => {
      if (newValue === 'onlyContent') {
        bodyBox.classList.add("text-subtitle-2")
        bodyBox.style.color = "var(--color-grey-5)"
        firstButton.addEventListener('click', function(){
          if (firstButton.innerText !== "") {
            store.commit("changeModal")
            bodyBox.classList.remove("text-subtitle-2")
            router.push(
              {
                name:nextPage.value
              })
          }
        })
        secondButton.addEventListener('click', function() {
          if (secondButton.innerText === "취소" || secondButton.innerText === "닫기") {
            store.commit("changeModal")
            bodyBox.classList.remove("text-subtitle-2")
          }
        })
      } else {
        console.log(oldValue,firstButton)
      }
    })
    }
    )
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