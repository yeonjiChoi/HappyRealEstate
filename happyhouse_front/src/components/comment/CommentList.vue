<template>
  <b-container>
    <comment-list-item
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      @update-comment="setCommentList()"
    />
  </b-container>
</template>

<script>
import { listComment } from "@/api/comment";

import CommentListItem from "@/components/comment/CommentListItem.vue";

export default {
  name: "CommentList",
  props: {
    qnaNo: Number,
  },
  components: {
    CommentListItem,
  },
  data() {
    return {
      comments: [],
    };
  },
  methods: {
    setCommentList() {
      listComment(
        this.qnaNo,
        (response) => {
          this.comments = response.data;
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
    },
  },
  created() {
    this.setCommentList();
  },
};
</script>

<style></style>
