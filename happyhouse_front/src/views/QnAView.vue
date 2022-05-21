<template>
  <div>
    <h2>QnA</h2>
    <router-view></router-view>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import FooterBar from "@/components/FooterBar.vue";

export default {
  components: { FooterBar },
  comments: { FooterBar },
};
</script>

<style></style>
