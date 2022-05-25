<template>
  <div v-if="!isModify" id="commentItem" class="ml-3 mt-1 p-2">
    <b-row align-v="center">
      <b-col class="text-center">
        <b-icon icon="person" /> {{ comment.userId }}
      </b-col>
      | <b-col cols="7"> {{ comment.content }} </b-col> |
      <b-col class="text-center" cols="2"> {{ comment.regDate | date }} </b-col>
      |
      <b-col cols="1" v-if="userInfo.userId == comment.userId">
        <b-icon icon="x-square" @click="changeModify()" /> /
        <b-icon icon="pencil-square" @click="deleteCmt()" />
      </b-col>
      <b-col v-else cols="1"> </b-col>
    </b-row>
  </div>

  <div v-else id="modifyForm" class="m-2">
    <b-row>
      <b-col cols="auto" class="mr-auto mb-2"
        >작성자 | {{ userInfo.userId }}</b-col
      >
    </b-row>
    <b-row>
      <b-col cols="10"><b-form-input type="text" v-model="modifyCmt" /> </b-col>
      <b-col
        ><b-button size="sm" class="mr-1" @click="doModify()"> 수정</b-button
        ><b-button size="sm" @click="changeModify()"> 취소</b-button></b-col
      >
    </b-row>
  </div>
</template>

<script>
import { deleteComment, modifyComment } from "@/api/comment";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      isModify: false,
      modifyCmt: this.comment.content,
    };
  },
  props: {
    comment: Object,
  },
  filters: {
    date(value) {
      return value.slice(5, 16);
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    deleteCmt() {
      deleteComment(
        this.comment.no,
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
          // eslint-disable-next-line
        }
      );
    },
    changeModify() {
      this.isModify = !this.isModify;
    },
    doModify() {
      this.changeModify();
      const params = { no: this.comment.no, content: this.modifyCmt };
      modifyComment(
        params,
        ({ data }) => {
          console.log(data);
          this.$emit("update-comment");
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line
        }
      );
    },
  },
};
</script>

<style scoped>
#commentItem {
  text-decoration: none;
  color: #7c7c7d;
  border-bottom: 1px solid #ced4da;
}
.link {
  text-decoration: none;
  color: #7c7c7d;
}
#modifyForm {
  border: 1px dashed #ced4da;
  border-radius: 10px;
  padding: 10px;
  color: #7d7d7d;
}
</style>
