<template>
  <div>
    <div class="container p-3" id="MemberManagementView">
      <h4><b-icon icon="person-lines-fill" /> 회원 관리</h4>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "noticeView",
};
</script>

<style>
#MemberManagementView {
  height: 800px;
}
</style>
