<template>
  <div
    id="house-item"
    class="m-2 p-2"
    @click="selectApt"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-row>
      <b-col cols="10">
        [{{ apt.sidoName }} {{ apt.gugunName }} {{ apt.dongName }}]
      </b-col>
      <b-col cols="2"
        ><b-icon icon="trash" @click="confirmDeleteApt"></b-icon
      ></b-col>
    </b-row>

    <b-row>
      <b-col> {{ apt.aptName }}</b-col>
    </b-row>
  </div>
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
    confirmDeleteApt() {
      if (
        confirm(this.apt.aptName + "을(를) 관심 아파트에서 삭제하시겠습니까?")
      ) {
        this.deleteInterestApt();
      }
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
  background-color: #d6eaf8;
}

#house-item {
  border: 1px dashed #ced4da;
  border-radius: 10px;
}
</style>
