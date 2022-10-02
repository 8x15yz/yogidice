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
        likePMec:[0, 0, 0, 0, 0, 0]
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
        LIKE_P_MEC: (state, meclist) => (state.likePMec = meclist),
        LIKE_GAME_ID: (state, GameId) => (state.likeGameId.push(GameId)),
    },
    actions: {
        GetUserInfo({ getters, commit }) {
            axios({
                url: api.users.get(),
                method: "get",
                headers: getters.authHeader
            }).then((res) => {
                console.log(res.data.statusCode)
                commit("SET_NICKNAME", res.data.nickName);
                commit("SET_KAKAOID", res.data.kakaoId);
                commit("SET_BOOKMARK", res.data.bookmarkResponses);
                commit("SET_HISTORY", res.data.historyResponses);
            })
            .catch((err) => {console.log(err)});
        },
        // GetLikeMec({commit, getters}) {
        //     let likemec = getters.likemec;
        //     commit('TEST_NUM', [0, 0, 0, 2, 0, 0]);
        //     for (mec in likemec) {
        //         commit('LIKE_GAME_ID', mec.value.id)
        //     }
        // }
    }
}