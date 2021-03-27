import './App.css';

import React from 'react';

import RoleComponent from './components/admin/RoleComponent';
import UsersComponent from './components/admin/UsersComponent';

import LoginComponent from './components/user/LoginComponent';

import NavBarComponent from './components/NavBarComponent';

import TestComponent from './components/TestComponent';

import TokenService from './services/external/TokenService';

import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loggedIn: false,
      spotifyApiKey: ""
    }

    console.log(TokenService.getToken());
  }

  
  

  loggedIn = () => {
    this.setState({
      loggedIn: true
    })
  }

  render() {
    return (
      <div className="App">
        <Router>
        
          <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <ul className="navbar-nav mr-auto">
              <li><Link to={'/'} className="nav-link"> Songify </Link></li>
              <li><Link to={'/users'} className="nav-link"> Users </Link></li>
              <li><Link to={'/roles'} className="nav-link"> Roles </Link></li>
              <li><Link to={'/test'} className="nav-link"> Test </Link></li>
            </ul>
            <ul className="navbar-nav ml-auto">
              <li><Link to={'/login'} className="nav-link"> Login</Link></li>
            </ul>
          </nav>

          <Switch>
            <Route path='/users' component={ UsersComponent } />
            <Route path='/roles' component={ RoleComponent } />
            <Route path='/test' component={ TestComponent } />
            <Route path='/login' component={ LoginComponent } />
          </Switch>

      </Router>
      </div>
    );
  }
}

export default App;