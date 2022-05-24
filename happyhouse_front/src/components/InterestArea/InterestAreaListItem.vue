<template>
  <b-row
    class="m-2"
    @click="selectArea"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" @click="deleteInterestArea">
      <b-icon icon="x-lg"></b-icon>
    </b-col>
    <b-col cols="10"
      >{{ area.sidoName }} {{ area.gugunName }} {{ area.dongName }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";
const memberStore = "memberStore";
const interestStore = "interestStore";

export default {
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    area: Object,
  },
  methods: {
    ...mapActions(interestStore, [
      "getInterArea",
      "deleteInterArea",
      "setLatLng",
    ]),
    selectArea() {
      this.setLatLng(this.area);
    },
    deleteInterestArea() {
      this.deleteInterArea(this.area);
      this.getInterArea(this.userInfo.userId);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
