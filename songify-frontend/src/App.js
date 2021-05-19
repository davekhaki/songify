import './App.css';

import React from 'react';

import NavBarComponent from './components/NavBarComponent';

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
        <NavBarComponent />
        {/* <PopularPlaylistComponent></PopularPlaylistComponent> */}
      </div>
    );
  }
}

export default App;