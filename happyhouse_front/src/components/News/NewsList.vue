<template>
  <b-container>
    <div class="mt-2 mb-2">
      <b-row v-if="userInfo">
        <b-col>
          <b-list-group horizontal class="justify-content-center">
            <b-list-group-item
              v-for="(area, index) in interestAreas"
              :key="index"
              @click="getNews(area.dongName)"
              >{{ area.dongName }}</b-list-group-item
            >
          </b-list-group>
        </b-col>
      </b-row>
      <b-row v-else>
        <b-col class="mt-1">
          <b-icon icon="info-circle-fill" /> 로그인 시 추가한 관심 지역에 관련된
          뉴스를 볼 수 있습니다.
        </b-col>
      </b-row>
    </div>
    <div>
      <b-icon icon="info-circle" /> 부동산 관련 뉴스 20개를 제공합니다. 클릭 시
      해당 뉴스로 이동합니다.
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
import { mapActions, mapState } from "vuex";

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
    if (this.userInfo != null) this.getInterArea(this.userInfo.userId);
  },
  components: {
    NewsListItem,
  },
  methods: {
    ...mapActions(interestStore, ["getInterArea"]),
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
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(interestStore, ["interestAreas"]),
  },
};
</script>

<style></style>
