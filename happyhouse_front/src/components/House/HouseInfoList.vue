<template>
  <div>
    <b-container
      v-if="houses && houses.length != 0"
      class="bv-example-row mt-3"
    >
      <div>{{ houses.length }}개의 아파트가 있습니다.</div>
      <house-info-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      />
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-container>
  </div>
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
  },
};
</script>

<style scoped></style>
