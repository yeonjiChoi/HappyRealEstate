<template>
  <b-container>
    <b-row>
      <b-col style="text-align: left">
        Comment
        <b-form @submit="onSubmit">
          <b-form-input
            label="comment"
            type="text"
            v-model="comment.content"
            placeholder="comments..."
          ></b-form-input>
          <b-button type="submit" class="m-1">등록</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { writeComment } from "@/api/comment.js";
const memberStore = "memberStore";
export default {
  name: "CommentInputItem",
  data() {
    return {
      comment: {
        no: 0,
        qnaNo: 0,
        userId: "",
        content: "",
      },
    };
  },
  props: {
    qnaNo: Number,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    // qna 번호 받아와서 넣어주기
    this.comment.qnaNo = this.qnaNo;

    // userId 현재 스토리지의 userInfo로 넣어주기
    this.comment.userId = this.userInfo.userId;
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.comment.content &&
        ((msg = "댓글 내용을 입력해주세요"), (err = false));
      err;
      if (!err) alert(msg);
      else {
        this.registerComment();
      }
    },
    registerComment() {
      writeComment(
        {
          userId: this.comment.userId,
          qnaNo: this.comment.qnaNo,
          content: this.comment.content,
        },
        ({ data }) => {
          let msg = "댓글 등록 오류";
          if (data === "success") {
            msg = "등록 완료";
          }
          alert(msg);
          this.$router.go();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveQnA() {
      this.$router.push({
        name: "QnADetail",
        params: { qnaNo: this.qnaNo },
      });
    },
  },
};
</script>

<style></style>
