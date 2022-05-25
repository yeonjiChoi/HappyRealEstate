<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="justify-content-md-left mb-2">
      <span class="mr-1">
        <b-form-select size="sm" v-model="searchKey" variant="secondary">
          <option value="">선택</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
        </b-form-select>
      </span>
      <span class="mr-1">
        <b-input
          size="sm"
          type="text"
          v-model="searchValue"
          @keyup.enter="getListQna()"
        />
      </span>
      <span>
        <b-button size="sm" variant="outline-secondary" @click="getListQna()">
          검색
        </b-button>
      </span>

      <b-col
        class="text-right"
        v-if="userInfo != null && userInfo.authority === 'ADMIN'"
      >
        <b-button size="sm" variant="outline-secondary" @click="moveWrite()"
          >Q&A 작성</b-button
        >
      </b-col>
    </b-row>
    <b-row>
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
    </b-row>
    <b-row>
      <b-pagination
        class="mx-auto m-2"
        center
        pills
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
        first-text="<<"
        prev-text="<"
        next-text=">"
        last-text=">>"
      ></b-pagination>
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
      searchKey: "",
      searchValue: "",
    };
  },
  created() {
    this.getListQna();
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
    getListQna() {
      const params = { key: this.searchKey, value: this.searchValue };
      listQnA(
        params,
        (response) => {
          this.QnAs = response.data;
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
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
