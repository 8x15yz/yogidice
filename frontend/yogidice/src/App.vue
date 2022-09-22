<template>
  <router-view id="wrap1"/>
</template>

<script>
export default {
  name: 'App',
  components: {

  },
  setup() {

  }
}
</script>

<style>
  #app {
    width: 100vw;
    height: 100vh; 
    background: url("static/lending_page.png") no-repeat fixed;
    background-size: 100% 100%;
    position: fixed;
  }
  #wrap1 {
    width: 100%;
    height: 100%;
    background-color: rgba(255,255,255,0.8)
  }


</style>
