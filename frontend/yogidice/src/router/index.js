// import Vue from "vue";
// import VueRouter from "vue-router";
import { createRouter, createWebHistory } from "vue-router";

import HomeView from "@/views/HomeView.vue";
import SignupView from "@/views/SignupView.vue";
import KakaoLoginView from "@/views/KakaoLoginView.vue";
import BerChart from "@/components/BerChart.vue";
import RegistNickName from "@/components/intro/RegistNickName.vue";
import WordCloud from "../components/WordCloud.vue";
import InitChoice from "@/views/InitChoice.vue";
import MypageView from "@/views/MypageView.vue";
import MainPageView from "@/views/MainPageView.vue";
import MoreGameView from "@/views/MoreGameView.vue";
import DiceBgLayout from '@/layouts/DiceBgLayout.vue'
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue'
import DetailView from '@/views/DetailView.vue'
import PlaceView from "@/views/PlaceView.vue"
import GamePlusView from "@/views/GamePlusView.vue"
import BoardBgLayout from "@/layouts/BoardBgLayout.vue"
import PickView from "@/views/PickView.vue"
import PickQuestionView from "@/views/PickQuestionView.vue"
import FilteringResView from "@/views/FilteringResView.vue"


// Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: HomeView,
  },
  {
    path: "/BerChart",
    name: "BerChart",
    component: BerChart,
  },
  {
    path: "/WordCloud",
    name: "WordCloud",
    component: WordCloud,
  },
  {
    path: "/MyPage",
    name: "MypageView",
    component: MypageView,
  },
  {
    path: "/signup",
    component: DiceBgLayout,
    children: [
      {
        path: "",
        name: "SignupView",
        component: SignupView,
      },
      {
        path: "regist",
        name: "RegistNickName",
        component: RegistNickName,
        props: true,
      },
      {
        path: "choice",
        name: "InitChoice",
        component: InitChoice,
      },
      {
        path: "/kakaologin",
        name: "KakaoLoginView",
        component: KakaoLoginView,
      },
    ],
  },
  {
    path: "/home",
    component: GreyBgHeadBar,
    children: [
      {
        path: "",
        name: "MainPage",
        component: MainPageView,
      },
      {
        path: ":type",
        name: "MoreList",
        component: MoreGameView,
      },
    ],
  },
  {
    path: "/game",
    component: GreyBgHeadBar,
    children: [
      {
        path: "/info",
        name: "GameDetail",
        component: DetailView,
      },
    ],
  },
  {
    path: "/cafes",
    component: GreyBgHeadBar,
    children: [
      {
        path: "",
        name: "PlaceView",
        component: PlaceView,
      },
    ],
  },
  {
    path: '/plusgame',
    name: 'GamePlusView',
    component: GamePlusView,
  },
  {
    path: "/pick",
    component: BoardBgLayout,
    children: [
      {
        path: "",
        name: "GamePickHome",
        component: PickView
      },
      {
        path: "question",
        name: "QuestionView",
        component: PickQuestionView
      },
      {
        path:"result",
        name: "PickResultView",
        component: FilteringResView
      }
    ]

  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
