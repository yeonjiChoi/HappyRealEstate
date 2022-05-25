<template>
  <b-container
    id="container"
    v-if="houses && houses.length != 0"
    class="bv-example-row p-3"
  >
    <b-row class="text-center mb-2">
      <b-col>
        <b>{{ houses.length }}</b
        >개의 아파트가 있습니다.
      </b-col>
    </b-row>

    <b-row class="overflow-auto">
      <b-table
        id="my-table"
        class="m-2"
        :items="houses"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        @row-clicked="selectHouse"
        hover
        border-collapse
      >
        <template #cell(동)="data"> {{ data.item.dongName }}</template>
        <template #cell(아파트)="data"> {{ data.item.aptName }}</template>
      </b-table>
    </b-row>
    <b-row class="text-center">
      <b-pagination
        class="mx-auto m-2"
        center
        pills
        size="sm"
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      >
      </b-pagination>
    </b-row>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row
      ><b-alert show style="background-color: #d6eaf8; height: 50px"
        >주택 목록이 없습니다.</b-alert
      ></b-row
    >
  </b-container>
</template>
<script>
import { mapMutations, mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseInfoList",
  components: {},
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      fields: ["동", "아파트"],
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
  background-color: #d6eaf8;
}
#container {
  border: 1px solid #ced4da;
  border-radius: 10px;
}
</style>
