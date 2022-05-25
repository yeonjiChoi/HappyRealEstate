<template>
  <b-row
    id="area-item"
    class="m-2 p-2"
    @click="selectArea"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="10"
      >{{ area.sidoName }} {{ area.gugunName }} {{ area.dongName }}
    </b-col>
    <b-col cols="2" @click="confirmDeleteArea">
      <b-icon icon="trash"></b-icon>
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
    confirmDeleteArea() {
      if (
        confirm(this.area.dongName + "을(를) 관심 지역에서 삭제하시겠습니까?")
      ) {
        this.deleteInterestArea();
      }
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
  background-color: #d6eaf8;
}
#area-item {
  border: 1px dashed #ced4da;
  border-radius: 10px;
}
</style>
