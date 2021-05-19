import axios from 'axios'

const config = require('../../config.json');

class AuthService{

    register(username, password, email){
        axios({
            method: 'post',
            url: config.REST_API_URL + "users/",
            data: {
                username: username,
                password: password,
                email: email,
                role: "USER"
            }
        })
    }

    login(username, password) {
        console.log("given username: " + username + "given password " + password);
        return axios
          .post(config.REST_API_URL + "login/", {
            username,
            password
          })
          .then(response => {
            if (response.data.id) {
                console.log("given username: " + username + "given password " + password);
                this.refreshToken(username, password);
                localStorage.setItem("user", JSON.stringify(response.data)); 
            }
    
            return response.data;
          });
      }

    logout() { 
        localStorage.removeItem("user");
        window.location.reload();
    }

    refreshToken(username, password){
        return axios
        .post("http://localhost:8080/login", {
          username,
          password
        })
        .then(response => {
          if (response.data.Authorization) {
              localStorage.setItem("JWTToken", JSON.stringify(response.data));
          }
          return response.data;
        });
    }

    getToken(){
        console.log("JWT TOKEN: " + localStorage.getItem("JWTToken"));
        return JSON.parse(localStorage.getItem("JWTToken"));
    }

    getCurrentUser(){
        return JSON.parse(localStorage.getItem("user"));
    }
}

export default new AuthService();