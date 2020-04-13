import axios from "axios";

export default class ContactService {
  baseUrl = `http://localhost:8083/api/contacts/`;

  getAll() {
    return axios
      .get(this.baseUrl + "all")
      .then((res) => res.data)
      .catch((error) => {
        console.log(error);
      });
  }

  getByText(textToSearch) {
    return axios
      .get(this.baseUrl + `?text=${textToSearch}`)
      .then((res) => res.data)
      .catch((error) => {
        console.log(error);
      });
  }

  save(contact) {
    return axios.post(this.baseUrl, contact).then((res) => res.data);
  }
}
