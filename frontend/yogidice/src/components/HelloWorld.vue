<template>
  <h1> 배포성공 ~~~~ </h1>
    <div v-for="(test, idx) in testlist" :key="idx">
      {{test.name}}  |  {{test.email}}  |  {{test.created_at}}
    </div>
</template>


<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      testlist: []
    }
  },
  created() {
    this.$axios.get('http://127.0.0.1:8000/api/v1/test_list')
    .then(res => {
      this.testlist = res.data
    })
    .catch(err => {
      console.log(err)
    })

    
  }
}
</script>

