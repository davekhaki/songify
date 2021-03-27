import axios from 'axios'
import TokenService from './TokenService';

const SPOTIFY_API_URL = 'https://api.spotify.com/v1/browse/categories';

const TOKEN = TokenService.getToken();

class GenreService{

    getGenres(){
        return axios(SPOTIFY_API_URL, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQBkaehuyyHEQvV9nvK56oUbfKK8Kuy5kCWjnGgC9ycELFlGthb_SsHYtR3EwmUK6I3dYI3BBYG68GRVatE' }
        })
    }
}

export default new GenreService();