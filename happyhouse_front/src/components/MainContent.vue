<template>
  <div>
    <div>
      <img src="@/assets/main_happyhouse.png" alt="mainhouseImage" />
    </div>
    <b-container fluid class="mt-3 mb-3">
      <b-row>
        <b-col><main-notice /></b-col>
        <b-col> <doughnut-chart /></b-col>
        <b-col> <main-news /></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import DoughnutChart from "@/components/Main/DoughnutChart.vue";
import MainNotice from "@/components/Main/MainNotice.vue";
import MainNews from "@/components/Main/MainNews.vue";

export default {
  data() {
    return {};
  },
  components: {
    DoughnutChart,
    MainNotice,
    MainNews,
  },
  methods: {},
};
</script>

<style scoped>
img {
  width: 100%;
  background-size: cover;
}
</style>
