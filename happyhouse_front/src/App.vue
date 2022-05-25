<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view />
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import HeaderNav from "@/components/HeaderNav.vue";
import FooterBar from "@/components/FooterBar.vue";

export default {
  components: { HeaderNav, FooterBar },
  comments: { HeaderNav, FooterBar },
};
</script>
<style>
/* Style 수정 ..! */
#app {
  font-family: NanumSquareR, NanumSquareB, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
* {
  font-family: NanumSquareR;
}
a,
a:hover,
a:visited {
  text-decoration: none;
}

@font-face {
  font-family: "NanumSquareR";
  src: url("assets/fonts/NanumSquareR.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "NanumSquareB";
  src: url("assets/fonts/NanumSquareB.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}
</style>
