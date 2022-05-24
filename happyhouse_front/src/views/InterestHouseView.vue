<template>
  <div>
    <h2>InterestHouse</h2>
    <div v-if="userInfo" class="container">
      <b-row>
        <b-col cols="4" align="left">
          <interest-house-list />
        </b-col>
        <b-col cols="8"> <interest-map /> </b-col>
      </b-row>
    </div>
    <div v-else>
      <h2>로그인 후 이용해주세요</h2>
    </div>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";

import FooterBar from "@/components/FooterBar.vue";
import InterestHouseList from "@/components/InterestHouse/InterestHouseList.vue";
import InterestMap from "@/components/InterestMap.vue";

export default {
  components: { FooterBar, InterestHouseList, InterestMap },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
