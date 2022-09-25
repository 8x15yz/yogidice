<template>
  <img class="smallImg" @click="selectImg" :src='imgUrl' alt="">
  <p class="text-subtitle-2">{{ title_kr }}</p>
  <span id="checked-mark" v-show="checkedMark" class="material-icons">check_circle</span>
</template>

<script>
import { computed,toRefs,reactive,getCurrentInstance,ref } from "vue"

export default {
  props: {
    game: Object
  },
  setup(props) {
    const internalInstance = getCurrentInstance()
    const emitter = internalInstance.appContext.config.globalProperties.emitter

    let { key } = toRefs(props.game)
    let { title_kr } = toRefs(props.game)
    let { thumburl } = toRefs(props.game)
    let checkedMark = ref(false)

    let imgUrl = computed(() => `https://boardlife.co.kr/${thumburl.value}`)
    const userSelection = reactive([])
    let selectCnt = computed(() => userSelection.length)

    // 지금 일단 동시 선택 안됨(선택은 되는데 담기지가 않음)
    const selectImg = function(e) {
      let gameTitle = e.target.nextElementSibling.innerText
      if (e.target.style.filter) {
      e.target.style.filter = ""
      userSelection.splice(userSelection.indexOf(gameTitle),1)

      } else {
      e.target.style.filter = "brightness(50%)"
      userSelection.push(gameTitle)
      console.log(userSelection)
      }
      checkedMark.value = !checkedMark.value
      emitter.emit("selectCnt",selectCnt)
    }


  return {
    key,
    title_kr,
    imgUrl,
    userSelection,
    checkedMark,
    selectImg
  }}


}
</script>

<style scoped>
.smallImg {
  width: 28vw;
  height: 28vw;
  object-fit: cover;
}
.smallImg + p{
  white-space: nowrap;
  overflow:hidden;
  text-overflow: ellipsis;
  margin-top: 0px;
}
#checked-mark {
  color: white;
  position: absolute;
  top: 8px;
  right: 6px;
  font-size: 8vw;
}


</style>