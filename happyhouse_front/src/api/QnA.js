// QnA controll
import { apiInstance } from "./http.js";

const api = apiInstance();

function listQnA(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}
function writeQnA(QnA, success, fail) {
  api.post(`/qna`, JSON.stringify(QnA)).then(success).catch(fail);
}
function getQnA(qnaNo, success, fail) {
  api.get(`/qna/${qnaNo}`).then(success).catch(fail);
}
function modifyQnA(QnA, success, fail) {
  api.put(`/qna/${QnA.qnaNo}`, JSON.stringify(QnA)).then(success).catch(fail);
}
function deleteQnA(qnaNo, success, fail) {
  api.delete(`/qna/${qnaNo}`).then(success).catch(fail);
}
export { listQnA, writeQnA, getQnA, modifyQnA, deleteQnA };
