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
    let locPosition
    let lat = ref(37.564343)
    let lon = ref(126.947613)

    // 지도 생성하기
    const initMap = function () {
      const container = document.getElementById("map")
      const options = {
        // 지도의 중심 좌표
        center: new kakao.maps.LatLng(lat.value, lon.value),
        // 지도의 확대 레벨
        level: 5                    
      }
      map = new kakao.maps.Map(container, options)
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {
        
          let newLat = position.coords.latitude; // 위도
          let newLon = position.coords.longitude; // 경도  
          locPosition = new kakao.maps.LatLng(newLat, newLon) 
          displayMarker(locPosition,"현재위치")
          map.setCenter(locPosition);      
      });
      }
    }

    // 마커 찍기
    const displayMarker = function (locPosition, message) {
      // 마커 이미지 변경하기
      // const imageSrc = '이미지 주소'
      // // 마커이미지의 주소입니다    
      // const imageSize = new kakao.maps.Size(64, 69) // 마커이미지의 크기입니다
      // const imageOption = {offset: new kakao.maps.Point(27, 69)} // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
            
      // // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      // const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({  
          map: map, 
          position: locPosition,
          // image: markerImage
      }); 
      
      var iwContent = message, // 인포윈도우에 표시할 내용
          iwRemoveable = true;

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
          content : iwContent,
          removable : iwRemoveable
      });
      
      // 인포윈도우를 마커위에 표시합니다 
      infowindow.open(map, marker);
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
      map,
      locPosition
    }


  }
}
</script>
<style>

</style>