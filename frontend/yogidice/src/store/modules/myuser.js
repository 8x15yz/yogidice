import api from "@/api/api";
import axios from "axios";

export default {
    namespaced: true,
    state: () => ({
        token: localStorage.getItem("token"),
        nickName: '',
        kakaoId: '',
        bookmark: [],
        history: [],
        likeGameId: [],
        likeMecha: [],
        likePMec:[0, 0, 0, 0, 0, 0],
        likePMecMax:'보드게임',
        rearrangemeca:[],
    }),
    getters: {
        authHeader: (state) => ({
            Authorization: `Bearer ${state.token}`,
            "Content-type": "Application/JSON",
        }),
        likemec: (state) => (state.bookmark)
    },
    mutations: {
        SET_NICKNAME: (state, nickName) => (state.nickName = nickName),
        SET_KAKAOID: (state, kakaoId) => (state.kakaoId = kakaoId),
        SET_BOOKMARK: (state, bookmarkResponses) => (state.bookmark = bookmarkResponses),
        SET_HISTORY: (state, historyResponses) => (state.history = historyResponses),
        LIKE_GAME_ID: (state, GameId) => (state.likeGameId.push(GameId)),
        LIKE_MECHA: (state, GameMec) => (state.likeMecha.push(GameMec)),
        LIKE_P_MEC: (state, gid) => (state.likePMec[gid] += 1),
        LIKE_P_MEC_MAX: (state, gname) => (state.likePMecMax = gname),
        REARRANGE_G_MEC: (state, wordelem) => (state.rearrangemeca.push(wordelem)),
    },
    actions: {
        GetUserInfo({ getters, commit }) {
            axios({
                url: api.users.get(),
                method: "get",
                headers: getters.authHeader
            }).then((res) => {
                // console.log(res.data.statusCode)
                commit("SET_NICKNAME", res.data.nickName);
                commit("SET_KAKAOID", res.data.kakaoId);
                commit("SET_BOOKMARK", res.data.bookmarkResponses);
                commit("SET_HISTORY", res.data.historyResponses);
                async function bookmarkgameidsave() {
                    let likegameid = [];
                    for (const mec in res.data.bookmarkResponses) {
                        commit('LIKE_GAME_ID', res.data.bookmarkResponses[mec].id)
                        likegameid.push(res.data.bookmarkResponses[mec].id)
                    }    
                    return likegameid
                }
                bookmarkgameidsave().then((likegameid) => {
                    for (const id in likegameid){
                        axios({
                            url: api.games.detailEdit(likegameid[id]),
                            method: "get",
                          })
                            .then((res) => {
                                let pmec = [0, 0, 0, 0, 0, 0]
                                let pmecmax = ['추리카드퍼즐', '경제', '파티', '조건', '말', '전략']
                                for ( let mec in res.data.mechanismGroupResponses) {
                                    commit('LIKE_MECHA', [
                                        res.data.mechanismGroupResponses[mec].mechanismName
                                        , pmecmax.indexOf(res.data.mechanismGroupResponses[mec].parentsMec)
                                    ])
                                    if (res.data.mechanismGroupResponses[mec].parentsMec == '추리카드퍼즐') { commit('LIKE_P_MEC',0 ); pmec[0] += 1}
                                    else if (res.data.mechanismGroupResponses[mec].parentsMec == '경제') { commit('LIKE_P_MEC',1); pmec[1] += 1 }
                                    else if (res.data.mechanismGroupResponses[mec].parentsMec == '파티') { commit('LIKE_P_MEC',2); pmec[2] += 1 }
                                    else if (res.data.mechanismGroupResponses[mec].parentsMec == '조건') { commit('LIKE_P_MEC',3); pmec[3] += 1 }
                                    else if (res.data.mechanismGroupResponses[mec].parentsMec== '말') { commit('LIKE_P_MEC',4); pmec[4] += 1 }
                                    else if (res.data.mechanismGroupResponses[mec].parentsMec == '전략') { commit('LIKE_P_MEC',5); pmec[5] += 1 }

                                    let wordelem = new Object();
                                    wordelem.text = res.data.mechanismGroupResponses[mec].mechanismName
                                    wordelem.size = 10
                                    wordelem.color = 'red'
                                    commit('REARRANGE_G_MEC',wordelem )
                                }
                                commit('LIKE_P_MEC_MAX', pmecmax[Math.max(...pmec)])
                            })
                            .catch((err) => console.log(err));
                    }
                        
                })
                
            })
            .catch((err) => {console.log(err)});
        },
    }
}