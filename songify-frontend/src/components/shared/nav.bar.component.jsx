import React, {Component} from 'react';
import { BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';

import Roles from '../admin/roles.list.component';
import Users from '../admin/users.list.component';
import Login from '../auth/login.component';
import UpdateUser from '../admin/update.user.component';
import AddPlaylist from '../user/add.playlist.component';
import Register from '../auth/register.component';

import AuthService from '../../services/auth/auth.service';
import Profile from './profile.component';
import MyPlaylists from '../user/my.playlists.component';
import SpecificPlaylist from '../user/specific.playlist.component';

export default class Navbar extends Component {
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

    renderNav(user) {
        if (user && user.role.name == "USER") { // nav bar for regular users
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                        <li><Link to={'/my-playlists'} className="nav-link"> My Playlists </Link></li>
                        <li><Link to={'/'} className="nav-link"> Browse Playlists </Link></li>
                        <li><Link to={'/new-playlist'} className="nav-link">New Playlist</Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/profile'} className="nav-link"> {this.state.currentUser.username}</Link></li>
                        <li onClick={AuthService.logout}><Link to={'/'} className="nav-link"> Logout </Link></li>
                    </ul>
                </nav>
            )
        }
        else if (user && user.role.name == "ADMIN") { // nav bar for admin
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li><Link to={'/'} className="nav-link"> Songify </Link></li>
                        <li><Link to={'/users'} className="nav-link"> Users </Link></li>
                        <li><Link to={'/roles'} className="nav-link"> Roles </Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li><Link to={'/profile'} className="nav-link"> {this.state.currentUser.username} </Link></li>
                        <li onClick={AuthService.logout}><Link to={'/'} className="nav-link"> Logout </Link></li>
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
                    {this.renderNav(this.state.currentUser)}
                    <Switch>
                        {/* SHARED ROUTES: */}
                        <Route path='/register' component={Register} />
                        <Route path='/login' component={Login} />
                        <Route path='/profile' component={Profile} />
                        {/* USER ROUTES : */}
                        <Route path='/my-playlists' component={MyPlaylists}/>
                        <Route path='/new-playlist' component={AddPlaylist} />
                        <Route path='/playlist/:id' component={SpecificPlaylist}/>
                        {/* ADMIN ROUTES : */}
                        <Route path='/users' component={Users} />
                        <Route path='/roles' component={Roles} />                        
                        <Route path='/update-user/:id' component={UpdateUser} />
                    </Switch>

                </Router>
            </div>
        )
    }
}
