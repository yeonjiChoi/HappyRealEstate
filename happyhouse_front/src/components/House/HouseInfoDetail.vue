<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col>{{ house.aptName }} 아파트 거래 정보</b-col>
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
import { mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  created() {
    this.CLEAR_HOUSE();
  },
  components: {
    HouseDealListItem,
    BasicMap,
  },
  computed: {
    ...mapState(houseStore, ["houseDeals", "house"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSE"]),
  },
};
</script>

<style></style>
