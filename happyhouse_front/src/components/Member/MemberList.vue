<template>
  <b-container class="bv-example-row mt-3">
    <b-row
      class="mb-1"
      v-if="userInfo != null && userInfo.authority === 'ADMIN'"
    >
    </b-row>
    <b-row>
      <b-table
        striped
        hover
        :per-page="perPage"
        :current-page="currentPage"
        :items="members"
        :fields="fields"
        ><template v-slot:cell(DelOrAutho)="{ item }">
          <span v-if="item.authority === 'USER'"
            ><b-button
              size="sm"
              variant="outline-info"
              @click="AuthModify(item)"
              >사용자</b-button
            >
          </span>
          <span v-if="item.authority === 'ADMIN'"
            ><b-button
              size="sm"
              variant="outline-info"
              @click="AuthModify(item)"
              >관리자</b-button
            >
          </span>
          |
          <span
            ><b-button
              size="sm"
              variant="outline-danger"
              @click="DeleteMember(item)"
              >삭제</b-button
            >
          </span>
        </template>
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
      ></b-pagination>
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
        { key: "userId", label: "아이디" },
        { key: "email", label: "이메일" },
        { key: "userName", label: "이름" },
        { key: "joinDate", label: "가입일" },
        { key: "DelOrAutho", label: "권한 | 삭제" },
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
        // eslint-disable-next-line
      }
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
          // eslint-disable-next-line
        }
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
          // eslint-disable-next-line
        }
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
