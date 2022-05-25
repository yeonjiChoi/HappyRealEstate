<template>
  <b-container class="mt-3">
    <div>
      <h5><b>뉴스</b></h5>
    </div>
    <div class="mt-3">
      <b-table
        thead-class="d-none"
        hover
        :per-page="8"
        :items="newsList"
        :fields="fields"
        @row-clicked="moveToNews"
      >
        <template #cell(제목)="data">
          <span v-html="data.item.title"></span>
        </template>
      </b-table>
    </div>
  </b-container>
</template>

<script>
import { getNewsList } from "@/api/news.js";

export default {
  data() {
    return {
      newsList: [],
      fields: ["제목", "링크"],
    };
  },
  created() {
    this.getNews("");
  },
  methods: {
    getNews(keyword) {
      const params = { searchval: keyword + " 부동산" };
      getNewsList(
        params,
        ({ data }) => {
          this.newsList = data;
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
    },
    moveToNews(news) {
      window.open(news.originallink, "_blank");
    },
  },
};
</script>
<style scoped>
* {
  color: #7d7d7d;
}
</style>
