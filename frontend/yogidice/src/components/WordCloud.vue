<template>
  <div id="word-cloud"></div>
</template>
<style>
  #word-cloud {
    background-color: rgba(255, 255, 255, 0.56);
    
  }
</style>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      words: [],
      wValue: 0
    };
  },
  mounted() {
    const wValue = document.body.clientWidth
    this.wValue = wValue
    axios.get('https://j7b206.p.ssafy.io/api/games/12333')
    .then(res => {
      console.log(res.data.mechanismGroupResponses)
      const mgrlist = res.data.mechanismGroupResponses
      for (let mgr of mgrlist) {
        console.log(mgr.mechanismName)
        let wordelem = new Object();
        wordelem.text = mgr.mechanismName
        wordelem.size = wValue*0.04
        if (mgr.mechanismName == '영역 이동') {
          wordelem.color = 'red'
        }
        else if (mgr.mechanismName == '시나리오 / 미션 / 캠페인 게임') {
          wordelem.color = 'red'
        }
        else {
          wordelem.color = 'green'
        }
        this.words.push(wordelem)
      }
      console.log(this.words)
      this.genLayout();
    })
    .catch(err => {
      console.log(err)
    })
    
  },
  methods: {
    genLayout() {
      const cloud = require("d3-cloud");
      cloud()
        .words(this.words)
        .padding(4)
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
      const width = this.wValue
      const height = this.wValue
      d3.select("#word-cloud")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .style("background", "rgba(255, 255, 255, 0.5)")
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")")
        .selectAll("text")
        .data(words)
        .enter()
        .append("text")
        .style("font-size", (d) => {
          return d.size*0.3 + "vw";
        })
        .style("fill", (d) => {return d.color})
        .attr("text-anchor", "middle")
        .attr("transform", (d) => {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text((d) => d.text);
    },
  },
};
</script>