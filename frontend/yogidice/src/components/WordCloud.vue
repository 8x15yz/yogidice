<template>
  <div id="word-cloud"></div>
</template>

<script>
export default {
  data() {
    return {
      words: [
        { text: "Bad", size: 25 },
        { text: "짜증나게", size: 20 },
        { text: "하지마", size: 30 },
        { text: "왘", size: 24 },
        { text: "연유커피", size: 10 },
      ],
    };
  },
  mounted() {
    this.genLayout();
  },
  methods: {
    genLayout() {
      const cloud = require("d3-cloud");
      cloud()
        .words(this.words)
        .padding(1)
        .font("Impact")
        .fontSize(function (d) {
          return d.size;
        })
        .on("end", this.end)
        .spiral("archimedean")
        .start()
        .stop();
    },
    end(words) {
      const d3 = require("d3");
      const width = 500;
      const height = 500;
      d3.select("#word-cloud")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .style("background", "white")
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")") // g를 중심에서 단어들을 그리기 때문에 g를 svg 중심으로 이동
        .selectAll("text")
        .data(words)
        .enter()
        .append("text")
        .style("font-size", (d) => {
          return d.size*2 + "px";
        })
        .style("font-family", "Impact")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => {
          return "translate(" + [d.x, d.y] + ")";
        })
        .text((d) => d.text);
    },
  },
};
</script>

