<template>
  <b-container>
    <b-row v-show="userInfo == null">
      <b-col
        ><b-icon icon="info-circle" /> 로그인을 하시면 관심 지역과 관심 매물을
        등록할 수 있습니다.</b-col
      >
    </b-row>
    <b-row align-h="center" class="mb-5 mt-3">
      <br />
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
      <b-button
        variant="outline-secondary"
        class="ml-3"
        v-show="userInfo != null"
        @click="setInterestArea"
        >관심지역 추가</b-button
      >
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";
const interestStore = "interestStore";

export default {
  name: "HouseInfoSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      interestArea: {
        userId: null,
        dongCode: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getTotAptCnt",
    ]),
    ...mapActions(interestStore, ["setInterArea"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },

    searchApt() {
      if (this.dongCode) {
        this.getHouseList(this.dongCode);
      }
    },

    setInterestArea() {
      if (this.dongCode) {
        this.interestArea = {
          userId: this.userInfo.userId,
          dongCode: this.dongCode,
        };
        console.log("setInterArea ", this.interestArea);
        this.setInterArea(this.interestArea);
        alert("관심 지역에 추가되었습니다");
      } else {
        alert("동을 선택해주세요");
      }
    },
  },
};
</script>

<style scoped>
* {
  color: #7d7d7d;
}
</style>
