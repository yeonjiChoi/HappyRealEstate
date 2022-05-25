<template>
  <b-container>
    <b-row>
      <b-col>
        <h4>
          <b-icon icon="person-circle" />
          로그인
        </h4>
      </b-col>
    </b-row>
    <b-row class="justify-content-center text-center">
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center p-2 mt-3" align="left">
          <b-form class="text-left">
            <b-alert show variant="info" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >

            <b-input-group prepend="아이디" class="mt-3">
              <b-form-input
                id="userid"
                v-model="member.userId"
                required
                placeholder="아이디를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-input-group>

            <b-input-group prepend="비밀번호" class="mt-3">
              <b-form-input
                type="password"
                id="userpwd"
                class="userpwd-item"
                v-model="member.userPwd"
                required
                placeholder="비밀번호를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-input-group>

            <b-row class="text-center mt-3">
              <b-col>
                <b-button
                  type="button"
                  variant="outline-info"
                  size="sm"
                  class="m-1"
                  @click="confirm"
                  >로그인</b-button
                >

                <b-button
                  type="button"
                  variant="outline-success"
                  class="m-1"
                  size="sm"
                  @click="movePage"
                  >회원가입</b-button
                >
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      member: {
        userId: null,
        userPwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.member);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "register" });
    },
  },
};
</script>

<style scoped>
.userpwd-item {
  font-family: Arial;
}
</style>
