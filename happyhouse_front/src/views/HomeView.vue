<template>
  <div id="app">
    <main-content></main-content>
    <footer-bar></footer-bar>
    <router-view />
  </div>
</template>

<script>
import MainContent from "@/components/MainContent.vue";
import FooterBar from "@/components/FooterBar.vue";

export default {
  components: { MainContent, FooterBar },
  comments: { MainContent, FooterBar },
};
</script>
<style>
/* Style 수정 ..! */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

a,
a:hover,
a:visited {
  text-decoration: none;
}
</style>
