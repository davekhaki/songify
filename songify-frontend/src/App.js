import './App.css';

import React from 'react';

import Navbar from './components/shared/nav.bar.component';

import Image from './img/logo.png';

import AuthService from './services/auth/auth.service';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: undefined,
    };

  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
      });
    }
  }

  render() {
    return (
      <div className="App">
        <Navbar />
        {/* <PopularPlaylist></PopularPlaylist> */}
      </div>
    );
  }
}

export default App;