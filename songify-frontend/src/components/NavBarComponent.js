import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';

import RoleTable from './tables/RolesTable';
import UsersTable from './tables/UsersTable';
import TestComponent from './TestComponent';
import Login from './forms/login.component';
import UpdateUserComponent from './forms/UpdateUserComponent';
import AddPlaylistComponent from './forms/AddPlaylistComponent';
import RegisterForm from './forms/register.component';

import AuthService from '../services/auth/auth.service';
import Profile from './profile.component';

export default class NavbarComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: undefined,
        };

    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();

        AuthService.refreshToken();
        AuthService.getToken();

        if (user) {
            this.setState({
                currentUser: user,
            });
        }
    }

    renderNav(user) {
        if (user && user.role.name == "USER") { // nav bar for regular users
            console.log("** loggedin and user **")
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                        <li><Link to={'/'} className="nav-link"> My Playlists </Link></li>
                        <li><Link to={'/'} className="nav-link"> Browse Playlists </Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/profile'} className="nav-link"> {this.state.currentUser.username}</Link></li>
                        <li onClick={AuthService.logout}><Link to={'/'} className="nav-link"> Logout </Link></li>
                    </ul>
                </nav>
            )
        }
        else if (user && user.role.name == "ADMIN") { // nav bar for admins
            console.log("** loggedin and admin **")
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                        <li><Link to={'/users'} className="nav-link"> Users </Link></li>
                        <li><Link to={'/roles'} className="nav-link"> Roles </Link></li>
                        <li><Link to={'/test'} className="nav-link"> Test </Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/profile'} className="nav-link"> {this.state.currentUser.username} </Link></li>
                        <li onClick={AuthService.logout}><Link to={'/'} className="nav-link"> Logout </Link></li>
                    </ul>
                </nav>
            )
        }
        else {
            console.log("** not loggedin**")
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
                    {this.renderNav(this.state.currentUser)}
                    <Switch>
                        <Route path='/profile' component={Profile} />
                        <Route path='/users' component={UsersTable} />
                        <Route path='/roles' component={RoleTable} />
                        <Route path='/test' component={TestComponent} />
                        <Route path='/login' component={Login} />
                        <Route path='/update-user/:id' component={UpdateUserComponent} />
                        <Route path='/newplaylist' component={AddPlaylistComponent} />
                        <Route path='/register' component={RegisterForm} />
                    </Switch>

                </Router>
            </div>
        )
    }
}
