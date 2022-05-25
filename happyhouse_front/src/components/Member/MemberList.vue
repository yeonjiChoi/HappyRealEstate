<template>
  <b-container class="bv-example-row mt-3">
    <b-row
      class="mb-1"
      v-if="userInfo != null && userInfo.authority === 'ADMIN'"
    >
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :per-page="perPage"
          :current-page="currentPage"
          :items="members"
          :fields="fields"
          ><template v-slot:cell(DelOrAutho)="{ item }">
            <span><b-btn @click="DeleteMember(item)">Delete</b-btn> </span>
            <span v-if="item.authority === 'USER'"
              ><b-btn @click="AuthModify(item)">관리자 지정</b-btn>
            </span>
            <span v-if="item.authority === 'ADMIN'"
              ><b-btn @click="AuthModify(item)">관리자 지정 해제</b-btn>
            </span>
          </template>
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
import { MemberList, MemeberDel, MemberModify } from "@/api/member.js";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberList",
  data() {
    return {
      members: [],
      fields: [
        { key: "userId", label: "ID" },
        { key: "userPwd", label: "PassWord" },
        { key: "email", label: "Email" },
        { key: "userName", label: "이름" },
        { key: "joinDate", label: "가입일" },
        { key: "DelOrAutho", label: "삭제/관리자 지정" },
      ],
      perPage: 10,
      currentPage: 1,
    };
  },
  created() {
    MemberList(
      (response) => {
        this.members = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    memberDetail(member) {
      this.$router.push({
        name: "memberDetail",
        params: { userId: member.userId },
      });
    },
    DeleteMember(member) {
      console.log(member.userId);
      MemeberDel(
        member.userId,
        ({ data }) => {
          let msg = "멤버 삭제 오류";
          if (data === "success") {
            msg = "삭제 완료";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    AuthModify(member) {
      console.log("Before : " + member.authority);
      member.authority = member.authority === "ADMIN" ? "USER" : "ADMIN";
      console.log("After : " + member.authority);

      MemberModify(
        member,
        ({ data }) => {
          let msg = "권한 조정 오류";
          if (data === "success") {
            msg = "권한 조정 완료";
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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.members.length;
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
