<template>
  <b-row
    class="m-2"
    @click="selectApt"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" @click="deleteInterestApt">
      <b-icon icon="x-lg"></b-icon>
    </b-col>
    <b-col cols="10">[{{ apt.dongName }}] {{ apt.aptName }} </b-col>
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
    apt: Object,
  },
  methods: {
    ...mapActions(interestStore, [
      "getInterApt",
      "deleteInterApt",
      "setLatLng",
    ]),
    selectApt() {
      this.setLatLng(this.apt);
    },
    deleteInterestApt() {
      this.deleteInterApt(this.apt);
      this.getInterApt(this.userInfo.userId);
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
