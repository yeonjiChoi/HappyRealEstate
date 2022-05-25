<template>
  <b-container class="mt-3">
    <div>
      <h5><b>공지사항</b></h5>
    </div>
    <div class="mt-3">
      <b-table
        thead-class="d-none"
        hover
        :per-page="8"
        :items="notices"
        :fields="fields"
        @row-clicked="viewNotice"
      >
        <template #cell(제목)="data">
          <span v-html="data.item.title"></span>
        </template>
      </b-table>
    </div>
  </b-container>
</template>

<script>
import { listNotice } from "@/api/notice";

export default {
  data() {
    return {
      notices: [],
      fields: ["제목"],
    };
  },
  created() {
    listNotice(
      (response) => {
        this.notices = response.data;
      },
      (error) => {
        console.log(error);
        // eslint-disable-next-line
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeWrite" });
    },
    viewNotice(notice) {
      this.$router.push({
        name: "noticeDetail",
        params: { noticeNo: notice.noticeNo },
      });
    },
  },
};
</script>
<style scoped>
* {
  color: #7d7d7d;
}
</style>
