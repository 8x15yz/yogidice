// import Vue from "vue";
// import VueRouter from "vue-router";
import {createRouter, createWebHistory } from "vue-router";

import DiceBgLayout from '@/layouts/DiceBgLayout.vue'
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue'
import HomeView from '@/views/HomeView.vue';
import SignupView from '@/views/SignupView.vue';
import QwerTy from '@/components/QwerTy.vue';
import BerChart from '@/components/BerChart.vue';
import WordCloud from '../components/WordCloud.vue';
import InitChoice from '@/views/InitChoice.vue';
import MainPageView from '@/views/MainPageView.vue'

// Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'HomeView',
        component: HomeView,
    },
    {
        path: '/signup',
        component: DiceBgLayout,
        children: [
            {
                path: '',
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
        path: '/home',
        component: GreyBgHeadBar,
        children: [
            {
                path: '',
                name: 'MainPage',
                component: MainPageView
            }
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
];


const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;
