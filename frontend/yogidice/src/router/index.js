// import Vue from "vue";
// import VueRouter from "vue-router";
import {createRouter, createWebHistory } from "vue-router";

import HelloWorld from '../components/HelloWorld.vue';
import QwerTy from '../components/QwerTy.vue';
import BerChart from '../components/BerChart.vue';
import WordCloud from '../components/WordCloud.vue';

// Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'HelloWorld',
        component: HelloWorld,
    },
    {
        path: '/qwerty',
        name: 'QwerTy',
        component: QwerTy,
    },
    {
        path: '/BerChart',
        name: 'BerChart',
        component: BerChart,
    },
    {
        path: '/WordCloud',
        name: 'WordCloud',
        component: WordCloud,
    },
];


const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;
