import axios from 'axios'
import authHeader from './auth/auth-header';

const config = require('../../config.json');

class RoleService{

    getRoles(){
        return axios({
            method: 'get',
            url: config.REST_API_URL + "roles",
            headers: authHeader()
        })
    }

    getRoleById(id){
        return axios({
            method: 'get',
            url: config.REST_API_URL + "roles/" + id,
            headers: authHeader()
        })
    }

    addRole(roleName){
        return axios({
            method: 'post',
            url: config.REST_API_URL + "roles",
            headers: authHeader(),
            data: {
                name: roleName
            }
        })
    }

    updateRole(id, name){
        return axios({
            method: 'put',
            url: config.REST_API_URL + "roles/" + id,
            headers: authHeader(),
            data: { 
                name: name
            }
        })
    }
}

export default new RoleService();
