// import Vue from "vue";
// import VueRouter from "vue-router";
import {createRouter, createWebHistory } from "vue-router";

import HomeView from '../views/HomeView.vue';
import SignupView from '../views/SignupView.vue';
import QwerTy from '../components/QwerTy.vue';
import BerChart from '../components/BerChart.vue';
import NewUserResearch from '@/views/NewUserResearch.vue';

// Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'HomeView',
        component: HomeView,
    },
    {
        path: '/signup',
        name: 'SignupView',
        component: SignupView,
    },
    {
        path: '/research',
        name: 'NewUserResearch',
        component: NewUserResearch,
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
];


const router = createRouter({
    history: createWebHistory(),
    routes,
  });

export default router;
