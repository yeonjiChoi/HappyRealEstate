<template>
  <div>
    <header class="navbar navbar-light bg-light">
      <b-container>
        <b-row>
          <div class="col-md-8">
            <router-link to="/">
              <img
                src="@/assets/happyhouse.png"
                alt="logo"
                class="navbar-brand"
              />
            </router-link>
          </div>
        </b-row>
        <b-col>
          <div v-if="userInfo">
            <b-button class="btn float-right" @click.prevent="onClickLogout"
              >LogOut</b-button
            >
            <div class="float-right">
              <router-link :to="{ name: 'memberInfo' }">MyPage</router-link>
            </div>
          </div>
          <div v-else>
            <b-button class="btn float-right">
              <router-link :to="{ name: 'login' }">Login</router-link>
            </b-button>
            <b-button class="btn float-right">
              <router-link :to="{ name: 'register' }"> Sign UP </router-link>
            </b-button>
          </div>
        </b-col>
      </b-container>
      <b-container>
        <b-row class="benter-block">
          <div>
            <ul class="nav">
              <li class="nav-item">
                <router-link :to="{ name: 'notice' }" class="link"
                  >공지사항</router-link
                >
              </li>
              <li class="nav-item">
                <router-link :to="{ name: 'QnA' }">QnA</router-link>
              </li>
              <li class="nav-item">
                <router-link :to="{ name: 'House' }"> 매물 정보 </router-link>
              </li>
              <li class="nav-item">
                <router-link :to="{ name: 'InterestHouse' }"
                  >관심 매물</router-link
                >
              </li>
              <li class="nav-item">
                <router-link :to="{ name: 'InterestArea' }"
                  >관심 지역</router-link
                >
              </li>
              <li class="nav-item">
                <router-link :to="{ name: 'News' }">뉴스</router-link>
              </li>
            </ul>
          </div>
        </b-row>
      </b-container>
    </header>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNav",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
.navbar-brand {
  height: 50px;
  width: 50px;
}
.nav-item {
  color: black;
  padding: 5px;
}
</style>
