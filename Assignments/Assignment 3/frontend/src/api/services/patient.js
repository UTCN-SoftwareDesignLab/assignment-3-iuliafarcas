import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allPatients() {
    return HTTP.get(BASE_URL + "/clinic/patient", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(patient) {
    return HTTP.post(BASE_URL + "/clinic/patient", patient, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(patient) {
    return HTTP.patch(BASE_URL + `/clinic/patient/${patient.id}`, patient, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(patient) {
    return HTTP.delete(BASE_URL + `/clinic/patient/${patient.id}`, { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
};
