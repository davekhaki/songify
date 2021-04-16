import './App.css';

import React from 'react';

import NavBarComponent from './components/NavBarComponent';

import TokenService from './services/external/TokenService';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loggedIn: false,
      spotifyApiKey: ""
    }

    TokenService.getToken().then(response => {
      //console.log("SPOTIFY ACCESS TOKEN: " + response.data.access_token);
      this.setState({spotifyApiKey: response.data.access_token});
      //console.log("token in state: " + this.spotifyApiKey);
    });
  }

  render() {
    return (
      <div className="App">
        <NavBarComponent></NavBarComponent>
      </div>
    );
  }
}

export default App;