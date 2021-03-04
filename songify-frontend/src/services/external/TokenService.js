import axios from 'axios'
import { Credentials } from './Credentials';

const spotify = Credentials();

class TokenService{

    getToken(){
        return axios('https://accounts.spotify.com/api/token', {
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded',
                'Authorization' : 'Basic ' + btoa(spotify.ClientId + ':' + spotify.ClientSecret)      
              },
              data: 'grant_type=client_credentials',
              method: 'POST'
        })
    }
}

export default new TokenService();