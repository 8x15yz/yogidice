<template>
  <div>
    <service-introduce/>
    <img @click="kakaoLogin" id="kakao-img" src="../static/kakao_login.png" alt="">
  </div>
</template>

<script>
import ServiceIntroduce from '@/components/ServiceIntroduce.vue'
import { useRouter } from 'vue-router' 
export default {

  components: { 
    ServiceIntroduce
  },

  setup() {   
    const router = useRouter()

    const kakaoLogin = function () {
      console.log("하이")
      router.push({
        name:"NewUserResearch"
    })
    }
    return {
      kakaoLogin
    }
  }  
}
</script>

<style scoped>
  #kakao-img {
    width: 90vw;
    display: block; 
    margin: 5vh auto;


  }

</style>