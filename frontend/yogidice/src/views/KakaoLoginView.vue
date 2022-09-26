<template>
  <div>로딩중...</div>
</template>

<script>
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();

    let query = window.location.search;
    let param = new URLSearchParams(query);
    let code = param.get("code");

    const getInfo = () => store.dispatch("user/getKakaoUserInfo", code);
    getInfo(code);

    return {
      code,
    };
  },
};
</script>

<style></style>
