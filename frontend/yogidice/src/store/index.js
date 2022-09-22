import { createStore } from "vuex";
import modal from "@/store/modules/modal.js";
import page from "@/store/modules/page.js"

export default createStore({
  modules: {  
    modal,
    page
  }
});
