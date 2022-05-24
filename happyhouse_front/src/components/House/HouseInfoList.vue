<template>
  <div>
    <b-container
      v-if="houses && houses.length != 0"
      class="bv-example-row mt-3"
    >
      <div>{{ houses.length }}개의 아파트가 있습니다.</div>
      <!-- <house-info-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      /> -->

      <div class="overflow-auto">
        <b-table
          id="my-table"
          :items="houses"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          @row-clicked="selectHouse"
          hover
          border-collapse
          small
        >
          <template #cell(번호)="data"> {{ data.index + 1 }} </template>
          <template #cell(동)="data"> {{ data.item.dongName }}</template>
          <template #cell(아파트)="data"> {{ data.item.aptName }}</template>
        </b-table>
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
        >
        </b-pagination>
      </div>
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-container>
  </div>
</template>
<script>
// import HouseInfoListItem from "@/components/House/HouseInfoListItem.vue";
import { mapMutations, mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseInfoList",
  components: {
    // HouseInfoListItem,
  },
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      fields: ["번호", "동", "아파트"],
      isColor: false,
    };
  },
  created() {
    this.CLEAR_HOUSES_LIST();
    this.makeItems();
  },

  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSES_LIST"]),
    makeItems() {
      this.houses.forEach((house, index) => {
        this.items.push({
          no: index,
          apartment: house.aptName,
          dong: house.dongName,
        });
      });
    },
    ...mapActions(houseStore, ["getHouseDealList", "detailHouse", "setLatLng"]),
    selectHouse(house, index) {
      console.log("클릭!", house, index);
      this.detailHouse(house);
      this.getHouseDealList(house);
      this.setLatLng(house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    rows() {
      return this.houses.length;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
