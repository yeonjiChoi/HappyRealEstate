<template>
  <b-container v-if="userInfo">
    <b-row>
      <b-col>
        <h4><b-icon icon="person-square" /> 회원정보 수정</h4></b-col
      >
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-container id="userInfoForm" class="mt-2">
          <b-form @submit="onSubmit">
            <b-row>
              <b-col></b-col>
              <b-col cols="4" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userId }}</b-col>
              <b-col></b-col>
            </b-row>

            <b-row class="mt-4">
              <b-col></b-col>
              <b-col cols="4" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start"
                ><b-form-input
                  label="name"
                  type="text"
                  v-model="user.userName"
                  required
                ></b-form-input
              ></b-col>
              <b-col></b-col>
            </b-row>

            <b-row class="mt-4">
              <b-col></b-col>
              <b-col cols="4" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start"
                ><b-form-input
                  label="email"
                  type="email"
                  v-model="user.email"
                  required
                ></b-form-input
              ></b-col>
              <b-col></b-col>
            </b-row>

            <b-row class="mt-4">
              <b-col>
                <b-button
                  variant="outline-success"
                  size="sm"
                  class="mr-1"
                  type="submit"
                  >정보수정</b-button
                >
                <b-button
                  variant="outline-danger"
                  size="sm"
                  @click="moveMyPage()"
                  >취소</b-button
                >
              </b-col>
            </b-row>
          </b-form>
        </b-container>
      </b-col>
      <b-col></b-col>
    </b-row>

    <!-- <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>내 정보 수정</template>
          <hr class="my-4" />

          <b-container class="mt-4">
            <b-form @submit="onSubmit">
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">아이디</b-col
                ><b-col cols="4" align-self="start">{{
                  userInfo.userId
                }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">이름</b-col
                ><b-col cols="6" align-self="start"
                  ><b-form-input
                    label="name"
                    type="text"
                    v-model="user.userName"
                    required
                  ></b-form-input
                ></b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">이메일</b-col
                ><b-col cols="6" align-self="start"
                  ><b-form-input
                    label="email"
                    type="email"
                    v-model="user.email"
                    required
                  ></b-form-input
                ></b-col>
                <b-col cols="2"></b-col>
              </b-row>

              <b-button variant="primary" class="mr-1" type="submit"
                >정보수정</b-button
              >
              <b-button @click="moveMyPage()">취소</b-button>
            </b-form>
          </b-container>
          <hr class="my-4" />
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row> -->
  </b-container>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import { MemberModify } from "@/api/member.js";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {
      user: {
        userId: "",
        userName: "",
        email: "",
        authority: "",
        joinDate: "",
      },
    };
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user.userId = this.userInfo.userId;
    this.user.userName = this.userInfo.userName;
    this.user.email = this.userInfo.email;
    this.user.authority = this.userInfo.authority;
    this.user.joinDate = this.userInfo.joinDate;
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    onSubmit(event) {
      event.preventDefault();
      MemberModify(
        this.user,
        ({ data }) => {
          let msg = "정보 수정 오류";
          if (data === "success") {
            msg = "정보 수정 완료";
          }
          alert(msg);
          this.SET_USER_INFO(this.user);
          //   this.SET_IS_LOGIN(false);
          //   this.SET_USER_INFO(null);
          //   sessionStorage.removeItem("access-token");

          //this.$router.go();
          this.moveMyPage();
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
    },

    moveMyPage() {
      this.$router.push({ name: "memberInfo" });
    },
  },
};
</script>

<style scoped>
#userInfoForm {
  border: 1px solid #ced4da;
  border-radius: 10px;
  padding: 40px;
}
</style>
