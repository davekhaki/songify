import axios from 'axios'
import TokenService from './TokenService';

const SPOTIFY_API_URL = 'https://api.spotify.com/v1/browse/categories';

var TOKEN;
TokenService.getToken().then(value => TOKEN = value);

class GenreService{

    getGenres(){
        return axios(SPOTIFY_API_URL, {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQAn96U0D0sXDndN3cpw1NAp8O-R2EU-EgAYnRBJjET8vRD3xuZnZqw82HeTZ5kBV8Lh89e0YQM9CdcxOdk' }
        })
    }

    test(){
        return axios('https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?album_type=SINGLE&offset=20&limit=10', {
            method: 'GET',
            headers: { 'Authorization' : 'Bearer BQAn96U0D0sXDndN3cpw1NAp8O-R2EU-EgAYnRBJjET8vRD3xuZnZqw82HeTZ5kBV8Lh89e0YQM9CdcxOdk'}
        })
    }
}

export default new GenreService();