<template>
  <b-container>
    <div>부동산 관련 뉴스 20개를 제공합니다.</div>

    <div v-if="userInfo">
      <b-list-group horizontal="md">
        <b-list-group-item
          v-for="(area, index) in interestAreas"
          :key="index"
          @click="getNews(area.dongName)"
          >{{ area.dongName }}</b-list-group-item
        >
      </b-list-group>
    </div>

    <news-list-item
      v-for="(news, index) in newsList"
      :key="index"
      :news="news"
    />
  </b-container>
</template>

<script>
import { getNewsList } from "@/api/news.js";
import NewsListItem from "@/components/News/NewsListItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
const interestStore = "interestStore";

export default {
  data() {
    return {
      newsList: [],
    };
  },
  created() {
    this.getNews("");
  },
  components: {
    NewsListItem,
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
        },
      );
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(interestStore, ["interestAreas"]),
  },
};
</script>

<style></style>
