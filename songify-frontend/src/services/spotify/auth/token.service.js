import axios from 'axios'

const config = require('../../../config.json');

class TokenService{

    refreshToken(){
        axios(config.SPOTIFY_AUTH_URL, {
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded',
                'Authorization' : 'Basic ' + btoa(config.SPOTIFY_CREDENTIALS.CLIENT_ID + ':' + config.SPOTIFY_CREDENTIALS.CLIENT_SECRET)      
              },
              data: 'grant_type=client_credentials',
              method: 'POST'
        }).then((response) => {
            localStorage.setItem("token", JSON.stringify(response.data.token_type + " " + response.data.access_token)); 
        })
    }

    getCurrentToken(){
        this.refreshToken();
        return JSON.parse(localStorage.getItem("token"));
    }
}

export default new TokenService();