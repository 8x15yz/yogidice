import { createStore } from "vuex";
import modal from "@/store/modules/modal.js";
import page from "@/store/modules/page.js";
import user from "@/store/modules/user.js";

export default createStore({
  modules: {
    modal,
    page,
    user,
  },
});
