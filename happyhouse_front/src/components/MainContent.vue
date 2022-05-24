<template>
  <div>
    MainContent
    <div class="container-sm">
      <img src="@/assets/mainHouseImage.jpg" alt="mainhouseImage" />
    </div>
    <b-container>
      <b-row>
        <b-col>NOTICE</b-col>
        <b-col> <doughnut-chart /></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import DoughnutChart from "@/components/DoughnutChart.vue";

export default {
  components: {
    DoughnutChart,
  },
};
</script>

<style>
img {
  width: 100%;
  background-size: cover;
}
</style>
