<template>
  <div id="word-cloud"></div>
</template>

<style>
#word-cloud {
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  height: 300px;
  border: solid rgb(193, 193, 193) 1px;
}
</style>

<script>
export default {
  name: 'WordCloud',
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
      const width = 300;
      const height = 300;
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

