// HouseMapCon
import { apiInstance } from "./http.js";

const api = apiInstance();

function insertInterestArea(params, success, fail) {
  api.post(`/interest/area`, params).then(success).catch(fail);
}

function selectInterestArea(params, success, fail) {
  api.get(`/interest/area`, { params: params }).then(success).catch(fail);
}

function deleteInterestArea(params, success, fail) {
  api
    .delete(`/interest/area/` + params)
    .then(success)
    .catch(fail);
}

function insertInterestApt(params, success, fail) {
  api.post(`/interest/apt`, params).then(success).catch(fail);
}

function selectInterestApt(params, success, fail) {
  api.get(`/interest/apt`, { params: params }).then(success).catch(fail);
}

function deleteInterestApt(params, success, fail) {
  api
    .delete(`/interest/apt/` + params)
    .then(success)
    .catch(fail);
}

export {
  insertInterestArea,
  selectInterestArea,
  deleteInterestArea,
  insertInterestApt,
  selectInterestApt,
  deleteInterestApt,
};
