import axios from 'axios'
import AuthService from './auth/auth.service';
import authHeader from './auth/auth-header';

const config = require('../../config.json');

class PlaylistService{

    getPlaylists(){
        console.log("Auth Header: " + authHeader())
        return axios({
            method: 'get',            
            url: config.REST_API_URL + "playlists",
            headers: authHeader(),
        });
    }

    getPlaylistById(id){
        return axios({
            method: 'get',
            url: config.REST_API_URL + "playlists/id/" + id,
            headers: authHeader(),
        })
    }

    getPopularPlaylists(){
        return axios.get(config.REST_API_URL + "playlists/popular");
    }

    addPlaylist(playlistTitle, playlistDesc){
        return axios({
            method: 'post',
            url: config.REST_API_URL + 'playlists',
            headers: authHeader(),
            data: {
                title: playlistTitle,
                desc: playlistDesc,
                username: AuthService.getCurrentUser().username
            }
        })
    }

    deletePlaylist(id){
        return axios({
            method: 'delete',
            url: config.REST_API_URL + 'playlists/' + id,
            headers: authHeader(),
        })
    }

    getMyPlaylists(){
        console.log("Auth Header !!!!: " + authHeader())
        return axios({
            method: 'get',
            url: config.REST_API_URL + 'playlists/' + AuthService.getCurrentUser().username,
            headers: authHeader(),
        })
    }

    getPlaylistsByUsername(username){
        console.log("auth header(): ")
        console.log(authHeader())
        return axios({
            method: 'get',
            url: config.REST_API_URL + 'playlists/' + username,
            headers: authHeader(),
        })
    }

    addSongToPlaylist(playlistId, spotifyId){
        return axios({
            method: 'post',
            url: config.REST_API_URL + 'playlists/add/' + playlistId + '/' + spotifyId,
            headers: authHeader(),
        })
    }

    removeSongFromPlaylist(playlistId, spotifyId){
        return axios({
            method: 'post',
            url: config.REST_API_URL + 'playlists/remove/' + playlistId + '/' + spotifyId,
            headers: authHeader()
        })
    }
}

export default new PlaylistService();