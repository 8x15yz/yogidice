<template>
  <header>
    <div class="header-box">
      <div class="header-1">
        <img id="head-logo" @click="moveToMain" src="../static/logo_white.png" alt="">
      </div>
      <div id="head-title" class="text-headline-6">여기 Dice</div>
      <div class="header-2">
        <span id="head-profile" @click="moveToMypage" class="material-icons">person</span>
      </div>
    </div>
  </header>
  
  <div class="res-cover">
    <div class="text-headline-6">지금 나에게 맞는 게임</div>
    <main-card-list id="res-list"></main-card-list>
  </div>
</template>

<script>
import MainCardList from "@/components/card/MainCardList.vue"

export default {
  components:{
    MainCardList
  }

}
</script>

<style>
.res-cover {
  width: 90vw;
  height: 90vh;
  background-color: rgba(255,255,255,0.7);
  padding: 5vh 5vw;
  display:flex;
  justify-content: center;
}
.res-cover #res-list {
  display: block;
  width: 90vw;
  height: 95vh;
  /* flex-wrap: wrap; */
  gap:2vh;
  overflow: scroll;
}
.res-cover #res-list>div{
  margin-bottom: 2vh;
}
.res-cover #res-list>div.card-more{
  visibility: hidden;
}
</style>