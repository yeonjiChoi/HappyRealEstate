<template>
  <b-container class="container mt-3">
    <h4><b-icon icon="newspaper" /> News</h4>
    <news-list></news-list>
  </b-container>
</template>

<script>
import NewsList from "@/components/News/NewsList.vue";
export default {
  components: {
    NewsList,
  },
};
</script>

<style scoped></style>
