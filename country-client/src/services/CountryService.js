import http from "../http-common";

const getAll = () => {
  return http.get("/v1/countries");
};
const process = () => {
  return http.post(`/v1/countries/process`);
};

const findByCc2 = (cc2) => {
  return http.get(`/v1/countries/cc2/${cc2}`);
};

const CountryService = {
  getAll,
  process,
  findByCc2
};

export default CountryService;
