<template>
  <div>
    <h5>Answer</h5>
    <b-container>
      <b-row>
        <b-col>
          <b-table striped hover :items="comments" :fields="fields"
            ><b-button></b-button>
          </b-table>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { listComment } from "@/api/comment";
export default {
  name: "CommentList",
  props: {
    qnaNo: Number,
  },
  data() {
    return {
      comments: [],
      fields: [
        { key: "content", label: "내용", tdClass: "tdClass" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "regDate", label: "작성일", tdClass: "tdClass" },
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
  methods: {},
};
</script>

<style></style>
