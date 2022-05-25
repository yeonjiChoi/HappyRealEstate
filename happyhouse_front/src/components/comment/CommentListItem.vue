<template>
  <div v-if="!isModify" id="commentItem" class="ml-3 mt-1 p-2">
    <b-row align-v="center">
      <b-col class="text-center">
        <b-icon icon="person" /> {{ comment.userId }}
      </b-col>
      | <b-col cols="7"> {{ comment.content }} </b-col> |
      <b-col class="text-center" cols="2"> {{ comment.regDate | date }} </b-col>
      |
      <b-col
        cols="1"
        v-if="userInfo != null && userInfo.userId == comment.userId"
      >
        <b-icon icon="pencil-square" @click="changeModify()" /> /
        <b-icon icon="x-square" @click="deleteCmt()" />
      </b-col>
      <b-col v-else cols="1"> </b-col>
    </b-row>
  </div>

  <div v-else id="modifyForm" class="m-2">
    <b-row>
      <b-col cols="auto" class="mb"> [작성자 |{{ userInfo.userId }}]</b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-input-group class="mt-1">
          <b-form-input type="text" v-model="modifyCmt" />
          <b-input-group-append>
            <b-button variant="outline-success" size="sm" @click="doModify()">
              수정</b-button
            >
            <b-button
              variant="outline-warning"
              size="sm"
              @click="changeModify()"
            >
              취소</b-button
            >
          </b-input-group-append>
        </b-input-group>
      </b-col>
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
  border-bottom: 1px solid #ced4da;
}
.link {
  text-decoration: none;
}
#modifyForm {
  border: 1px dashed #b6babd;
  border-radius: 10px;
  padding: 10px;
}
</style>
