<template>
  <div id="map"></div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

var markerPosition;
var marker;

export default {
  data() {
    return {};
  },
  props: {
    house: Object,
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ed1abee1141363889fd200db7f7ce90e";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["latlng"]),
  },
  methods: {
    initMap() {
      var lat = parseFloat(this.latlng[0].lat);
      var lng = parseFloat(this.latlng[0].lng);
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(lat, lng),
        level: 1,
      };
      this.map = new kakao.maps.Map(container, options);
      markerPosition = new kakao.maps.LatLng(lat, lng);
      marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(null);
      marker.setMap(this.map);
    },
  },
  watch: {
    latlng: function setCenter() {
      marker.setMap(null);
      // 이동할 위도 경도 위치를 생성합니다
      console.log("LATLNG 확인 ", this.latlng);
      var lat = parseFloat(this.latlng[0].lat);
      var lng = parseFloat(this.latlng[0].lng);
      console.log(lat, lng);
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 이동 시킵니다
      this.map.setCenter(moveLatLon);

      markerPosition = new kakao.maps.LatLng(lat, lng);
      marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 350px;
  border: none;
}
</style>
