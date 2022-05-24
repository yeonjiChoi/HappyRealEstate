<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col cols="10">{{ house.aptName }} 아파트 거래 정보</b-col>
      <b-col cols="2"
        ><b-icon icon="bookmark-heart" @click="setInterestApt"></b-icon
      ></b-col>
    </b-row>
    <b-row>
      <basic-map :house="house" />
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.aptCode }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">법정동 : {{ house.dongCode }} </b-alert>
      </b-col>
    </b-row>

    <div>
      <b-table-simple hover responsive>
        <b-thead head-variant="dark">
          <b-tr>
            <b-th>거래일</b-th>
            <b-th>전용 면적</b-th>
            <b-th>층</b-th>
            <b-th>가격</b-th>
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

    <!-- <house-deal-list-item
      v-for="(deal, index) in houseDeals"
      :key="index"
      :deal="deal"
    /> -->
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
      } else {
        alert("로그인 후 이용해주세요");
      }
    },
  },
};
</script>

<style></style>
