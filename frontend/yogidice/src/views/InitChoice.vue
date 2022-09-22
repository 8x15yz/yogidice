<template>
  <div>
    <div v-show='initMessage' class='text-headline-6'>여기다이스에서 내 취향에 맞는 <br> 보드게임을 추천 받으세요!</div>
    <div v-show='!initMessage' class='text-headline-6' style='display:block'>
      <div> 재미있게 플레이했던 게임을 선택해주세요! </div>
      <search-bar></search-bar>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import SearchBar from '@/components/SearchBar.vue'

export default {
  components: {
    SearchBar
  },
  setup() {
    let initMessage = ref(true)

    onMounted(() => {
      setTimeout(function()
      { initMessage.value = !initMessage.value },
    3000)})

    return {
      initMessage
    }
  }

}
</script>

<style>

</style>