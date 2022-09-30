const HOST = "https://j7b206.p.ssafy.io/api";

const user = "/users";
const game = "/games";
const bgg = "/bggdatas";
const cafe = "/cafes";
const cafeGames = "/cafegamelists";

export default {
  users: {
    get: () => HOST + user,
    login: () => HOST + user + "/login",
    regist: () => HOST + user + "/regist",
    check: () => HOST + user + "/check",
    callback: () => HOST + user + "/callback",
    bookmark: () => HOST + user + "/bookmark",
    history: () => HOST + user + "/history",
    historyControll: (historyId) => HOST + user + "/history" + `/${historyId}`,
    review: () => HOST + user + "/review",
  },

  games: {
    getCreate: () => HOST + game,
    detailEdit: (gameId) => HOST + game + `/${gameId}`,
    mostReviewd10: () => HOST + game + '/top10/review',
    mostRecent10: () => HOST + game + '/top10/recent',
    mostRating10: () => HOST + game + '/top10/rating',
  },

  bggDats: {
    getCreate: () => HOST + bgg,
    detailEdit: (bggDataId) => HOST + bgg + `/${bggDataId}`,
  },

  cafes: {
    getCreate: () => HOST + cafe,
    detailEdit: (cafeId) => HOST + cafe + `/${cafeId}`,
  },

  cafeGames: {
    create: () => HOST + cafeGames,
    edit: (gameListId) => HOST + cafeGames + `/${gameListId}`,
    getCafe: (cafeId) => HOST + cafeGames + cafe + `/${cafeId}`,
    getGame: (gameId) => HOST + cafeGames + `/game/${gameId}`,
  },
};
