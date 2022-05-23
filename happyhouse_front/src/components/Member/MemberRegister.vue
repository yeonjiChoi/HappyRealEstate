<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>Register</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" align="left">
          <b-form class="text-left" @submit="onSubmit">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                required
                placeholder="아이디 입력...."
                v-model="member.userId"
              ></b-form-input>
              <b-button @click="idCheck">아이디 체크</b-button>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                required
                placeholder="비밀번호 입력...."
                v-model="member.userPwd"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호 확인:">
              <b-form-input
                type="password"
                required
                placeholder="비밀번호 입력...."
                v-model="pwdForCheck"
                v-on:keyup="checkPwd"
              ></b-form-input>
              <div>{{ pwdChecking }}</div>
            </b-form-group>
            <b-form-group label="Email:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                required
                placeholder="Email 입력...."
                v-model="member.email"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="userName">
              <b-form-input
                type="text"
                id="userName"
                required
                placeholder="이름 입력...."
                v-model="member.userName"
              ></b-form-input>
            </b-form-group>
            <b-button type="submit">회원가입</b-button>
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
      pwdChecking: "비밀번호가 일치하지 않습니다.",
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
        alert("아이디는 5자 이상 12자리 이하");
      } else {
        checkId(this.member.userId, ({ data }) => {
          let msg = "사용할 수 없는 ID";
          if (data === "success") {
            msg = "사용 가능한 ID";
            alert(msg);
          } else {
            alert(msg);
          }
        });
      }
    },
    checkPwd(event) {
      event.preventDefault();
      if (this.member.userPwd === this.pwdForCheck) {
        this.pwdChecking = "비밀번호 일치!";
      } else {
        this.pwdChecking = "비밀번호가 일치하지 않습니다.";
      }
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.member.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.member.userid.focus());
      err &&
        !this.member.userPwd &&
        ((msg = "비밀번호 입력해주세요"),
        (err = false),
        this.$refs.member.userPwd.focus());
      err &&
        !this.member.userName &&
        ((msg = "이름 입력해주세요"),
        (err = false),
        this.$refs.member.userName.focus());
      err &&
        !this.member.email &&
        ((msg = "email 입력해주세요"),
        (err = false),
        this.$refs.member.email.focus());

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
          let msg = "회원 가입 오류";
          if (data === "success") {
            msg = "회원 가입 완료";
          }
          alert(msg);
          this.moveLogin();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveLogin() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>
