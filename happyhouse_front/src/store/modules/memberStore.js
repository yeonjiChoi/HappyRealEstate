import jwt_decode from "jwt-decode";
import { login, findById } from "@/api/member.js";

const memberStore = {
  namespace: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async memberConfirm({ commit }, member) {
      await login(member, (response) => {
        if (response.data.messeage === "success") {
          let token = response.data["access-token"];
          commit("SET_IS_LOGIN", true);
          commit("SET_IS_LOGIN_ERROR", false);
          sessionStorage.setItem("access-token", token);
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", true);
        }
      });
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userId,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("NO User Info");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default memberStore;
