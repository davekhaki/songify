import axios from 'axios'

const config = require('../config.json');

class RoleService{

    getRoles(){
        return axios.get(config.REST_API_URL + "roles");
    }
}

export default new RoleService();




























