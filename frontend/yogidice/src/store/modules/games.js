import api from "@/api/api"
import axios from "axios";
// import router from "@/router";

export default {
  namespaced: true,

  state: () => ({
    detail: {},
    mainGames: [],
    presentType: '',

  }),
  getters: {
    

  },
  mutations: {
    SET_DETAIL: (state,details) => state.detail = details,
    SET_MAIN_GAMES: (state,games) => state.mainGames.push(games),
    SET_TYPE: (state,type) => state.presentType = type, 
  },
  actions: {
    getDetails ({commit},gameId) {
      axios({
        url: api.games.detailEdit(gameId),
        method: "get"
      }).then((res) => {
        console.log("성공")
        commit("SET_DETAIL",res.data)
      }).catch((err)=>console.log(err))

    },
    changeMainGames({commit,dispatch},type){
      if (type === "리뷰많은순") {
        axios({
          url: api.games.mostReviewd(),
          method: "get",
        })
        .then(res => {
          let tmp = []
          for (let r of res.data.responses) {
            tmp.push(r.gameId)
          }
          dispatch("registGameDetails",tmp)
          commit("SET_TYPE",type)
        })
        .catch(err => console.log(err))
      }
    },

    registGameDetails ({commit},gameNums) {
      for (let gameNum of gameNums) {
        axios({
          url: api.games.detailEdit(gameNum),
          method: "get"
        }).then((res) => {
          let data = res.data
          let details = 
            {
              "gameId": data.id,
              "title_kr": data.titleKr,
              "thumbUrl": data.thumbUrl,
              "rating": data.ratingUser,
              "players": `${data.minPlayers} ~ ${data.maxPlayers}인`,
              "times": data.playingTime,
              "level": data.difficulty
            }
          commit("SET_MAIN_GAMES",details)
        })
        .catch((err) => {
          console.log(err)
        })
      }

    }
    
  }
}