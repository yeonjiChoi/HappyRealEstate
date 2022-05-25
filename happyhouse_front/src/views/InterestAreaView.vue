<template>
  <b-container id="interestAreaView" class="p-3">
    <h4><b-icon icon="bookmark-star" /> 관심 지역</h4>
    <div id="categoryMap" class="p-2 mt-2" v-if="userInfo">
      <div>
        <b-icon icon="info-circle" /> 지역을 누르면 해당 위치로 이동하고,
        카테고리별 주변 상권을 확인할 수 있습니다.
      </div>

      <b-row>
        <b-col cols="4" align="left">
          <interest-area-list />
        </b-col>
        <b-col cols="8" class="p-3"> <interest-map /> </b-col>
      </b-row>
    </div>
    <div v-else>
      <b-jumbotron
        header="로그인 후 이용해주세요"
        lead="사용자가 추가한 관심 지역을 확인할 수 있습니다."
      >
        <b-button :to="{ name: 'login' }" variant="outline-dark"
          >Login</b-button
        >
      </b-jumbotron>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";

import InterestAreaList from "@/components/InterestArea/InterestAreaList.vue";
import InterestMap from "@/components/InterestMap.vue";
export default {
  components: { InterestAreaList, InterestMap },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
* {
  color: #7d7d7d;
}
#interestAreaView {
  height: 800px;
}

#categoryMap {
  border: 1px solid #ced4da;
  border-radius: 10px;
}
</style>
