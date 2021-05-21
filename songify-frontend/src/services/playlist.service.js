import axios from 'axios'
import AuthService from './auth/auth.service';

const config = require('../config.json');

class PlaylistService{

    getPlaylists(){
        return axios.get(config.REST_API_URL + "playlists");
    }

    getPopularPlaylists(){
        return axios.get(config.REST_API_URL + "playlists/popular");
    }

    addPlaylist(playlistTitle, playlistDesc){
        axios({
            method: 'post',
            url: config.REST_API_URL + 'playlists',
            data: {
                title: playlistTitle,
                desc: playlistDesc,
                username: AuthService.getCurrentUser().username
            }
        })
    }

    getMyPlaylists(){
        return axios({
            method: 'get',
            url: config.REST_API_URL + 'playlists/' + AuthService.getCurrentUser().username,

        })
    }
}

export default new PlaylistService();