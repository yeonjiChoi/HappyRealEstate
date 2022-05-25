<template>
  <div>
    <h5>Answer</h5>
    <b-container>
      <b-row>
        <b-col>
          <b-table striped hover :items="comments" :fields="fields"
            ><template v-slot:cell(Del)="{ item }">
              <span v-if="item.userId == userInfo.userId"
                ><b-btn @click="DeleteComment(item)">Delete</b-btn>
              </span>
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { listComment, deleteComment } from "@/api/comment";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "CommentList",
  props: {
    qnaNo: Number,
  },
  data() {
    return {
      comments: [],
      fields: [
        { key: "content", label: "내용" },
        { key: "userId", label: "작성자" },
        { key: "regDate", label: "작성일" },
        { key: "Del", label: "삭제" },
      ],
    };
  },
  created() {
    listComment(
      this.qnaNo,
      (response) => {
        this.comments = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    DeleteComment(item) {
      console.log(item.no);
      deleteComment(
        item.no,
        ({ data }) => {
          let msg = "댓글 삭제 오류";
          if (data === "success") {
            msg = "삭제 완료";
          }
          alert(msg);
          this.$router.go();
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>
