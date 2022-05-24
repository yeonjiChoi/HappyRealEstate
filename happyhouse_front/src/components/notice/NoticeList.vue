<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="notices"
          :fields="fields"
          @row-clicked="viewNotice"
        >
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listNotice } from "@/api/notice";

export default {
  name: "NoticeList",
  data() {
    return {
      notices: [],
      fields: [
        { key: "noticeNo", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "regDate", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listNotice(
      param,
      (response) => {
        this.notices = response.data;
      },
      (error) => {
        console.log(error);
      },
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

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
