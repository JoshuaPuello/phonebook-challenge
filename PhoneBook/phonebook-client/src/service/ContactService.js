import axios from 'axios';

export default class ContactService {

    baseUrl = 'http://localhost:8083/api/contacts/';

    getAll() {
        return axios.get(this.baseUrl + "all")
        .then(res => res.data);
    }

    save(contact) {
        return axios.post(this.baseUrl, contact)
        .then(res => res.data);
    }

}