import './App.css';

import React from 'react';

import NavBarComponent from './components/NavBarComponent';
import PopularPlaylistComponent from './components/home/PopularPlaylistComponent';

import TokenService from './services/external/TokenService';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loggedIn: false,
      apiToken: "",
      username: "",
    }
  }

  render() {
    return (
      <div className="App">
        <NavBarComponent></NavBarComponent>
        {/* <PopularPlaylistComponent></PopularPlaylistComponent> */}
      </div>
    );
  }
}

export default App;