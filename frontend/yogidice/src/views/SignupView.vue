<template>
  <div>
    <div v-show="signUpPage">
      <service-introduce />
      <img
        @click="kakaoLogin"
        id="kakao-img"
        src="../static/kakao_login.png"
        alt="Kakao Login"
      />
    </div>
    <new-user-research v-show="nickNamePage" />
  </div>
</template>

<script>
import ServiceIntroduce from "@/components/ServiceIntroduce.vue";
import NewUserResearch from "@/components/NewUserResearch.vue";
import { useStore } from "vuex";
// import { useRouter } from 'vue-router'
import { computed } from "vue";
export default {
  components: {
    ServiceIntroduce,
    NewUserResearch,
  },

  setup() {
    const store = useStore();
    let signUpPage = computed(() => store.state.page.signUpPage);
    let nickNamePage = computed(() => store.state.page.nickNamePage);
    // const router = useRouter()

    const kakaoLogin = function () {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=" +
          process.env.VUE_APP_KAKAO_REST_API_KEY +
          "&redirect_uri=" +
          process.env.VUE_APP_KAKAO_REDIRECT_URI +
          "&response_type=code",
      );
      // router.push({
      //   name:"NewUserResearch")}
      // store.commit("changeSignUpPage");
      // store.commit("changeNickNamePage");
    };
    return {
      kakaoLogin,
      signUpPage,
      nickNamePage,
    };
  },
};
</script>

<style scoped>
#kakao-img {
  width: 90vw;
  display: block;
  margin: 5vh auto;
}
</style>
