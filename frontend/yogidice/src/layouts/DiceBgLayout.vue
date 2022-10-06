<template>
  <router-view id="wrap1"></router-view>
</template>

<script>
import { useRoute } from "vue-router";
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
#wrap1 {
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.8);
}
</style>
