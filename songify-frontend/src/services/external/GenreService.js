import axios from 'axios'
import TokenService from './TokenService';

const SPOTIFY_API_URL = 'https://api.spotify.com/v1/browse/categories';

const TOKEN = TokenService.getToken();

class GenreService{

    getGenres(){
        return axios(SPOTIFY_API_URL, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQD8t-aYFvd-rrdb0vNOiMaT2SDcjsFPsQWBVN8_vF0lhUHGKbE7IxQm806NW1VhuY-y-6gxhTizfdnIJ_c' }
        })
    }
}

export default new GenreService();