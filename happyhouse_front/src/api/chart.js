import { apiInstance } from "./http.js";

const api = apiInstance();
function getChartData(success, fail) {
  api.get(`/chart`).then(success).catch(fail);
}

export { getChartData };
