// notice controll
import { apiInstance } from "./http.js";

const api = apiInstance();

function listNotice(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}
function writeNotice(notice, success, fail) {
  api.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}
function getNotice(noticeNo, success, fail) {
  api.get(`/notice/${noticeNo}`).then(success).catch(fail);
}
function modifyNotice(notice, success, fail) {
  api
    .put(`/notice/${notice.noticeNo}`, JSON.stringify(notice))
    .then(success)
    .catch(fail);
}
function deleteNotice(noticeNo, success, fail) {
  api.delete(`/notice/${noticeNo}`).then(success).catch(fail);
}
export { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice };
