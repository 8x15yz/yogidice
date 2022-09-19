import { createApp } from 'vue'
import App from './App.vue'

// vuex
import { store } from './store/index'

// vue-router
import router from './router/index.js'

// axios
import axios from 'axios';

const app = createApp(App)
app.use(store)
app.use(router)

app.config.globalProperties.$axios = axios;

app.mount('#app')



