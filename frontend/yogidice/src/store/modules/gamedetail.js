import api from "@/api/api";
import axios from "axios";

export default {
    namespaced: true,

    state: () => ({
        lengamecategory: [0, 0, 0, 0, 0, 0],
    }),
    getters: {},
    mutations: {
        SET_LENGAME: (state, lengamecategory) => (state.lengamecategory = lengamecategory),
    },
    actions: {
        getLengame({ commit }, gameId) {
            axios({
              url: api.games.detailEdit(gameId),
              method: "get",
            })
              .then((res) => {
                const mgrlist = res.value.mechanismGroupResponses
                console.log('이ㅣ거돼?', res.value.mechanismGroupResponses)
                
                for (let mgr of mgrlist) {
                    console.log(mgr.parentsMec)
                    if (mgr.parentsMec == '추리카드퍼즐') { lengamecategory[0] += 1}
                    else if (mgr.parentsMec == '경제') { lengamecategory[1] += 1}
                    else if (mgr.parentsMec == '파티') { lengamecategory[2] += 1}
                    else if (mgr.parentsMec == '조건') { lengamecategory[3] += 1}
                    else if (mgr.parentsMec == '말') { lengamecategory[4] += 1}
                    else if (mgr.parentsMec == '전략') { lengamecategory[5] += 1}
                }
                console.log(lengamecategory)
                commit("SET_LENGAME", lengamecategory);
              })
              .catch((err) => console.log('이거안돼', err));
        },
    }
}