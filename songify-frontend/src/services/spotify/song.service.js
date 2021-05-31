import axios from 'axios'

import spotifyHeader from './auth/token.header';

const config = require('../../config.json');

class SongService{

    searchForSongs(searchTerm){
        return axios({
            method: 'get',
            url: config.SPOTIFY_API_URL + 'search?q=' + searchTerm + '&type=track&market=us&limit=10&offset=5',
            headers: spotifyHeader()
        })
    }

    getSongsByQueryUrl(url){
        return axios({
            method: 'get',
            url: url,
            headers: spotifyHeader()
        })
    }

    getSongsByIds(ids){
        return axios({
            method: 'get',
            url: config.SPOTIFY_API_URL + 'tracks?ids=' + ids,
            headers: spotifyHeader()
        })
    }
}

export default new SongService();