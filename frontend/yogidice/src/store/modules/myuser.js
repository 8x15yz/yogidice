import api from "@/api/api";
import axios from "axios";

export default {
  namespaced: true,
  state: () => ({
    token: localStorage.getItem("token"),
    nickName: "",
    kakaoId: "",
    bookmark: [],
    history: [],
    // likeGameId: [],
    likeMecha: [],
    likePMec: [0, 0, 0, 0, 0, 0],
    likePMecMax: "보드게임",
    rearrangemeca: [],
    userreview: 0,
  }),
  getters: {
    authHeader: (state) => ({
      Authorization: `Bearer ${state.token}`,
      "Content-type": "Application/JSON",
    }),
    likemec: (state) => state.bookmark,
    token: (state) => state.token,
  },
  mutations: {
    SET_NICKNAME: (state, nickName) => (state.nickName = nickName),
    SET_KAKAOID: (state, kakaoId) => (state.kakaoId = kakaoId),
    SET_BOOKMARK: (state, bookmarkResponses) =>
      (state.bookmark = bookmarkResponses),
    SET_HISTORY: (state, historyResponses) =>
      (state.history = historyResponses),
    LIKE_MECHA: (state, GameMec) => state.likeMecha.push(GameMec),
    LIKE_P_MEC: (state, gid) => (state.likePMec[gid] += 1),
    LIKE_P_MEC_RESET: (state) => (state.likePMec = [0, 0, 0, 0, 0, 0]),
    USER_REVIEW: (state) => (state.userreview += 1),
    USER_REVIEW_RESET: (state) => (state.userreview = 0),
    LIKE_P_MEC_MAX: (state, gname) => (state.likePMecMax = gname),
    CHEMI: (state, chemi) => (state.chemi = chemi),
  },
  actions: {
    GetUserInfo({ getters, commit }) {
      axios({
        url: api.users.get(),
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_NICKNAME", res.data.nickName);
          commit("SET_KAKAOID", res.data.kakaoId);
          commit("SET_BOOKMARK", res.data.bookmarkResponses);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    GetUserHistory({ getters, commit }) {
      commit("LIKE_P_MEC_RESET");
      commit("USER_REVIEW_RESET");
      axios({
        url: api.users.history(),
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_HISTORY", res.data.responses);
          // console.log('dspd', res.data.responses)
          let pmec = [0, 0, 0, 0, 0, 0];
          const pmecmax = [
            "논리",
            "경제",
            "파티",
            "룰",
            "말",
            "전략",
          ];
          for (let hisgame of res.data.responses) {
            if (hisgame.review != null) {
              commit("USER_REVIEW");
            }
            for (let mecha of hisgame.mechanismGroupResponses) {
              commit("LIKE_MECHA", [mecha.mechanismName, mecha.parentsMec]);
              if (mecha.parentsMec == "추리카드퍼즐") {
                commit("LIKE_P_MEC", 0);
                pmec[0] += 1;
              } else if (mecha.parentsMec == "경제") {
                commit("LIKE_P_MEC", 1);
                pmec[1] += 1;
              } else if (mecha.parentsMec == "파티") {
                commit("LIKE_P_MEC", 2);
                pmec[2] += 1;
              } else if (mecha.parentsMec == "조건") {
                commit("LIKE_P_MEC", 3);
                pmec[3] += 1;
              } else if (mecha.parentsMec == "말") {
                commit("LIKE_P_MEC", 4);
                pmec[4] += 1;
              } else if (mecha.parentsMec == "전략") {
                commit("LIKE_P_MEC", 5);
                pmec[5] += 1;
              }
            }
          }
          commit("LIKE_P_MEC_MAX", pmecmax[pmec.indexOf(Math.max(...pmec))]);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    SendReview({ getters }, reviewdata) {
      axios({
        url: `https://yogidice.site/api/users/history/${reviewdata[1]}`,
        method: "put",
        headers: getters.authHeader,
        data: reviewdata[0],
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    userAndGame({ commit, getters }, gameId) {
      axios({
        url: api.gameInfo.userandgame(gameId),
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("CHEMI", res.data.response);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
