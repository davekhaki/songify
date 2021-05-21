import axios from 'axios'

const config = require('../config.json');

class UserService{

    getUsers(){
        return axios.get(config.REST_API_URL + "users");
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
        return axios.put(config.REST_API_URL + "users/" + id, user)
    }

    deleteUser(id){
        return axios.delete(config.REST_API_URL + "users/" + id)
    }
}

export default new UserService();