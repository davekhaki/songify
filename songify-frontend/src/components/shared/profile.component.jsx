import React, { Component } from "react";

import AuthService from '../../services/rest/auth/auth.service';
import FriendsService from "../../services/rest/friends.service";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      userReady: false,
      currentUser: { username: "", role: [] },
      friends: [],
      friendRequests: []
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.props.history.push('/login');
    this.setState({ currentUser: currentUser })
    this.setState({ friends: currentUser.friends })

    FriendsService.getMyFriendRequests().then((response) => {
      console.log(response);
      this.setState({ friendRequests: response.data })
    })
  }

  acceptRequest(id) {
    FriendsService.acceptRequest(this.state.currentUser.id, id)
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
        <div class="row">
          <div class="col-sm-6">
            <h1>Friends List</h1>
            <table className="table table-striped">
              <thead>
                <tr>
                  <td col-md-1>Username</td>
                </tr>
              </thead>
              <tbody>
                {
                  this.state.friends.map(
                    friend =>
                      <tr key={friend.friendName}>
                        <td>{friend.friendName}</td>
                      </tr>
                  )
                }
              </tbody>
            </table>
          </div>
          <div class="col-sm-6">
            <h1>Friend Requests:</h1>
            <table className="table table-striped">
              <thead>
                <tr>
                  <td>Sent By:</td>
                  <td></td>
                </tr>
              </thead>
              <tbody>
                {
                  this.state.friendRequests.map(
                    request =>
                      <tr key={request.id}>
                        <td col-md-1>{request.senderId}</td>
                        <td col-md-2>
                          <button type="button" className="btn btn-success" onClick={() => this.acceptRequest(request.senderId)}>Accept</button>
                        </td>
                      </tr>
                  )
                }
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}