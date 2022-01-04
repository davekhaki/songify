import axios from 'axios'

const config = require('../../../config.json');

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
        return axios
          .post(config.REST_API_URL + "auth/", {
            username,
            password
          })
          .then(response => {
            if (response.data.id) {
                this.refreshToken(username, password).then((response)=>{
                    console.log(response);
                })
                var user = response.data;
                user.password = "XXXXXXXX"
                localStorage.setItem("user", JSON.stringify(user)); 
            }
    
            return response.data;
          });
      }

    logout() { 
        localStorage.removeItem("user");
        localStorage.removeItem("bearer");
        window.location.reload();
    }

    refreshToken(username, password){
        return axios({
            method: 'post',
            url: "http://localhost:8080/login",
            data: {
                username: username,
                password: password
            }
        }).then((response) => {
          if (response) {
              localStorage.setItem("bearer", JSON.stringify(response.data));
          }
          return response.data;
        });
    }

    getToken(){
        console.log("JWT TOKEN: " + localStorage.getItem("bearer"));
        return JSON.parse(localStorage.getItem("bearer"));
    }

    getCurrentUser(){
        return JSON.parse(localStorage.getItem("user"));
    }
}

export default new AuthService();