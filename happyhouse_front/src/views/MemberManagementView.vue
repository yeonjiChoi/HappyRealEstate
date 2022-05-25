<template>
  <div>
    <div class="container">
      <h2>MemberManagement</h2>
      <router-view></router-view>
      <footer-bar></footer-bar>
    </div>
  </div>
</template>

<script>
import FooterBar from "@/components/FooterBar.vue";

export default {
  name: "noticeView",
  components: { FooterBar },
  comments: { FooterBar },
};
</script>

<style></style>
