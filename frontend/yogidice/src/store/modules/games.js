import api from "@/api/api";
import axios from "axios";
// import router from "@/router";

export default {
  namespaced: true,

  state: () => ({
    detail: {},
    mainGames: [],
    subGames: [],
    smallGames: [],
    longGames: [],
    searchResult: [],
    presentType: "",
    selectedGames: [],
    penalty: ["가", "나", "다", "라", "마", "바"],
  }),
  getters: {
    getAuthHeader: (state, getters, rootState, rootGetters) =>
      rootGetters["user/authHeader"],
  },
  mutations: {
    SET_DETAIL: (state, details) => (state.detail = details),
    ADD_MAIN_GAMES: (state, games) => state.mainGames.push(games),
    SET_MAIN_GAMES: (state, games) => (state.mainGames = games),
    SET_SUB_GAMES: (state, games) => state.subGames.push(games),
    SET_SMALL_GAMES: (state, games) => (state.smallGames = games),
    ADD_SMALL_GAMES: (state, games) => state.smallGames.push(...games),
    SET_SEARCH_RESULT: (state, result) => {
      state.searchResult = result;
    },
    SET_LONG_GAMES: (state, games) => (state.longGames = games),
    ADD_LONG_GAMES: (state, games) => state.longGames.push(games),
    SET_TYPE: (state, type) => (state.presentType = type),
    RESET_GAMES: (state) => (state.mainGames = []),
    RESET_SUB_GAMES: (state) => (state.subGames = []),
    RESET_SMALL_GAMES: (state) => (state.smallGames = []),
    RESET_LONG_GAMES: (state) => (state.longGames = []),
    ADD_SELECTED_GAMES: (state, gameId) => state.selectedGames.push(gameId),
    REMOVE_SELECTED_GAMES: (state, gameId) => {
      state.selectedGames.splice(state.selectedGames.indexOf(gameId), 1);
    },
  },
  actions: {
    getDetails({ commit }, gameId) {
      axios({
        url: api.games.detailEdit(gameId),
        method: "get",
      })
        .then((res) => {
          console.log("성공", res.data);
          commit("SET_DETAIL", res.data);
        })
        .catch((err) => console.log(err));
    },
    changeMainGames({ commit, dispatch, getters }, type) {
      let url;
      if (type === "리뷰많은순") {
        url = api.games.mostReviewd10();
      } else if (type === "평점순") {
        url = api.games.mostRating10();
      } else if (type === "최신게임") {
        url = api.games.mostRecent10();
      }
      axios({
        url: url,
        method: "get",
        headers: getters.getAuthHeader,
      })
        .then((res) => {
          console.log(res.data);
          let tmp = [];
          for (let r of res.data.responses) {
            tmp.push(r.gameId);
          }
          let params = {
            gameNums: tmp,
            kind: "main",
          };
          dispatch("registGameDetails", params);
          commit("SET_TYPE", type);
        })
        .catch((err) => console.log(err));
    },
    changeSubGames({ dispatch }, gameId) {
      axios({
        url: api.games.extendedGames(gameId),
        method: "get",
      })
        .then((res) => {
          let tmp = [];
          for (let r of res.data.responses) {
            tmp.push(r.id);
          }
          let params = {
            gameNums: tmp,
            kind: "sub",
          };
          dispatch("registGameDetails", params);
        })
        .catch(() => {
          let params = {
            gameNums: [],
            kind: "sub",
          };
          dispatch("registGameDetails", params);
        });
    },
    changeSmallGames({ commit }, payload) {
      // 선호도 조사면 smallGames를 전체로 바꿔야되는데...
      if (payload.type === "선호도조사") {
        axios({
          url: api.games.getCreate(),
          method: "get",
          params: {
            page: payload.page,
            size: 15,
          },
        })
          .then((res) => {
            commit("ADD_SMALL_GAMES", res.data.responses);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    changeLongGames({ dispatch }, payload) {
      let url;
      if (payload.type === "리뷰많은순") {
        url = api.games.sortReview();
      } else if (payload.type === "평점순") {
        url = api.games.sortRating();
      } else if (payload.type === "최신게임") {
        url = api.games.sortRecent();
      }
      axios({
        url: url,
        method: "get",
        params: {
          page: payload.page,
          size: 30,
        },
      })
        .then((res) => {
          let tmp = [];
          for (let r of res.data.responses) {
            tmp.push(r.gameId);
          }
          let kind = "long";
          let params = {
            gameNums: tmp,
            kind: kind,
          };
          dispatch("registGameDetails", params);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    registGameDetails({ commit }, params) {
      for (let i = 0; i < params.gameNums.length; i++) {
        axios({
          url: api.games.detailEdit(params.gameNums[i]),
          method: "get",
        })
          .then((res) => {
            let data = res.data;
            let details = {
              id: data.id,
              titleKr: data.titleKr,
              thumbUrl: data.thumbUrl,
              ratingUser: data.ratingUser,
              minPlayers: data.minPlayers,
              maxPlayers: data.maxPlayers,
              playingTime: data.playingTime,
              difficulty: data.difficulty,
            };
            if (params.kind === "main") {
              commit("SET_MAIN_GAMES", details);
            } else if (params.kind === "long") {
              commit("ADD_LONG_GAMES", details);
            } else {
              commit("SET_SUB_GAMES", details);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    searchGames({ commit }, info) {
      return axios({
        url: api.games.searchGame(info.gameTitle),
        method: "get",
      })
        .then((res) => {
          if (info.searchLoc === "선호도조사") {
            commit("SET_SEARCH_RESULT", res.data.responses);
          } else {
            commit("SET_LONG_GAMES", res.data.responses);
          }
        })
        .catch(() => {
          if (info.searchLoc === "선호도조사") {
            commit("SET_SEARCH_RESULT", []);
          } else {
            commit("SET_LONG_GAMES", []);
          }
        });
    },
    filteringGames({ commit }, answers) {
      commit("RESET_GAMES");
      axios({
        url: api.games.filtering(),
        method: "post",
        data: answers,
      })
        .then((res) => {
          commit("SET_MAIN_GAMES", res.data.responses);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getDetailRecommend({ commit }, gameId) {
      axios({
        url: api.games.detailRecommend(gameId),
        method: "get",
      })
        .then((res) => {
          commit("SET_MAIN_GAMES", res.data.responses);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMainRecommend({ commit, getters }) {
      axios({
        url: api.games.mainRecommend(),
        method: "get",
        headers: getters.getAuthHeader,
      })
        .then((res) => {
          commit("SET_MAIN_GAMES", res.data.responses);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addSelectedGames({ commit }, gameId) {
      commit("ADD_SELECTED_GAMES", gameId);
    },
    removeSelectedGames({ commit }, gameId) {
      commit("REMOVE_SELECTED_GAMES", gameId);
    },
    resetMainGames({ commit }) {
      commit("RESET_GAMES");
    },
    resetSmallGames({ commit }) {
      commit("RESET_SMALL_GAMES");
    },
    resetSubGames({ commit }) {
      commit("RESET_SUB_GAMES");
    },
    resetLongGames({ commit }) {
      commit("RESET_LONG_GAMES");
    },
  },
};
