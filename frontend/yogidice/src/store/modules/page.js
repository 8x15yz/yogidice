export default {
  state: () => ({
    signUpPage: true,
    nickNamePage: false,
  }),
  mutations: {
    changeSignUpPage (state) {
      state.signUpPage = !state.signUpPage
    },
    changeNickNamePage (state) {
      state.nickNamePage = !state.nickNamePage
    }

  }

}