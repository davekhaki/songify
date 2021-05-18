import React, { Component } from "react";

import AuthService from '../services/auth/auth.service';

export default class Profile extends Component {
    constructor(props) {
      super(props);
  
      this.state = {
        userReady: false,
        currentUser: { username: "", role: []}
      };
    }
  
    componentDidMount() {
      const currentUser = AuthService.getCurrentUser();
  
      if (!currentUser) this.props.history.push('/login');
      this.setState({ currentUser: currentUser })
    }
  
    render() {
      const { currentUser } = this.state;
  
      return (
        <div className="container">
          <div>
          <header className="jumbotron">
            <h3>
              <strong>{currentUser.username}</strong> Profile
            </h3>
          </header>
          <p>
            <strong>Id:</strong>{" "}
            {currentUser.id}
          </p>
          <p>
            <strong>Email:</strong>{" "}
            {currentUser.email}
          </p>
          <strong>Role:</strong>
          <ul>
            {}
            {/* {currentUser.roles.map((role, index) => <li key={index}> {role}</li>)} */}
          </ul>
        </div>
        </div>
      );
    }
  }