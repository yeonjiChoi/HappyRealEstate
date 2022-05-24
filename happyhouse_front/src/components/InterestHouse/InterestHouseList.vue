<template>
  <b-container
    v-if="interestApts && interestApts.length != 0"
    class="bv-example-row mt-3"
  >
    <interest-house-list-item
      v-for="(apt, index) in interestApts"
      :key="index"
      :apt="apt"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>관심 아파트가 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import InterestHouseListItem from "@/components/InterestHouse/InterestHouseListItem.vue";

import { mapActions, mapState } from "vuex";
const interestStore = "interestStore";
const memberStore = "memberStore";

export default {
  data() {
    return {};
  },
  created() {
    this.getInterApt(this.userInfo.userId);
  },
  components: {
    InterestHouseListItem,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(interestStore, ["interestApts"]),
  },
  methods: {
    ...mapActions(interestStore, ["getInterApt"]),
  },
};
</script>

<style></style>
