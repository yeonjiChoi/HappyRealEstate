import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeDelete from "@/components/notice/NoticeDelete.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeModify from "@/components/notice/NoticeModify.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },

  // 로그인 로그아웃
  {
    path: "/login",
    name: "login",
    component: LoginView,
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
        path: "/noticeDelete",
        name: "noticeDelete",
        component: NoticeDelete,
      },
      {
        path: "/noticeDetail",
        name: "noticeDetail",
        component: NoticeDetail,
      },
      {
        path: "/noticeModify",
        name: "noticeModify",
        component: NoticeModify,
      },
      {
        path: "/noticeWrite",
        name: "noticeWrite",
        component: NoticeWrite,
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
        path: "/QnADelete",
        name: "QnADelete",
        component: () => import("@/components/QnA/QnADelete.vue"),
      },
      {
        path: "/QnADetail",
        name: "QnADetail",
        component: () => import("@/components/QnA/QnADetail.vue"),
      },
      {
        path: "/QnAModify",
        name: "QnAModify",
        component: () => import("@/components/QnA/QnAModify.vue"),
      },
      {
        path: "/QnAWrite",
        name: "QnAWrite",
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
