<template>
  <div @click="test">{{playnowname}} 관련 영상</div>
    <div
      v-for="videoId in videoIds"
      :key="videoId"
    >
      <iframe id="ytplayer" type="text/html" width="300" height="180"
      :src="videoId"
      frameborder="0"></iframe>
    </div>

  
</template>

<script>
import axios from "axios";
import { reactive,computed } from 'vue';
import { useStore } from "vuex";
export default {
  setup(){
    const store = useStore()
    let playnowname = computed(()=>store.state.gamedetail.playnowname)

    let videoIds = reactive([])
    const test = function () {
      if (playnowname.value !== ""){
        axios({
          url: "https://www.googleapis.com/youtube/v3/search",
          method: "get",
          params: {
            key:"AIzaSyDfN3PYabbgHgso6PPs9j7gEzPSNfK6AO8",
            part:"snippet",
            q:`${playnowname.value}설명`,
          }})
          .then((res)=>{
            for (let i of res.data.items) {
              let tmp = `https://www.youtube.com/embed/${i.id.videoId}`
              videoIds.push(tmp)
              console.log(videoIds)
            }
          })
          .catch((err) => {
            console.log(err)
          })
        }
      }

    return {
      test,
      videoIds,
      playnowname
    }
  }

}
</script>

<style>

</style>