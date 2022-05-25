<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1 mt-2">
      <b-col>
        <b-card header-tag="header" footer-tag="footer">
          <template #header>
            <h6 class="mb-0 p-2">[{{ notice.noticeNo }}] {{ notice.title }}</h6>
          </template>
          <b-card-text class="text-left" style="height: 500px">{{
            notice.content
          }}</b-card-text>
          <template #footer>
            <b-row>
              <b-col cols="auto" class="mr-auto"
                >작성자 : {{ notice.userId }}</b-col
              >
              <b-col cols="auto" class="">작성일 : {{ notice.regDate }}</b-col>
            </b-row>
          </template>
        </b-card>
      </b-col>
    </b-row>
    <b-row
      class="mb-1"
      v-if="userInfo != null && userInfo.authority === 'ADMIN'"
    >
      <b-col class="text-left">
        <b-button size="sm" variant="outline-secondary" @click="listNotice"
          >목록</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button
          size="sm"
          variant="outline-secondary"
          @click="moveModifyNotice"
          class="mr-2"
          >수정</b-button
        >
        <b-button size="sm" variant="outline-secondary" @click="deleteNotice"
          >삭제</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getNotice, deleteNotice } from "@/api/notice";
import store from "@/store/index.js";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "NoticeDetail",
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getNotice(
      this.$route.params.noticeNo,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        console.log("공지사항 에러발생!!", error);
        // eslint-disable-next-line
      },
    );
  },
  methods: {
    listNotice() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeNo: this.notice.noticeNo },
      });
    },
    deleteNotice() {
      const checkUserInfo = store.getters["memberStore/checkUserInfo"];
      if (checkUserInfo.authority === "USER") {
        alert("권한이 없습니다");
      } else if (confirm("정말로 삭제?")) {
        deleteNotice(this.notice.noticeNo, () => {
          this.$router.push({ name: "noticeList" });
        });
      }
    },
  },
};
</script>

<style></style>
