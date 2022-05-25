<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button size="sm" variant="outline-secondary" @click="listQnA"
          >목록</b-button
        >
      </b-col>
      <b-col
        v-if="userInfo != null && this.QnA.userId === userInfo.userId"
        class="text-right"
      >
        <b-button
          size="sm"
          variant="outline-info "
          @click="moveModifyQnA"
          class="mr-2"
          >수정</b-button
        >
        <b-button size="sm" variant="outline-danger" @click="deleteQnA"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1 mt-2">
      <b-col>
        <b-card header-tag="header" footer-tag="footer">
          <template #header>
            <h6 class="mb-0 p-2">[{{ QnA.qnaNo }}] {{ QnA.title }}</h6>
          </template>
          <b-card-text class="text-left" style="height: 300px">{{
            QnA.content
          }}</b-card-text>
          <template #footer>
            <b-row>
              <b-col cols="auto" class="mr-auto"
                >작성자 : {{ QnA.userId }}</b-col
              >
              <b-col cols="auto" class="">작성일 : {{ QnA.regDate }}</b-col>
            </b-row>
          </template>
        </b-card>
      </b-col>
    </b-row>
    <div id="commentContainer" class="mt-3">
      <template v-if="userInfo != null">
        <div>
          <comment-input-list-item :qnaNo="qnaNo"></comment-input-list-item>
        </div>
      </template>
      <div class="mt-2" id="commentList">
        <comment-list :qnaNo="qnaNo"></comment-list>
      </div>
    </div>
  </b-container>
</template>

<script>
import { getQnA, deleteQnA } from "@/api/QnA";
import CommentList from "@/components/comment/CommentList.vue";
import CommentInputListItem from "@/components/comment/CommentInputList.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
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
    ...mapState(memberStore, ["userInfo"]),
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
        console.log(this.QnA.userId);
      },
      (error) => {
        console.log("QnA 에러발생!!", error);
        // eslint-disable-next-line
      }
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

<style scoped>
#commentList {
  overflow-y: scroll;
  flex: 1 1 auto;
  height: 300px;
}
#commentContainer {
  border: 1px solid #ced4da;
  border-radius: 5px;
}
</style>
