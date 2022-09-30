import api from "@/api/api";
import axios from "axios";
// import router from "@/router";

export default {
  namespaced: true,

  state: () => ({
    detail: {},
    mainGames: [],
    subGames: [],
    presentType: "",
  }),
  getters: {},
  mutations: {
    SET_DETAIL: (state, details) => (state.detail = details),
    SET_MAIN_GAMES: (state, games) => state.mainGames.push(games),
    SET_SUB_GAMES: (state, games) => state.subGames.push(games),
    SET_TYPE: (state, type) => (state.presentType = type),
    RESET_GAMES: (state) => (state.mainGames = []),
    RESET_SUB_GAMES: (state) => (state.subGames = []),
  },
  actions: {
    getDetails({ commit }, gameId) {
      axios({
        url: api.games.detailEdit(gameId),
        method: "get",
      })
        .then((res) => {
          console.log("성공");
          commit("SET_DETAIL", res.data);
        })
        .catch((err) => console.log(err));
    },
    changeMainGames({ commit, dispatch }, type) {
      let url;
      if (type === "리뷰많은순") {
        url = api.games.mostReviewd10();
      } else if (type === "TOP10") {
        url = api.games.mostRating10();
      } else if (type === "최신게임") {
        url = api.games.mostRecent10();
      }
      axios({
        url: url,
        method: "get",
      })
        .then((res) => {
          let tmp = [];
          for (let r of res.data.responses) {
            tmp.push(r.gameId);
          }
          let kind = "main";
          let params = {
            gameNums: tmp,
            kind: kind,
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
          let kind = "sub";
          let params = {
            gameNums: tmp,
            kind: kind,
          };
          dispatch("registGameDetails", params);
        })
        .catch((err) => console.log(err));
    },

    registGameDetails({ commit }, params) {
      for (let gameNum of params.gameNums) {
        axios({
          url: api.games.detailEdit(gameNum),
          method: "get",
        })
          .then((res) => {
            let data = res.data;
            let details = {
              gameId: data.id,
              title_kr: data.titleKr,
              thumbUrl: data.thumbUrl,
              rating: data.ratingUser,
              players: `${data.minPlayers} ~ ${data.maxPlayers}인`,
              times: data.playingTime,
              level: data.difficulty,
            };
            if (params.kind === "main") {
              commit("SET_MAIN_GAMES", details);
            } else {
              commit("SET_SUB_GAMES", details);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    resetMainGames({ commit }) {
      commit("RESET_GAMES");
    },
    resetSubGames({ commit }) {
      commit("RESET_SUB_GAMES");
    },
  },
};
