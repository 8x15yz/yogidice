import axios from "axios";
import router from "@/router";
import api from "@/api/api.js";

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
    registNickName({ commit, getters }, newNickName) {
      axios({
        url: api.users.regist(),
        method: "put",
        headers: getters.authHeader,
        data: newNickName,
      })
        .then(() => {
          commit("SET_CURRENT_USER", newNickName);
          alert("닉네임이 성공적으로 변경되었습니다!");
          router.push({ name: "InitChoice" });
        })
        .catch(() => alert("닉네임을 변경하지 못했습니다."));
    },
    registBookMark({ getters, dispatch }, gameList) {
      axios({
        url: api.users.bookmark(),
        method: "post",
        headers: getters.authHeader,
        data: gameList,
      })
        .then(() => {
          dispatch(
            "modal/registModal",
            {
              info: {},
              from: "",
              header: "",
              body: "성공적으로 등록되었습니다!",
              footer1: "",
              footer2: "",
            },
            { root: true },
          );
          setTimeout(function () {
            router.push({
              name: "MainPage",
            });
          }, 2000);
        })
        .catch(() => {
          dispatch(
            "modal/registModal",
            {
              info: {},
              from: "",
              header: "",
              body: "등록에 실패하였습니다!",
              footer1: "",
              footer2: "",
            },
            { root: true },
          );
          setTimeout(function () {
            dispatch("modal/closeModal", null, { root: true });
          }, 2000);
        });
    },
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
          url: api.users.get(),
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
        .get(`${api.users.callback()}?code=${code}`)
        .then((res) => {
          dispatch("checkUser", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    checkUser({ dispatch }, userInfo) {
      axios({
        url: api.users.check(),
        method: "post",
        data: userInfo,
      })
        .then((res) => {
          if (res.data.registed) {
            dispatch("kakaoLogin", userInfo);
          } else {
            router.push({
              name: "RegistNickName",
              params: { nickName: userInfo.nickName, kakaoId: userInfo.kakaoId },
            });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    kakaoRegist({ dispatch }, formData) {
      axios({
        url: api.users.regist(),
        method: "post",
        data: formData,
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          dispatch("modal/closeModal",null,{ root:true });
          router.push({ name: "InitChoice" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    kakaoLogin({ dispatch }, userInfo) {
      axios({
        url: api.users.login(),
        method: "post",
        data: userInfo,
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({ name: "MainPage" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
