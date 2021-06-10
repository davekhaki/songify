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
      usernames: []
    }
  }

  async componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    if (!currentUser) this.props.history.push('/login');
    this.setState({ currentUser: currentUser })
    this.setState({ friends: currentUser.friends })

    const raw = await FriendsService.getMyFriendRequests();
    this.setState({ friendRequests: raw.data })
  }

  componentDidUpdate() {
    this.state.friendRequests.map(
      request => {
        UserService.getUsername(request.senderId).then((response) => {

          // this.setState(prevState => ({
          //   usernames: {
          //     ...prevState.usernames,
          //     [prevState.usernames[0]]: "hey",
          //   },
          // }))

          //this.state.usernames[request.senderId] = response.data;
          //console.log(this.state.usernames[request.senderId]);
        
        })
      }
    )
  }

  acceptRequest(id) {
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
              <tbody>
                {
                  this.state.friendRequests.map(
                    request =>
                      <tr key={request.id}>
                        {console.log(this.state.usernames[0])}
                        <td className="col-md-1" >{this.state.usernames[request.senderId]}</td>
                        <td className="col-md-2">
                          <button type="button" className="btn btn-success" onClick={() => this.acceptRequest(request.senderId)}>Accept</button>
                        </td>
                      </tr>
                  )
                }
              </tbody>
            </table>
          </div>
        </div>
      </div >
    );
  }
}



