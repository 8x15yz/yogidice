<template>
  <div class="type-comment-bg text-subtitle-2">
    <div class="type-comment">일주일간 가장 <br> 인기 있었던 게임입니다🏅</div>
  </div>
  <div id="wrap-back-more">
    <more-card-list>
    </more-card-list> 
  </div>
</template>

<script>
import MoreCardList from "@/components/card/MoreCardList.vue"

export default {
  components: { 
    MoreCardList
  },
  setup() {
    return {
      
    }
  }

}
</script>

<style>
.type-comment-bg {
  width: 100vw;
  height: 20vh;
  position: relative;
  background-image: url("../static/topten.jpg");
  background-size: cover;
  text-align: center;
  color: white
}
#wrap-back-more {
  width: 100vw;
  height: 80vh;
  background-color: white;
  overflow: scroll;
}
.type-comment{
  width: 100%;
  height: 12vh;
  padding: 4vh 0 4vh 0;
  background-color: var(--color-bg-modal);
  color: white;
  text-align: center;
  font-size: 20px;
  line-height: 5vh;

}
</style>