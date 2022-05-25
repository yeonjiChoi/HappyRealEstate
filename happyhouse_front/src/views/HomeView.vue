<template>
  <div id="app">
    <main-content></main-content>
    <router-view />
  </div>
</template>

<script>
import MainContent from "@/components/MainContent.vue";

export default {
  components: { MainContent },
  comments: { MainContent },
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
