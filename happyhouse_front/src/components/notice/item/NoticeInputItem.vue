<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userId-group"
          label="작성자:"
          label-for="userId"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userId"
            disabled
            v-model="notice.userId"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="notice.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeNotice, getNotice, modifyNotice } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice: {
        noticeNo: 0,
        userId: "",
        title: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getNotice(
        this.$route.params.noticeNo,
        ({ data }) => {
          this.notice = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isuserId = true;
    } else {
      this.notice.userId = this.userInfo.userId;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.notice.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.notice.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.noticeNo = 0;
      this.notice.title = "";
      this.notice.content = "";
      this.$router.push({ name: "noticeList" });
    },
    registNotice() {
      writeNotice(
        {
          userId: this.notice.userId,
          title: this.notice.title,
          content: this.notice.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyNotice() {
      modifyNotice(
        {
          noticeNo: this.notice.noticeNo,
          userId: this.notice.userId,
          title: this.notice.title,
          content: this.notice.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "noticeList" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style></style>
