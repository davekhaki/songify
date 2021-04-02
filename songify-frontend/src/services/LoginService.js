import axios from 'axios'
import md5 from 'md5';

const config = require('../config.json');

class LoginService{

    tryLogin(username, password){
        console.log( axios.post(config.REST_API_URL + "login" + "?username=" + username + "&passHash=" + md5(password)));
        return axios.post(config.REST_API_URL + "login" + "?username=" + username + "&passHash=" + md5(password))
    }
}

export default new LoginService();