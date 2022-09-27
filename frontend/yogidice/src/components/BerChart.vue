<template>
  <Radar
    :chart-options="chartOptions"
    :chart-data="chartData"
  />
</template>

<script>
import { Radar } from "vue-chartjs";

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement,
  RadialLinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  PointElement,
  RadialLinearScale,
  LineElement
);

export default {
  name: "RadarChart",
  components: {
    Radar,
  },
  props: {
    lengamecategory: Array
  },
  setup(props) {
    const chartData = {
        labels: [
          "추리카드퍼즐",
          "경제",
          "파티",
          "조건",
          "말",
          "전략",
        ],
        datasets: [
          {
            borderColor: "green",
            pointBackgroundColor: "yellow",
            data: props.lengamecategory,
          }
        ],
      }
      const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins:{ legend: { display: false }}
      }
    return {
      chartData, 
      chartOptions
    }
  }
};
</script>
