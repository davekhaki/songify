import React, { Component } from "react";

import AuthService from '../../services/rest/auth/auth.service';
import FriendsService from "../../services/rest/friends.service";
import UserService from "../../services/rest/user.service";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      userReady: false,
      currentUser: { username: "", role: [] },
      friends: [],
      friendRequests: [],
      usernames: [],
      searchTerm: "",
    }

    this.onChangeSearchTerm = this.onChangeSearchTerm.bind(this);
    this.sendFriendRequest = this.sendFriendRequest.bind(this);
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    if (!currentUser) this.props.history.push('/login');
    this.setState({ currentUser: currentUser })
    this.setState({ friends: currentUser.friends })

    FriendsService.getMyFriendRequests().then((response)=>{
      this.setState({ friendRequests: response.data })
    });
  }

  componentDidUpdate() {
    //
  }

  onChangeSearchTerm(e) {
    this.setState({ searchTerm: e.target.value })
  }

  sendFriendRequest(){
    UserService.getUsers().then((response)=>{
      var list = response.data
      list.forEach(user => {
        if(user.username == this.state.searchTerm){
          FriendsService.createRequest(user.id)
        }
      });
    })
  }

  acceptRequest(id) {
    console.log("current user id: " + this.state.currentUser.id + " request friend id: " + id);
    FriendsService.acceptRequest(this.state.currentUser.id, id)
    window.location.reload()
  }

  render() {
    const { currentUser } = this.state;

    return (
      <div>
        <div className="container">
          <div>
            <header className="jumbotron">
              <h3>
                <strong>{currentUser.username}</strong> Profile
              </h3>
            </header>
            <p>
              <strong>Email:</strong>{" "}
              {currentUser.email}
            </p>
          </div>
        </div>
        <div className="row">
          <div className="col-sm-6">
            <h1>Friends List</h1>
            <ul className="list-group">
              {this.state.friends.map(friend => <li className='list-group-item'> {friend.friendName} </li>)}
            </ul>
          </div>
          <div className="col-sm-6">
            <h1>Friend Requests:</h1>
            <table className="table table-striped">
              <thead>
                <tr>
                  <td>Sent By:</td>
                  <td></td>
                </tr>
              </thead>
              <tbody data-cy="friendreqtr">
                {
                  this.state.friendRequests.map(
                    request =>
                      <tr key={request.id}>
                        <td className="col-md-1" >{request.senderId}</td>
                        <td className="col-md-2">
                          <button data-cy="acceptreqbtn" type="button" className="btn btn-success" onClick={() => this.acceptRequest(request.senderId)}>Accept</button>
                        </td>
                      </tr>
                  )
                }
              </tbody>
            </table>
          </div>
        </div>
        <div>
          <h1>Send a friend request</h1>
          <div className="d-flex justify-content-center">
                <div className="col-lg-6">
                  <label>Search</label>
                  <input
                        type="text"
                        value={this.state.searchTerm}
                        onChange={this.onChangeSearchTerm}
                        className="form-control rounded-left"
                        placeholder="User Name"
                        required=""/>
                  <button type="button" className="btn btn-primary" placeholder="User Name" onClick={this.sendFriendRequest}> Send Request </button>
          </div>
        </div>
        </div>
      </div >
    );
  }
}



