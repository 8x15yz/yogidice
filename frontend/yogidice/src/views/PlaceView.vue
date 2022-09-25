<template>
  <div>
    <div id="map" style="width:500px;height:400px;"></div>
  </div>
</template>

<script>
import { onMounted,ref } from 'vue'
export default {
  setup() {
    let map
    let lat = ref(37.564343)
    let lon = ref(126.947613)
    const initMap = function () {
      const container = document.getElementById("map")
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {
        
          lat.value = position.coords.latitude, // 위도
          lon.value = position.coords.longitude; // 경도  
          console.log(lat.value,lon.value)              
      });
      }
      const options = {
        // 지도의 중심 좌표
        center: new kakao.maps.LatLng(lat.value, lon.value),
        // 지도의 확대 레벨
        level: 5                    
      }
      map = new kakao.maps.Map(container, options)
    }
    onMounted (() => {
      if (window.kakao && window.kakao.maps) {
        initMap()
      } else {
        const script = document.createElement("script");
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=98483126b77d6c122713d9878feec1bc"
        /* global kakao */
        script.addEventListener("load", () => {
          kakao.maps.load(initMap)
        })
        document.head.appendChild(script)
      }})

    return {
      map
    }


  }
}
</script>
<style>

</style>