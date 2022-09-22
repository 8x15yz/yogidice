// import Vue from "vue";
// import VueRouter from "vue-router";
import { createRouter, createWebHistory } from "vue-router";

import HomeView from "@/views/HomeView.vue";
import SignupView from "@/views/SignupView.vue";
import KakaoLoginView from "@/views/KakaoLoginView.vue";
import QwerTy from "@/components/QwerTy.vue";
import BerChart from "@/components/BerChart.vue";
import NewUserResearch from "@/components/NewUserResearch.vue";
import WordCloud from "../components/WordCloud.vue";
import InitChoice from "@/views/InitChoice.vue";

// Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: HomeView,
  },
  {
    path: "/signup",
    name: "SignupView",
    component: SignupView,
  },
  {
    path: "/kakaologin",
    name: "KakaoLoginView",
    component: KakaoLoginView,
  },
  {
    path: "/research",
    name: "NewUserResearch",
    component: NewUserResearch,
    props: true,
  },
  {
    path: "/choice",
    name: "InitChoice",
    component: InitChoice,
  },
  {
    path: "/qwerty",
    name: "QwerTy",
    component: QwerTy,
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
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
