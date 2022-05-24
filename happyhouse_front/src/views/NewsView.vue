<template>
  <b-container>
    <div>News</div>
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

<style></style>
