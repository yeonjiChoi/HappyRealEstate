// Member Controll
import { apiInstance } from "./http.js";

const api = apiInstance();
// 회원 전체 목록
// function MemberList(success, fail) {
//   api.get(`/user`).then(success).catch(fail);
// }
// 회원 등록
function MemberRegist(Member, success, fail) {
  api.post(`/user`, JSON.stringify(Member)).then(success).catch(fail);
}
// 회원 정보 수정
//function MemberModify() {}
// 회원 삭제
//function MemeberDel() {}
// 회원 정보 권한 수정
//function MemberFixAuth() {}
// 아이디 중복 검사
function checkId(userId, success, fail) {
  api.get(`/user/checkId/${userId}`).then(success).catch(fail);
}
// 회원 인증
//function MemberAuth() {}
// 로그인
async function login(Member, succes, fail) {
  await api
    .post(`/user/login`, JSON.stringify(Member))
    .then(succes)
    .catch(fail);
}
async function findById(userId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userId}`).then(success).catch(fail);
}

export { MemberRegist, checkId, login, findById };
