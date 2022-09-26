import { createStore } from "vuex";
import modal from "@/store/modules/modal.js";
import page from "@/store/modules/page.js";
import user from "@/store/modules/user.js";
import games from "@/store/modules/games.js"

export default createStore({
  modules: {
    modal,
    page,
    user,
    games,
  },
});
