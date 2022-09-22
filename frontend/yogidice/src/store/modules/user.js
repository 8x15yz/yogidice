import axios from "axios";
import router from "@/router";

export default {
  namespaced: true,
  state: () => ({
    token: localStorage.getItem("token") || "",
    currentUser: {},
  }),
  getters: {
    isLogginedIn: (state, _, rootState) =>
      !!state.token || !!rootState.company.token,
    authHeader: (state) => ({
      Authorization: `Bearer ${state.token}`,
      "Content-type": "Application/JSON",
    }),
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
  },
  actions: {
    saveToken({ commit }, token) {
      commit("SET_TOKEN", token);
      localStorage.setItem("token", token);
    },

    async removeToken({ commit }) {
      await commit("SET_TOKEN", "");
      await commit("SET_CURRENT_USER", {});
      localStorage.setItem("token", "");
    },

    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLogginedIn) {
        axios({
          url: "http://j7b206.p.ssafy.io/api/users/",
          method: "get",
          headers: getters.authHeader,
        })
          .then((res) => {
            commit("SET_CURRENT_USER", res.data);
          })
          .catch((err) => {
            if (err.response.status === 401) {
              dispatch("removeToken");
              router.push({ name: "Login" });
            }
          });
      }
    },

    getKakaoUserInfo({ dispatch }, code) {
      axios
        .get("http://j7b206.p.ssafy.io/api/users/callback?code=" + code)
        .then((res) => {
          dispatch("checkUser", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    checkUser({ dispatch }, userInfo) {
      axios({
        url: "http://j7b206.p.ssafy.io/api/users/check",
        method: "post",
        data: userInfo,
      })
        .then((res) => {
          if (res.data.registed) {
            dispatch("kakaoLogin", userInfo);
          } else {
            router.push({ name: "BerChart" });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    kakaoRegist({ dispatch }, formData) {
      axios({
        url: "http://j7b206.p.ssafy.io/api/users/regist",
        method: "post",
        data: JSON.stringify(formData),
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({ name: "home" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    kakaoLogin({ dispatch }, userInfo) {
      axios({
        url: "http://j7b206.p.ssafy.io/api/users/login",
        method: "post",
        data: userInfo,
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({
            name: "NewUserResearch",
            params: { nickName: userInfo.nickName },
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
