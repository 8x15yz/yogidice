import { createStore } from "vuex";
import modal from "@/store/modules/modal.js";
<<<<<<< HEAD
import page from "@/store/modules/page.js"

export default createStore({
  modules: {  
    modal,
    page
  }
=======
import page from "@/store/modules/page.js";
import user from "@/store/modules/user.js";

export default createStore({
  modules: {
    modal,
    page,
    user,
  },
>>>>>>> 414af86c8131039a23ba20213aab430ff1826ef4
});
