<template>
  <b-container id="memberView" class="bv-example-row p-3">
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "MemberView",
};
</script>

<style>
#memberView {
  height: 800px;
}
</style>
