<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
    </b-col>
    <b-button @click="setInterestArea">관심지역 등록</b-button>
  </b-row>
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
      if (this.dongCode) this.getHouseList(this.dongCode);
    },

    setInterestArea() {
      if (this.dongCode) {
        if (this.userInfo != null) {
          this.interestArea = {
            userId: this.userInfo.userId,
            dongCode: this.dongCode,
          };
          console.log("setInterArea ", this.interestArea);
          this.setInterArea(this.interestArea);
        } else {
          alert("로그인 후 이용해주세요");
        }
      } else {
        alert("동을 선택해주세요");
      }
    },
  },
};
</script>

<style></style>
