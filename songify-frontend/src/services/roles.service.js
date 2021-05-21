import axios from 'axios'

const config = require('../config.json');

class RoleService{

    getRoles(){
        return axios.get(config.REST_API_URL + "roles");
    }

    getRoleById(id){
        return axios({
            method: 'get',
            url: config.REST_API_URL + "roles/" + id
        })
    }

    addRole(roleName){
        return axios({
            method: 'post',
            url: config.REST_API_URL + "roles",
            data: {
                name: roleName
            }
        })
    }

    updateRole(id, name){
        return axios({
            method: 'put',
            url: config.REST_API_URL + "roles/" + id,
            data: { 
                name: name
            }
        })
    }
}

export default new RoleService();




























