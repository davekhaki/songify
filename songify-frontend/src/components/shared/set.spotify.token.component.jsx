import React, { Component } from "react";
import TokenService from "../../services/spotify/auth/token.service";



export default class SetSpotifyToken extends Component {
    constructor(props) {
      super(props);
  
      this.state = {
        accessToken: this.props.match.params.accessToken,
      };
    }
  
    componentDidMount() {
        TokenService.setToken(this.state.accessToken);
        this.props.history.push("/");        
    }
  
    render() {
      return (
        <div></div>
      );
    }
  }