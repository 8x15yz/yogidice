export default {
  state: () => (
    {
      showModal: false,

    }),
  getters: {
   
  },
  mutations: {
    changeModal (state) {
      state.showModal = !state.showModal
    }
   
  }
}