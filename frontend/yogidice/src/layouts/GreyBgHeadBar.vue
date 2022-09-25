<template> 
  <header>
    <div class="header-box">
      <div class="header-1">
        <img id="head-logo" src="../static/logo_white.png" alt="">
      </div>
      <div id="head-title" class="text-headline-6">여기 Dice</div>
      <div class="header-2">
        <span id="head-profile" class="material-icons">person</span>
      </div>
    </div>
  </header>
  <router-view id="wrap2"></router-view>
</template>

<script>
export default { 

}
</script>

<style>
header {
  position: sticky;
}
#wrap2 {
  background-color: var(--color-bg-base);
  height: 94vh;
  overflow: scroll;
}
.header-box {
  display: flex;
  justify-content: center;
  height: 6vh;
  padding: 3vw 4vw 0vw 4vw;
  gap: 4vw;
  background: black
}
.header-1 {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  width: 16vw;
}
.header-2 {
  display: flex;
  justify-content: flex-end;
  align-self: center;
  width: 16vw;
}
#head-title {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60vw;
  font-size: 5vw;
  color: white
}
#head-profile {
  font-size: 8vw;
  color: white;
}
</style>