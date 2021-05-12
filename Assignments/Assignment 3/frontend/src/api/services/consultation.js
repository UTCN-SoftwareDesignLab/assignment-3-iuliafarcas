import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allConsultations() {
        return HTTP.get(BASE_URL + "/clinic/consultation", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(consultation) {
        return HTTP.post(BASE_URL + "/clinic/consultation", consultation, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(consultation) {
        return HTTP.patch(BASE_URL + `/clinic/consultation/${consultation.id}`, consultation, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(consultation) {
        return HTTP.delete(BASE_URL + `/clinic/consultation/${consultation.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};
