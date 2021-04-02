import axios from 'axios'
import TokenService from './TokenService';

const SPOTIFY_API_URL = 'https://api.spotify.com/v1/browse/categories';

var TOKEN;
TokenService.getToken().then(value => TOKEN = value);

class GenreService{

    getGenres(){
        return axios(SPOTIFY_API_URL, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQBU-LWoVrWTOS2vR5UY3TTVy8ZZcfVKFzz67b1uOoHWHnLpV8qs6W-i9vq6CkWQNRCnRWpG5O5XakZaB7M' }
        })
    }

    test(){
        return axios('https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?album_type=SINGLE&offset=20&limit=10', {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQBU-LWoVrWTOS2vR5UY3TTVy8ZZcfVKFzz67b1uOoHWHnLpV8qs6W-i9vq6CkWQNRCnRWpG5O5XakZaB7M'}
        })
    }
}

export default new GenreService();