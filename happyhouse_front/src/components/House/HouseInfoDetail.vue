<template>
  <b-container id="container" v-if="house" class="bv-example-row">
    <b-row> </b-row>
    <b-row>
      <b-col class="mx-auto m-2">
        <basic-map :house="house" />
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <h5>
          <b>
            {{ house.aptName }}
          </b>
        </h5>
      </b-col>
    </b-row>
    <b-row>
      <b-col
        >건축 연도 {{ house.buildYear }} | 지번 {{ house.jibun }} | 법정동
        {{ house.dongName }}
      </b-col>
    </b-row>

    <b-row v-show="userInfo != null" class="mt-1 mb-2">
      <b-col cols="auto" class="mr-auto ml-2">
        <b-icon icon="list-ul" /> 거래 정보
      </b-col>
      <b-col cols="auto" class="">
        <b-button variant="outline-secondary" size="sm" @click="setInterestApt"
          >관심 아파트 추가</b-button
        ></b-col
      >
    </b-row>

    <b-row>
      <b-col> </b-col>
    </b-row>
    <div class="deal-table">
      <b-table-simple hover responsive>
        <b-thead head-variant="light">
          <b-tr>
            <b-th>계약일</b-th>
            <b-th>전용 면적(m&sup2;)</b-th>
            <b-th>층</b-th>
            <b-th>가격(원)</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <house-deal-list-item
            v-for="(deal, index) in houseDeals"
            :key="index"
            :deal="deal"
          />
        </tbody>
      </b-table-simple>
    </div>
  </b-container>
</template>

<script>
import HouseDealListItem from "@/components/House/HouseDealListItem.vue";
import BasicMap from "@/components/BasicMap.vue";
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";
const interestStore = "interestStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      interestApt: {
        userId: null,
        aptCode: null,
      },
    };
  },
  created() {
    this.CLEAR_HOUSE();
  },
  components: {
    HouseDealListItem,
    BasicMap,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["houseDeals", "house"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSE"]),
    ...mapActions(interestStore, ["setInterApt"]),

    setInterestApt() {
      if (this.userInfo != null) {
        this.interestApt = {
          userId: this.userInfo.userId,
          aptCode: this.house.aptCode,
        };
        console.log("setInterApt 호출 : ", this.interestApt);
        this.setInterApt(this.interestApt);
        alert("관심 매물에 추가되었습니다.");
      } else {
        alert("로그인 후 이용해주세요");
      }
    },
    changeFillIcon() {
      this.myIcon = "bookmark-heart-fill";
    },
    chageIcon() {
      this.myIcon = "bookmark-heart";
    },
  },
};
</script>

<style scoped>
#container {
  border: 1px solid #ced4da;
  border-radius: 10px;
}

.deal-table {
  height: 400px;
  overflow-y: scroll;
}
</style>
