import './App.css';

import React from 'react';

import NavBarComponent from './components/NavBarComponent';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loggedIn: false,
      role: "USER",
      apiToken: "",
      username: "",
    }

    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogin(){
    this.setState({loggedIn: true});
  }

  handleLogout(){
    this.setState({loggedIn: false});
  }

  render() {
    return (
      <div className="App">
        <NavBarComponent loggedIn={this.state.loggedIn} role={this.state.role} handleLogin={this.handleLogin}/>
        {/* <PopularPlaylistComponent></PopularPlaylistComponent> */}
      </div>
    );
  }
}

export default App;