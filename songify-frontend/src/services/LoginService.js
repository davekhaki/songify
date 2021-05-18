import axios from 'axios'

const config = require('../config.json');

class LoginService{
    //REMOVE ENTIRE CLASS
    tryLogin(username, password){
        return axios({
            method: 'post',
            url: config.REST_API_URL + "login/",
            data: {
                username: username,
                password: password,
            }
        })
    }
}

export default new LoginService();