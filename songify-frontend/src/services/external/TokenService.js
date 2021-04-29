import axios from 'axios'

const config = require('../../config.json');

class TokenService{

    getToken(){
        console.log(btoa(config.SPOTIFY_CREDENTIALS.CLIENT_ID + ':' + config.SPOTIFY_CREDENTIALS.CLIENT_SECRET))
        return axios('https://accounts.spotify.com/api/token', {
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded',
                'Authorization' : 'Basic ' + btoa(config.SPOTIFY_CREDENTIALS.CLIENT_ID + ':' + config.SPOTIFY_CREDENTIALS.CLIENT_SECRET)      
              },
              data: 'grant_type=client_credentials',
              method: 'POST'
        })
    }
}

export default new TokenService();