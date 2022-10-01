import api from "@/api/api";
import axios from "axios";

export default {
    namespaced: true,
    state: () => ({
        token: localStorage.getItem("token"),
        nickName: '',
        kakaoId: '',
        bookmark: [],
        history: []
    }),
    getters: {
        authHeader: (state) => ({
            Authorization: `Bearer ${state.token}`,
            "Content-type": "Application/JSON",
        }),
    },
    mutations: {
        SET_NICKNAME: (state, nickName) => (state.nickName = nickName),
        SET_KAKAOID: (state, kakaoId) => (state.kakaoId = kakaoId),
        SET_BOOKMARK: (state, bookmarkResponses) => (state.bookmark = bookmarkResponses),
        SET_HISTORY: (state, historyResponses) => (state.history = historyResponses),
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
        }
    }
}