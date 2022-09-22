// import Vue from "vue";
// import VueRouter from "vue-router";
import {createRouter, createWebHistory } from "vue-router";

import DiceBg from '@/layouts/DiceBgLayout.vue'
import HomeView from '@/views/HomeView.vue';
import SignupView from '@/views/SignupView.vue';
import QwerTy from '@/components/QwerTy.vue';
import BerChart from '@/components/BerChart.vue';
import WordCloud from '../components/WordCloud.vue';
import InitChoice from '@/views/InitChoice.vue';
import SearchBar from '@/components/SearchBar.vue'
import MypageView from '@/views/MypageView.vue';

// Vue.use(VueRouter);

const routes = [
    { 
        path: '/test',
        name: 'test',
        component: SearchBar
    },
    {
        path: '/',
        name: 'HomeView',
        component: HomeView,
    },
    {
        path: '/signup',
        component: DiceBg,
        children: [
            {
                path: '/',
                name: 'SignupView',
                component: SignupView,
            },
            {            
                path: 'choice',
                name: 'InitChoice',
                component: InitChoice
            },
        ]
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
    {
        path: '/MyPage',
        name: 'MypageView',
        component: MypageView,
    }
];


const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;
