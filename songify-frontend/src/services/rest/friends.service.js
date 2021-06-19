import axios from 'axios'
import authHeader from './auth/auth-header';
import AuthService from './auth/auth.service';
import UserService from './user.service';

const config = require('../../config.json');

class FriendsService{
    async getMyFriendRequests(){
        return await axios({
            method: 'get',
            url: config.REST_API_URL + 'friends/requests/' + AuthService.getCurrentUser().id,
            headers: authHeader(),
        })
    }

    createRequest(receiverId){
        axios({
            method: 'post',
            url: config.REST_API_URL + "friends",
            headers: authHeader(),
            data:{
                senderId: AuthService.getCurrentUser().id,
                receiverId: receiverId
            }
        })
    }

    acceptRequest(firstUserId, secondUserId){
        axios({
            method: 'post',
            url: config.REST_API_URL + 'friends/accept',
            headers: authHeader(),
            data:{
                firstUserId: firstUserId,
                secondUserId: secondUserId
            }
        })
    }
}

export default new FriendsService();