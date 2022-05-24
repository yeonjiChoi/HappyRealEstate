import { apiInstance } from "./http.js";

const api = apiInstance();

function getNewsList(params, success, fail) {
  api.get(`/news/${params.searchval}`).then(success).catch(fail);
}

export { getNewsList };
