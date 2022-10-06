<template>
  <div class="radar-ch">
    <div class="radar-ch-inner">
      <Radar
        :chart-options="chartOptions"
        :chart-data="chartData"
        style="height: 300px; width: 300px"
      />
    </div>
  </div>
</template>

<style scoped>
.radar-ch {
  height: 330px;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: yellow; */
}
.radar-ch-inner {
  height: 320px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f3f3f3;
}
</style>

<script>
import { Radar } from "vue-chartjs";
import { reactive } from "vue";
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
  LineElement,
);

export default {
  name: "RadarChart",
  components: {
    Radar,
  },
  props: {
    lengamecategory: Array,
  },
  setup(props) {
    // let lcmax = Math.max(...props.lengamecategory)
    let lengamecategoryten = reactive(props.lengamecategory);
    // const lenten = lengamecategoryten.map( x => x/Math.max(...lengamecategoryten)*10)
    const chartData = {
      labels: ["논리", "경제", "파티", "룰", "말", "전략"],
      datasets: [
        {
          borderColor: "#71C9CE",
          pointBackgroundColor: "#71C9CE",
          data: lengamecategoryten,
        },
      ],
    };
    const chartOptions = {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
    };
    return {
      chartData,
      chartOptions,
    };
  },
};
</script>
