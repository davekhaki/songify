import axios from 'axios'
import AuthService from './auth/auth.service';

const config = require('../../config.json');

class FriendsService{
    getMyFriendRequests(){
        return axios({
            method: 'get',
            url: config.REST_API_URL + 'friends/requests/' + AuthService.getCurrentUser().id,
        })
    }

    acceptRequest(firstUserId, secondUserId){
        axios({
            method: 'post',
            url: config.REST_API_URL + 'friends/accept',
            data:{
                firstUserId: firstUserId,
                secondUserId: secondUserId
            }
        })
    }
}

export default new FriendsService();