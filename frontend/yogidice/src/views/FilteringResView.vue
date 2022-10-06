<template>
  <div>
    <grey-bg-head-bar></grey-bg-head-bar>
  </div>
  <div class="res-cover">
    <div class="text-headline-6">
      <div>
        <div class="displayflex" style="margin-top: 20px;">
          지금 나에게 맞는 게임
        </div>
        <div class="displayflex">
          <div class="again" @click="returnpick">다시하기</div>
        </div>
      </div>
    </div>
    <main-card-list id="res-list"></main-card-list>
  </div>
</template>

<script>
import MainCardList from "@/components/card/MainCardList.vue";
import GreyBgHeadBar from "@/layouts/GreyBgHeadBar.vue";
import { useRouter } from "vue-router";

export default {
  components: {
    MainCardList,
    GreyBgHeadBar,
  },
  setup() {
    const router = useRouter();

    const moveToMain = function () {
      router.push({ name: "MainPage" });
    };
    const moveToMypage = function () {
      router.push({ name: "MypageView" });
    };
    const returnpick = function () {
      router.push({ name: "GamePickHome" });
    };
    return {
      moveToMain,
      moveToMypage,
      returnpick
    };
  },
};
</script>

<style>
.displayflex {
  display: flex;
  justify-content: center;
}
.again {
  margin-top: 20px;
  width: 90px;
  height: 35px;
  border: 3px solid rgb(49, 49, 49);
  color: rgb(49, 49, 49);
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 40px 40px;
}
.res-cover {
  width: 90vw;
  height: 90vh;
  background-color: rgba(255, 255, 255, 0.7);
  padding: 5vh 5vw;
  display: flex;
  justify-content: center;
}
.res-cover #res-list {
  display: block;
  width: 90vw;
  height: 95vh;
  /* flex-wrap: wrap; */
  gap: 2vh;
  overflow: scroll;
}
.res-cover #res-list > div {
  margin-bottom: 2vh;
}
.res-cover #res-list > div.card-more {
  visibility: hidden;
}
</style>
