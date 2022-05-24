import {
  insertInterestArea,
  selectInterestArea,
  deleteInterestArea,
  insertInterestApt,
  selectInterestApt,
  deleteInterestApt,
} from "@/api/interest";

const interestStore = {
  namespaced: true,
  state: {
    interestAreas: [],
    interestApts: [],
    latlng: { lat: null, lng: null },
  },
  getters: {},
  mutations: {
    SUCCESS_SET_INTEREST_AREA: (data) => {
      console.log(data);
    },
    SET_INTEREST_AREA: (state, interestAreas) => {
      state.interestAreas = interestAreas;
    },
    DELETE_INTEREST_AREA: (state, area) => {
      var index;
      state.interestAreas.forEach(function (item, i) {
        if (item.no == area.no) {
          index = i;
        }
      });
      state.interestAreas.splice(index, 1);

      // console.log(state.interestAreas, area);
      // const index = state.interestAreas.indexOf(area.no);
      // console.log(index);
      // state.interestAreas.splice(index, 1);
    },
    SET_LAT_LNG: (state, data) => {
      state.latlng = [{ lat: data.lat, lng: data.lng }];
      console.log("SET LATLNG ", state.latlng);
    },
    SUCCESS_SET_INTEREST_APT: (data) => {
      console.log(data);
    },
    SET_INTEREST_APT: (state, data) => {
      state.interestApts = data;
    },
    DELETE_INTEREST_APT: (state, apt) => {
      var index;
      state.interestApts.forEach(function (item, i) {
        if (item.no == apt.no) {
          index = i;
        }
      });
      state.interestApts.splice(index, 1);
      // console.log(state.interestApts, apt);
      // const index = state.interestApts.indexOf(apt.no);
      // console.log(index);
      // state.interestApts.splice(index, 1);
    },
  },
  actions: {
    setInterArea: ({ commit }, interestArea) => {
      insertInterestArea(
        interestArea,
        ({ data }) => {
          commit("SUCCESS_SET_INTEREST_AREA", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getInterArea: ({ commit }, user) => {
      const params = { userId: user };
      selectInterestArea(
        params,
        ({ data }) => {
          commit("SET_INTEREST_AREA", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteInterArea: ({ commit }, area) => {
      console.log("deleteInterestArea 호출 : ", area.no);
      deleteInterestArea(
        area.no,
        ({ data }) => {
          console.log(data);
          commit("DELETE_INTEREST_AREA", area);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    setLatLng: ({ commit }, data) => {
      commit("SET_LAT_LNG", data);
    },
    setInterApt: ({ commit }, interestApt) => {
      console.log(interestApt);
      insertInterestApt(
        interestApt,
        ({ data }) => {
          commit("SUCCESS_SET_INTEREST_APT", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getInterApt: ({ commit }, user) => {
      const params = { userId: user };
      selectInterestApt(
        params,
        ({ data }) => {
          commit("SET_INTEREST_APT", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteInterApt: ({ commit }, apt) => {
      console.log("deleteInterestApt 호출 : ", apt.no);
      deleteInterestApt(
        apt.no,
        ({ data }) => {
          console.log(data);
          commit("DELETE_INTEREST_APT", apt);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
export default interestStore;
