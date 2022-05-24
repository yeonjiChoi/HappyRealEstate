import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseDealList,
  houseListPagination,
  getTotalAptCount,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    houseDeals: [],
    house: null,
    latlng: { lat: null, lng: null },
    totalAptCnt: null,
  },
  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      console.log(sidos);
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      console.log("SET DONG LIST, ", dongs);
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_HOUSE_DEAL_LIST: (state, deals) => {
      console.log("SET HOUSE DEAL LIST, ", deals);
      state.houseDeals = deals;
    },
    CLEAR_HOUSES_LIST: (state) => {
      state.houses = [];
    },
    CLEAR_HOUSE: (state) => {
      console.log("CLEAR HOUSE");
      state.house = null;
    },
    SET_LAT_LNG: (state, house) => {
      state.latlng = [{ lat: house.lat, lng: house.lng }];
      console.log("SET LATLNG ", state.latlng);
    },
    SET_TOTAL_APT_CNT: (state, aptCnt) => {
      state.totalAptCnt = aptCnt;
      console.log("SEt_TOTAL_APT_CNT", aptCnt);
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const params = { dong: dongCode };
      houseList(
        params,
        ({ data }) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList2: ({ commit }, dongCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const params = { page: 0, dongCode: dongCode };
      console.log(params);
      houseListPagination(
        params,
        ({ data }) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    getHouseDealList: ({ commit }, house) => {
      const params = { aptCode: house.aptCode };
      houseDealList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_DEAL_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    setLatLng: ({ commit }, house) => {
      commit("SET_LAT_LNG", house);
    },

    getTotAptCnt: ({ commit }, dongCode) => {
      const params = { dongCode: dongCode };
      getTotalAptCount(
        params,
        ({ data }) => {
          commit("SET_TOTAL_APT_CNT", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default houseStore;
