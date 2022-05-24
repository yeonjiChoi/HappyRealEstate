import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeModify from "@/components/notice/NoticeModify.vue";
import store from "@/store/index.js";
Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
  } else {
    next();
  }
};
const isAdmin = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
  } else if (checkUserInfo.authority === "USER") {
    alert("권한이 없습니다");
  } else {
    next();
  }
};
const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },

  // 로그인 로그아웃
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/Member/MemberLogin.vue"),
      },
      {
        path: "register",
        name: "register",
        component: () => import("@/components/Member/MemberRegister.vue"),
      },
      {
        path: "memberInfo",
        name: "memberInfo",
        component: () => import("@/components/Member/MemberMyPage.vue"),
      },
    ],
  },
  // 공지사항
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: NoticeList,
      },
      {
        path: "noticeDetail/:noticeNo",
        name: "noticeDetail",
        component: NoticeDetail,
      },
      {
        path: "noticeModify/:noticeNo",
        name: "noticeModify",
        beforeEnter: isAdmin,
        isAdmin,
        component: NoticeModify,
      },
      {
        path: "noticeWrite",
        name: "noticeWrite",
        beforeEnter: isAdmin,
        isAdmin,
        component: () => import("@/components/notice/NoticeWrite.vue"),
      },
    ],
  },
  // QnA
  {
    path: "/QnA",
    name: "QnA",
    component: () => import("@/views/QnAView.vue"),
    redirect: "/QnA/list",
    children: [
      {
        path: "list",
        name: "QnAlist",
        component: () => import("@/components/QnA/QnAList.vue"),
      },
      {
        path: "QnADelete",
        name: "QnADelete",
        component: () => import("@/components/QnA/QnADelete.vue"),
      },
      {
        path: "QnADetail",
        name: "QnADetail",
        component: () => import("@/components/QnA/QnADetail.vue"),
      },
      {
        path: "QnAModify",
        name: "QnAModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/QnA/QnAModify.vue"),
      },
      {
        path: "QnAWrite",
        name: "QnAWrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/QnA/QnAWrite.vue"),
      },
    ],
  },
  // APT List
  {
    path: "/House",
    name: "House",
    component: () => import("@/views/HouseInfoView.vue"),
    redirect: "/House/list",
    children: [
      {
        path: "list",
        name: "HouseList",
        component: () => import("@/components/House/HouseInfoList.vue"),
      },
    ],
  },
  {
    path: "/InterestHouse",
    name: "InterestHouse",
    component: () => import("@/views/InterestHouseView.vue"),
    redirect: "/InterestHouse/list",
    children: [
      {
        path: "list",
        name: "InterestHouseList",
        component: () =>
          import("@/components/InterestHouse/InterestHouseList.vue"),
      },
    ],
  },
  {
    path: "/InterestArea",
    name: "InterestArea",
    component: () => import("@/views/InterestAreaView.vue"),
    redirect: "/InterestArea/list",
    children: [
      {
        path: "list",
        name: "InterestAreaList",
        component: () =>
          import("@/components/InterestArea/InterestAreaList.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
