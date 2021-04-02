import axios from 'axios'

const config = require('../config.json');

class UserService{

    getUsers(){
        return axios.get(config.REST_API_URL + "users");
    }
}

export default new UserService();