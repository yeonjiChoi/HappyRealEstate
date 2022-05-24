<template>
  <b-tr>
    <b-td>{{ noticeNo }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'noticeDetail', params: { noticeNo: noticeNo } }"
        >{{ title }}</router-link
      >
    </b-th>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ regDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    noticeNo: Number,
    userId: String,
    title: String,
    regDate: String,
  },
  filters: {
    dateFormat(regDate) {
      return moment(new Date(regDate)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
