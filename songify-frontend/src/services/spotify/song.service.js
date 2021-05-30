import axios from 'axios'

import spotifyHeader from './auth/token.header';

const config = require('../../config.json');

class SongService{

    getSongsByIds(ids){
        return axios({
            method: 'get',
            url: config.SPOTIFY_API_URL + 'tracks?ids=' + ids,
            headers: spotifyHeader()
        })
    }
}

export default new SongService();