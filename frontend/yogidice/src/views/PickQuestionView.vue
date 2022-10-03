<template>
  <div class="question-cover">
    <div class="question-page">여기는 질문 몇번쨰인지</div>
    <question-list></question-list>
  </div>
</template>

<script>
import QuestionList from '@/components/QuestionList.vue'
export default {
  components: {
     QuestionList 
  },

}
</script>

<style scoped>
.question-cover {
  width: 100vw;
  height: 90vh;
  background-color: rgba(255,255,255,0.7);
  padding-top: 15vh;
  overflow: visible
}
.question-page {
  width: 100%;
}

</style>