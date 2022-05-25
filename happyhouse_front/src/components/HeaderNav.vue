<template>
  <div>
    <b-navbar class="navbar" toggleable="lg" variant="light">
      <router-link id="header-title" to="/" class="ml-4">
        HAPPY HOUSE
      </router-link>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item
            ><b-link :to="{ name: 'notice' }" class="nav-link"
              >공지사항</b-link
            ></b-nav-item
          >
          <b-nav-item>
            <b-link :to="{ name: 'QnA' }" class="nav-link"
              >Q&A</b-link
            ></b-nav-item
          >

          <b-nav-item>
            <b-link :to="{ name: 'House' }" class="nav-link">
              거래 정보
            </b-link></b-nav-item
          >
          <b-nav-item>
            <router-link :to="{ name: 'InterestHouse' }" class="nav-link"
              >관심 아파트</router-link
            ></b-nav-item
          >
          <b-nav-item>
            <router-link :to="{ name: 'InterestArea' }" class="nav-link"
              >관심 지역</router-link
            ></b-nav-item
          >
          <b-nav-item>
            <router-link :to="{ name: 'News' }" class="nav-link"
              >뉴스</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto mr-4">
          <b-nav-form v-if="userInfo">
            <b-link :to="{ name: 'memberInfo' }" class="nav-link"
              >My Page</b-link
            >
            <b-link @click.prevent="onClickLogout" class="nav-link"
              >Logout</b-link
            >
          </b-nav-form>
          <b-nav-form v-else>
            <b-link :to="{ name: 'login' }" class="nav-link">Login</b-link>
            <b-link :to="{ name: 'register' }" class="nav-link">
              Sign Up
            </b-link>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
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
.nav-item {
  padding: 5px;
}
#header-title {
  text-decoration: none;
  color: #7c7c7d;
  font-size: 25px;
  font-weight: bold;
}
</style>
