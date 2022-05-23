// HouseMapCon
import { apiInstance } from "./http.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function houseDealList(params, success, fail) {
  api.get(`/map/deal`, { params: params }).then(success).catch(fail);
}

function insertInterestArea(params, success, fail) {
  api.post(`/interest/area`, { params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseDealList,
  insertInterestArea,
};
