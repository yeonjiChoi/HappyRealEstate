<template>
  <div>
    <b-container>
      <h2>HouseInfo</h2>
      <b-row>
        <b-col>
          <house-info-search-bar></house-info-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="left">
          <house-info-list></house-info-list>
        </b-col>
        <b-col cols="6">
          <house-info-detail></house-info-detail>
        </b-col>
      </b-row>
    </b-container>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import FooterBar from "@/components/FooterBar.vue";
import HouseInfoList from "@/components/House/HouseInfoList.vue";
import HouseInfoSearchBar from "@/components/House/HouseInfoSearchBar.vue";
import HouseInfoDetail from "@/components/House/HouseInfoDetail.vue";
export default {
  name: "HouseInfoView",
  components: { FooterBar, HouseInfoSearchBar, HouseInfoList, HouseInfoDetail },
};
</script>

<style></style>
