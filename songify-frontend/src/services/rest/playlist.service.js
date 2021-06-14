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
        })
    }

    getPopularPlaylists(){
        return axios.get(config.REST_API_URL + "playlists/popular");
    }

    addPlaylist(playlistTitle, playlistDesc){
        return axios({
            method: 'post',
            url: config.REST_API_URL + 'playlists',
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
            url: config.REST_API_URL + 'playlists/add/' + playlistId + '/' + spotifyId
        })
    }
}

export default new PlaylistService();