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
          :per-page="perPage"
          :current-page="currentPage"
          :items="QnAs"
          :fields="fields"
          @row-clicked="viewQnA"
        >
        </b-table>
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
        ></b-pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listQnA } from "@/api/QnA";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "QnAList",
  data() {
    return {
      QnAs: [],
      fields: [
        { key: "qnaNo", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "regDate", label: "작성일", tdClass: "tdClass" },
      ],
      perPage: 10,
      currentPage: 1,
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listQnA(
      param,
      (response) => {
        this.QnAs = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QnAWrite" });
    },
    viewQnA(QnA) {
      this.$router.push({
        name: "QnADetail",
        params: { qnaNo: QnA.qnaNo },
      });
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.QnAs.length;
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
