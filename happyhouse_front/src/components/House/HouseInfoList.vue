<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-info-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col v-if="houses"><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseInfoListItem from "@/components/House/HouseInfoListItem.vue";
import { mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseInfoList",
  components: {
    HouseInfoListItem,
  },
  data() {
    return {};
  },
  created() {
    this.CLEAR_HOUSES_LIST();
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSES_LIST"]),
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
