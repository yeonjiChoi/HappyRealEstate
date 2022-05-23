<template>
  <b-tr>
    <b-td>{{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}</b-td>
    <b-td>{{ deal.area }}</b-td>
    <b-td>{{ deal.floor }}</b-td>
    <b-td>{{
      (parseInt(deal.dealAmount.replace(",", "")) * 10000) | price
    }}</b-td>
  </b-tr>
  <!-- <b-row>
    <b-col>{{ deal.aptCode }}</b-col>
    <b-col
      >{{
        (parseInt(deal.dealAmount.replace(",", "")) * 10000) | price
      }}원</b-col
    >
    <b-col>{{ deal.floor }}층</b-col>
  </b-row> -->
</template>

<script>
export default {
  namne: "HouseDealListItem",
  data() {
    return {};
  },
  props: {
    deal: Object,
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
