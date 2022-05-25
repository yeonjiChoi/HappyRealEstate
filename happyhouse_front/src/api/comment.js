import { apiInstance } from "./http.js";

const api = apiInstance();

function listComment(qnaNo, success, fail) {
  api.get(`/qna/comment/${qnaNo}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api.post(`/qna/comment`, JSON.stringify(comment)).then(success).catch(fail);
}
function modifyComment(params, success, fail) {
  api
    .put(`/qna/comment/${params.no}`, JSON.stringify(params))
    .then(success)
    .catch(fail);
}
function deleteComment(no, success, fail) {
  api.delete(`/qna/comment/${no}`).then(success).catch(fail);
}

export { listComment, writeComment, modifyComment, deleteComment };
