const HOST = "http://localhost:8081/api";

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
    deleteBookMark: (gameId) => HOST + user + `/bookmark/${gameId}`,
    history: () => HOST + user + "/history",
    historyControll: (historyId) => HOST + user + "/history" + `/${historyId}`,
    review: () => HOST + user + "/review",
  },

  games: {
    getCreate: () => HOST + game,
    searchGame: (gameTitle) => HOST + game + "/search" + `/${gameTitle}`,
    detailEdit: (gameId) => HOST + game + `/${gameId}`,
    extendedGames: (gameId) => HOST + game + "/extend" + `/${gameId}`,
    mostReviewd10: () => HOST + game + "/top10/review",
    mostRecent10: () => HOST + game + "/top10/recent",
    mostRating10: () => HOST + game + "/top10/rating",
    sortRating: () => HOST + game + "/rating",
    sortReview: () => HOST + game + "/review",
    sortRecent: () => HOST + game + "/recent",
    filtering: () => HOST + game + "/recommend",
    mainRecommend: () => HOST + game + "/recommend/main",
    detailRecommend: (gameId) => HOST + game + "/detail" + `/${gameId}`
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
