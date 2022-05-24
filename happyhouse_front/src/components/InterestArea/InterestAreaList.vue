<template>
  <b-container
    v-if="interestAreas && interestAreas.length != 0"
    class="bv-example-row mt-3"
  >
    <interest-area-list-item
      v-for="(area, index) in interestAreas"
      :key="index"
      :area="area"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>관심 지역이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import InterestAreaListItem from "@/components/InterestArea/InterestAreaListItem.vue";
import { mapActions, mapState } from "vuex";
const interestStore = "interestStore";
const memberStore = "memberStore";

export default {
  data() {
    return {};
  },
  created() {
    this.getInterArea(this.userInfo.userId);
  },
  components: {
    InterestAreaListItem,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(interestStore, ["interestAreas"]),
  },
  methods: {
    ...mapActions(interestStore, ["getInterArea"]),
  },
};
</script>

<style></style>
