// Member Controll
import { apiInstance } from "./http.js";

const api = apiInstance();
// 회원 전체 목록
function MemberList(success, fail) {
  api.get(`/user`).then(success).catch(fail);
}
// 회원 등록
function MemberRegist(Member, succes, fail) {
  api.post(`/user`, JSON.stringify(Member)).then(succes).catch(fail);
}
// 회원 정보 수정
function MemberRegist() {}
// 회원 삭제
function MemeberDel() {}
// 회원 정보 권한 수정
function MemberFixAuth() {}
// 아이디 중복 검사
function checkId() {}
// 회원 인증
function MemberAuth() {}
// 로그인
function doLogin(userId, succes, fail) {
  api.post(`/user/login`).then(succes).catch(fail);
}
export { login, findById };
