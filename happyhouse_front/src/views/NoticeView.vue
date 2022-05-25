<template>
  <b-container id="noticeView" class="p-3">
    <h4><b-icon icon="megaphone" /> NOTICE</h4>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "noticeView",
};
</script>

<style>
#noticeView {
  height: 800px;
}
</style>
