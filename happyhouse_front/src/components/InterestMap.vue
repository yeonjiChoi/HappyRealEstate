<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width: 100%; height: 100%; position: relative; overflow: hidden"
    ></div>
    <ul id="category">
      <li id="BK9" data-order="0" @click="onClickCategory('BK9', 'bank')">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1" @click="onClickCategory('MT1', 'mart')">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2" @click="onClickCategory('PM9', 'pharmacy')">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3" @click="onClickCategory('OL7', 'gasStation')">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4" @click="onClickCategory('CE7', 'cafe')">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li
        id="CS2"
        data-order="5"
        @click="onClickCategory('CS2', 'convenienceStore')"
      >
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";
const interestStore = "interestStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      placeOverlay: null,
      contentNode: null,
      markers: [],
      currCategory: "",
      infowindow: null,
      ps: null,
      overlay: null,
      categories: [
        "bank",
        "mart",
        "pharmacy",
        "gasStation",
        "cafe",
        "convenienceStore",
      ],
      categoryStatus: {
        bank: "off",
        mart: "off",
        pharmacy: "off",
        gasStation: "off",
        cafe: "off",
        convenienceStore: "off",
      },
      marker: null,
      markerPosition: null,
    };
  },
  computed: {
    ...mapState(interestStore, ["latlng"]),
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initKakaoMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initKakaoMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a0907e584ee8c57ddcabc138315de0f5&libraries=services";
      document.head.appendChild(script);
    }
  },
  watch: {
    latlng: function setCenter() {
      // 이동할 위도 경도 위치를 생성합니다
      console.log("LATLNG 확인 ", this.latlng);
      var lat = parseFloat(this.latlng[0].lat);
      var lng = parseFloat(this.latlng[0].lng);
      console.log(lat, lng);
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 이동 시킵니다
      this.map.setCenter(moveLatLon);

      // this.markerPosition = new kakao.maps.LatLng(lat, lng);
      if (this.marker == null) this.marker = new kakao.maps.Marker();
      this.marker.setPosition(new kakao.maps.LatLng(lat, lng));
      // this.marker = new kakao.maps.Marker({
      //   position: this.markerPosition,
      // });

      this.marker.setMap(this.map);
    },
  },
  methods: {
    initKakaoMap() {
      const mapContainer = document.getElementById("map");
      const mapOptions = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3,
      };
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.map = new kakao.maps.Map(mapContainer, mapOptions);
      this.ps = new kakao.maps.services.Places(this.map);
      this.contentNode = document.createElement("div");
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      this.contentNode.className = "placeinfo_wrap";
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap,
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap,
      );
      this.placeOverlay.setContent(this.contentNode);
    },
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }
      this.placeOverlay.setMap(null);
      this.removeMarker();
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        this.displayPlaces(data);
      }
    },
    displayPlaces(places) {
      const kakaoMap = this;
      const order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");
      for (let i = 0; i < places.length; i++) {
        const marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order,
        );
        (function (marker, place) {
          kakao.maps.event.addListener(marker, "click", function () {
            kakaoMap.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },
    addMarker(position, order) {
      const imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png",
        imageSize = new kakao.maps.Size(27, 28),
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208),
          spriteOrigin: new kakao.maps.Point(46, order * 36),
          offset: new kakao.maps.Point(11, 28),
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions,
        ),
        marker = new kakao.maps.Marker({
          position: position,
          image: markerImage,
        });
      marker.setMap(this.map);
      this.markers.push(marker);
      return marker;
    },
    removeMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayPlaceInfo(place) {
      let content = '<div class="placeinfo">';
      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }
      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';
      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    onClickCategory(selectedId, key) {
      const id = selectedId;
      if (this.categoryStatus[key] === "off") {
        this.offAllCategoryStatus();
        this.categoryStatus[key] = "on";
      } else {
        this.categoryStatus[key] = "off";
      }
      this.placeOverlay.setMap(null);
      if (this.categoryStatus[key] === "on") {
        this.currCategory = id;
        this.searchPlaces();
      } else {
        this.currCategory = "";
        this.removeMarker();
      }
    },
    offAllCategoryStatus() {
      this.categories.forEach((category) => {
        this.categoryStatus[category] = "off";
      });
    },
  },
};
</script>

<style scoped>
@import "./InterestMap.scss";
</style>
