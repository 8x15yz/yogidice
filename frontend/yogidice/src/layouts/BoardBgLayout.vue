<template>
  <div id="wrap3">
    <router-view :key="route.fullPath"></router-view>
  </div>
</template>

<script>
import { useRoute } from "vue-router"
export default {
  setup() {
    const route = useRoute();

    return {
      route,
    };
  },
};
</script>

<style>
#wrap3 {
  width: 100vw;
  height: 100vh;
  background: url("../static/pickbg1.jpg") no-repeat center;
  background-size: cover;
}
</style>
