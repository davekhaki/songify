import axios from 'axios'

const config = require('../config.json');

class PlaylistService{

    getPlaylists(){
        return axios.get(config.REST_API_URL + "playlists");
    }

    getPopularPlaylists(){
        return axios.get(config.REST_API_URL + "playlists/popular");
    }
}

export default new PlaylistService();