<template>
  <Doughnut
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :width="width"
    :height="height"
  />
</template>

<script>
import { Doughnut } from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  ArcElement,
  CategoryScale,
} from "chart.js";

ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale);
import { getChartData } from "@/api/chart.js";

export default {
  components: {
    Doughnut,
  },
  props: {
    chartId: {
      type: String,
      default: "doughnut-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
  },
  data() {
    return {
      dongList: [],
      countList: [],
      bgColor: [
        "#D6EAF8",
        "#AED6F1",
        "#85C1E9",
        "#5DADE2",
        "#3498DB",
        "#2E86C1",
        "#2874A6",
        "#21618C",
        "#1B4F72",
        "#184665",
      ],
      chartData: {},
      // chartData: {
      //   labels: [],
      //   datasets: [
      //     {
      //       backgroundColor: ["#41B883", "#E46651", "#00D8FF", "#DD1B16"],
      //       data: [],
      //     },
      //   ],
      // },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  created() {
    this.getChart();
  },
  methods: {
    getChart() {
      getChartData(
        ({ data }) => {
          for (let i = 0; i < data.length; i++) {
            if (i == 10) break;
            this.dongList.push(data[i].dongName);
            this.countList.push(data[i].count);
            // console.log(this.makeColor());
            // this.bgColor.push(this.makeColor());
          }
          this.chartData = {
            labels: this.dongList,
            datasets: [
              {
                backgroundColor: this.bgColor,
                data: this.countList,
              },
            ],
          };
        },
        (error) => {
          console.log(error);
        },
      );
    },
    makeColor() {
      return "#11C" + Math.floor(Math.random() * 1000);
    },
  },
};
</script>

<style></style>
