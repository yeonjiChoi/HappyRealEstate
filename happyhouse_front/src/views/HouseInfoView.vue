<template>
  <b-container id="houseInfoView" class="p-3">
    <h4><b-icon icon="building" /> 아파트 거래 정보</h4>
    <b-row>
      <b-col>
        <house-info-search-bar></house-info-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="4" align="left">
        <house-info-list></house-info-list>
      </b-col>
      <b-col cols="8">
        <house-info-detail></house-info-detail>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseInfoList from "@/components/House/HouseInfoList.vue";
import HouseInfoSearchBar from "@/components/House/HouseInfoSearchBar.vue";
import HouseInfoDetail from "@/components/House/HouseInfoDetail.vue";

export default {
  name: "HouseInfoView",
  components: { HouseInfoSearchBar, HouseInfoList, HouseInfoDetail },
};
</script>

<style scope>
* {
  color: #7d7d7d;
}
#houseInfoView {
  height: 1200px;
}
</style>
