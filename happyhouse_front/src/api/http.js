import axios from "axios";
import { baseURL } from "@/config";
// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: baseURL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance };
