import axios from 'axios'

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
                desc: playlistDesc
            }
        })
    }
}

export default new PlaylistService();