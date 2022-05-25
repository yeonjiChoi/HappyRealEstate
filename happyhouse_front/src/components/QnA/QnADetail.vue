<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button @click="listQnA">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button size="sm" @click="moveModifyQnA" class="mr-2"
          >글수정</b-button
        >
        <b-button size="sm" @click="deleteQnA">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${QnA.qnaNo}.
          ${QnA.title} </h3><div><h6>${QnA.userId}</div><div>${QnA.regDate}</h6></div>`"
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
    <comment-list :qnaNo="qnaNo"></comment-list>
    <comment-input-list-item :qnaNo="qnaNo"></comment-input-list-item>
  </b-container>
</template>

<script>
import { getQnA, deleteQnA } from "@/api/QnA";
import CommentList from "@/components/comment/CommentList.vue";
import CommentInputListItem from "@/components/comment/CommentInputList.vue";
export default {
  name: "QnADetail",

  data() {
    return {
      qnaNo: Number(this.$route.params.qnaNo),
      QnA: {},
    };
  },
  components: { CommentList, CommentInputListItem },
  computed: {
    message() {
      if (this.QnA.content) return this.QnA.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQnA(
      this.$route.params.qnaNo,
      (response) => {
        this.QnA = response.data;
      },
      (error) => {
        console.log("QnA 에러발생!!", error);
      },
    );
  },
  methods: {
    listQnA() {
      this.$router.push({ name: "QnAList" });
    },
    moveModifyQnA() {
      this.$router.replace({
        name: "QnAModify",
        params: { qnaNo: this.QnA.qnaNo },
      });
    },
    deleteQnA() {
      if (confirm("정말로 삭제?")) {
        deleteQnA(this.QnA.qnaNo, () => {
          this.$router.push({ name: "QnAList" });
        });
      }
    },
  },
};
</script>

<style></style>
