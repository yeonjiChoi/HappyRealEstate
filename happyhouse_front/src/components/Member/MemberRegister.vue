<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col>
        <h4><b-icon icon="person-square" /> Register</h4>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" align="left">
          <b-form class="text-left" @submit="onSubmit">
            <b-input-group prepend="아이디" class="mt-3">
              <b-form-input
                id="userid"
                required
                placeholder="아이디를 입력하세요"
                v-model="member.userId"
              ></b-form-input>
              <b-input-group-append>
                <b-button variant="outline-secondary" @click="idCheck"
                  >아이디 체크</b-button
                >
              </b-input-group-append>
            </b-input-group>

            <b-input-group prepend="비밀번호" class="mt-3" style="color: black">
              <b-form-input
                type="password"
                id="userpwd"
                class="userpwd-item"
                required
                placeholder="비밀번호를 입력하세요"
                v-model="member.userPwd"
              ></b-form-input>
            </b-input-group>

            <b-input-group prepend="비밀번호 확인" class="mt-3">
              <b-form-input
                type="password"
                class="userpwd-item"
                required
                placeholder="비밀번호를 다시 입력하세요"
                v-model="pwdForCheck"
                v-on:keyup="checkPwd"
              ></b-form-input>
            </b-input-group>
            <b-row class="text-right">
              <b-col>
                <div>비밀번호 일치 여부 [{{ pwdChecking }}]</div>
              </b-col>
            </b-row>

            <b-input-group prepend="이메일" class="mt-3">
              <b-form-input
                type="email"
                id="email"
                required
                placeholder="이메일을 입력하세요"
                v-model="member.email"
              ></b-form-input>
            </b-input-group>

            <b-input-group prepend="이름" class="mt-3">
              <b-form-input
                type="text"
                id="userName"
                required
                placeholder="이름을 입력하세요"
                v-model="member.userName"
              ></b-form-input>
            </b-input-group>
            <b-row class="text-right mt-3">
              <b-col>
                <b-button type="submit">회원가입</b-button>
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
import { MemberRegist, checkId } from "@/api/member.js";

export default {
  name: "memberRegist",
  data() {
    return {
      member: {
        userId: "",
        userPwd: "",
        userName: "",
        eamil: "",
        authority: "",
        joinDate: "",
      },
      pwdForCheck: "",
      pwdChecking: "",
      isIdPossible: false,
      isPwdPossible: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {},
  created() {},
  methods: {
    idCheck(event) {
      event.preventDefault();
      if (this.member.userId.length < 5 || this.member.userId.length > 12) {
        alert("아이디는 5자 이상 12자리 이하로 입력해주세요");
      } else {
        checkId(this.member.userId, ({ data }) => {
          let msg = "사용할 수 없는 아이디입니다.";
          if (data === "success") {
            this.isIdCheck = true;
            this.isIdPossible = true;
            msg = "사용 가능한 아이디입니다.";
            alert(msg);
          } else {
            this.isIdPossible = false;
            alert(msg);
          }
        });
      }
    },
    checkPwd(event) {
      event.preventDefault();
      if (this.member.userPwd === this.pwdForCheck) {
        this.pwdChecking = "일치";
        this.isPwdPossible = true;
      } else {
        this.pwdChecking = "비일치";
        this.isPwdPossible = false;
      }
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.member.userId &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.member.userid.focus());
      err &&
        !this.member.userPwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.member.userPwd.focus());
      err &&
        !this.member.userName &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.member.userName.focus());
      err &&
        !this.member.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.member.email.focus());

      if (this.isIdPossible == false) {
        alert("아이디 중복 미체크 또는 이미 존재하는 아이디입니다.");
      }
      if (this.isPwdPossible == false) {
        alert("비밀번호를 다시 확인해주세요.");
      }
      if (!err) {
        alert(msg);
      } else {
        this.MemberRegist();
      }
    },
    MemberRegist() {
      MemberRegist(
        {
          userId: this.member.userId,
          userPwd: this.member.userPwd,
          userName: this.member.userName,
          email: this.member.email,
        },
        ({ data }) => {
          let msg = "회원가입 중 오류가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입에 성공하였습니다.";
          }
          alert(msg);
          this.moveLogin();
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
    },
    moveLogin() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
.userpwd-item {
  font-family: Arial;
}
</style>
