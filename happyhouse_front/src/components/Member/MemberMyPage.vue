<template>
  <b-container v-if="userInfo">
    <b-row>
      <b-col>
        <h4><b-icon icon="file-person" /> MyPage</h4>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-container id="userInfoForm" class="mt-2">
          <b-row>
            <b-col></b-col>
            <b-col cols="4" align-self="end">아이디</b-col>|<b-col
              cols="4"
              align-self="start"
              >{{ userInfo.userId }}</b-col
            >
            <b-col></b-col>
          </b-row>
          <b-row class="mt-4">
            <b-col></b-col>
            <b-col cols="4" align-self="end">이름</b-col>|<b-col
              cols="4"
              align-self="start"
              >{{ userInfo.userName }}</b-col
            >
            <b-col></b-col>
          </b-row>
          <b-row class="mt-4">
            <b-col></b-col>
            <b-col cols="4" align-self="end">이메일</b-col>|<b-col
              cols="4"
              align-self="start"
              >{{ userInfo.email }}</b-col
            >
            <b-col></b-col>
          </b-row>
          <b-row class="mt-4">
            <b-col></b-col>
            <b-col cols="4" align-self="end">가입일</b-col>|<b-col
              cols="4"
              align-self="start"
              >{{ userInfo.joinDate }}</b-col
            >
            <b-col></b-col>
          </b-row>
          <b-row class="mt-4">
            <b-col>
              <b-button
                variant="outline-info"
                class="mr-1"
                size="sm"
                @click="moveModifyMember()"
                >정보수정</b-button
              >
              <b-button
                size="sm"
                variant="outline-danger"
                @click="DeleteMember(userInfo.userId)"
                >회원탈퇴</b-button
              >
            </b-col>
          </b-row>
        </b-container>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { MemeberDel } from "@/api/member.js";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    DeleteMember(userId) {
      MemeberDel(
        userId,
        ({ data }) => {
          let msg = "멤버 삭제 오류";
          if (data === "success") {
            msg = "삭제 완료";
          }
          alert(msg);
          this.onClickLogout();
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
    },
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    moveModifyMember() {
      this.$router.push({ name: "modifyMember" });
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
