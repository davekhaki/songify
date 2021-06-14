import axios from 'axios'
import authHeader from './auth/auth-header';

const config = require('../../config.json');

class UserService{

    getUsers(){
        return axios({
            method: 'get',
            url: config.REST_API_URL + "users",
            headers: authHeader()
        })
    }

    getUserById(id){
        return axios.get(config.REST_API_URL + "users/" + id)
    }

    addUser(usernameParam, passwordParam, emailParam){
        axios({
            method: 'post',
            url: config.REST_API_URL + "users/",
            data: {
                username: usernameParam,
                password: passwordParam,
                email: emailParam,
                role: "USER"
            }
        })
    }

    updateUser(user, id){
        return axios({
            method: 'put',
            url: config.REST_API_URL + "users/" + id,
            headers: authHeader(),
            data: {
                id: id,
                user: user
            }
        })
        return axios.put(config.REST_API_URL + "users/" + id, user)
    }

    deleteUser(id){
        return axios({
            method: 'delete',
            url: config.REST_API_URL + "users/" + id,
            headers: authHeader()
        })
    }

    async getUsername(id){
        return await axios.get(config.REST_API_URL + 'users/username/' + id)
    }
}

export default new UserService();