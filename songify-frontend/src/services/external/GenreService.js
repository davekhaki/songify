import axios from 'axios'
import TokenService from './TokenService';

const SPOTIFY_API_URL = 'https://api.spotify.com/v1/browse/categories';

var TOKEN;
TokenService.getToken().then(value => TOKEN = value);

class GenreService{

    getGenres(){
        return axios(SPOTIFY_API_URL, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQBuBJwDVsJqibA-bB2dEKMLeVipkseKFqcJBPu6U3HKDmJnHH5CFVblckCuUutyn3TdOSmETHgJKHVOtbk' }
        })
    }

    test(){
        return axios('https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?album_type=SINGLE&offset=20&limit=10', {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQBuBJwDVsJqibA-bB2dEKMLeVipkseKFqcJBPu6U3HKDmJnHH5CFVblckCuUutyn3TdOSmETHgJKHVOtbk'}
        })
    }
}

export default new GenreService();