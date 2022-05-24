<template>
  <b-container class="bv-example-row mt-3">
    <b-row
      class="mb-1"
      v-if="userInfo != null && userInfo.authority === 'ADMIN'"
    >
      <b-col class="text-left">
        <b-button @click="listNotice">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button size="sm" @click="moveModifyNotice" class="mr-2"
          >글수정</b-button
        >
        <b-button size="sm" @click="deleteNotice">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${notice.noticeNo}.
          ${notice.title} </h3><div><h6>${notice.userId}</div><div>${notice.regDate}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
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
