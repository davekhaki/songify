import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import RoleComponent from './tables/RolesTable';
import UsersComponent from './admin/UsersComponent';
import TestComponent from './TestComponent';
import LoginForm from './forms/LoginForm';
import UpdateUserComponent from './forms/UpdateUserComponent';
import AddPlaylistComponent from './forms/AddPlaylistComponent';
import RegisterForm from './forms/RegisterForm';

class NavbarComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {

        }
    }

    login() {
        this.props.handle();
    }

    renderNav(loggedIn, role) { //add role as param for user or admin nav bar
        console.log("loggedIn value: " + loggedIn);
        console.log("role value: " + role);
        if (loggedIn && role === "USER") { // nav bar for regular users
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                        <li><Link to={'/'} className="nav-link"> My Playlists </Link></li>
                        <li><Link to={'/'} className="nav-link"> Browse Playlists </Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/profile'} className="nav-link"> **Username** </Link></li>
                        <li><Link to={'/logout'} className="nav-link"> Logout </Link></li>
                    </ul>
                </nav>
            )
        }
        else if (loggedIn && role === "ADMIN") { // nav bar for admins
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                        <li><Link to={'/users'} className="nav-link"> Users </Link></li>
                        <li><Link to={'/roles'} className="nav-link"> Roles </Link></li>
                        <li><Link to={'/test'} className="nav-link"> Test </Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/profile'} className="nav-link"> **Username** </Link></li>
                        <li><Link to={'/login'} className="nav-link"> Logout </Link></li>
                    </ul>
                </nav>
            )
        }
        else {
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/login'} className="nav-link"> Login</Link></li>
                    </ul>
                </nav>
            );
        }
    }

    render() {
        return (
            <div id="nav">
                <Router>
                    {this.renderNav(this.props.loggedIn, this.props.role)}

                    <Switch>
                        <Route path='/users' component={UsersComponent} />
                        <Route path='/roles' component={RoleComponent} />
                        <Route path='/test' component={TestComponent} />
                        <Route path='/login' component={LoginForm} />
                        <Route path='/update-user/:id' component={UpdateUserComponent} />
                        <Route path='/newplaylist' component={AddPlaylistComponent} />
                        <Route path='/register' component={RegisterForm} />
                    </Switch>

                </Router>
            </div>
        )
    }
}

export default NavbarComponent;