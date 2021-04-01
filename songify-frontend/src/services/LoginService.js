import axios from 'axios'
import md5 from 'md5';

const LOGIN_REST_API_URL = 'http://localhost:8080/v1/api/login';

class LoginService{

    tryLogin(username, password){
        console.log( axios.post(LOGIN_REST_API_URL + "?username=" + username + "&pass_hash=" + md5(password)));
        return axios.post(LOGIN_REST_API_URL + "?username=" + username + "&pass_hash=" + md5(password))
    }
}

export default new LoginService();