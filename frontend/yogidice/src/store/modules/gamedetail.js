import api from "@/api/api";
import axios from "axios";

export default {
    namespaced: true,

    state: () => ({
        lengamecategory: [0, 0, 0, 0, 0, 0],
        detail: {},
    }),
    getters: {},
    mutations: {
        SET_DETAIL: (state, details) => (state.detail = details),
        LIKE_P_MEC: (state, gid) => (state.lengamecategory[gid] += 1),
        LIKE_P_MEC_RESET: (state) => (state.lengamecategory = [0, 0, 0, 0, 0, 0]),

    },
    actions: {
        getLengames({commit}, gameId) {
            commit('LIKE_P_MEC_RESET')
            axios({
              url: api.games.detailEdit(gameId),
              method: "get",
            })
              .then((res) => {
                let pmec = [0, 0, 0, 0, 0, 0]
                for (let mecha of res.data.mechanismGroupResponses) {
                    if (mecha.parentsMec == '추리카드퍼즐') { commit('LIKE_P_MEC',0 ); pmec[0] += 1}
                    else if (mecha.parentsMec == '경제') { commit('LIKE_P_MEC',1); pmec[1] += 1}
                    else if (mecha.parentsMec == '파티') { commit('LIKE_P_MEC',2); pmec[2] += 1}
                    else if (mecha.parentsMec == '조건') { commit('LIKE_P_MEC',3); pmec[3] += 1}
                    else if (mecha.parentsMec== '말') { commit('LIKE_P_MEC',4); pmec[4] += 1}
                    else if (mecha.parentsMec == '전략') { commit('LIKE_P_MEC',5); pmec[5] += 1}
                }
                commit("SET_DETAIL", res.data);
            })
              .catch((err) => {console.log(err)})
        }
        
    }
}