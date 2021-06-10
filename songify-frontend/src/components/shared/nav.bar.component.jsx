import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';
import Image from '../../img/logo.png';
import AuthService from '../../services/rest/auth/auth.service';

import Roles from '../admin/roles.list.component';
import Users from '../admin/users.list.component';
import Login from '../auth/login.component';
import UpdateUser from '../admin/update.user.component';
import AddPlaylist from '../user/add.playlist.component';
import Register from '../auth/register.component';
import AddRole from '../admin/add.role.component';
import Profile from './profile.component';
import MyPlaylists from '../user/my.playlists.component';
import SpecificPlaylist from '../user/specific.playlist.component';
import UpdateRole from '../admin/update.role.component';
import Home from './home.component';
import DeletePlaylist from '../user/delete.playlist.component';
import BrowsePlaylists from '../user/browse.playlist.component';
import AccessDenied from './access.denied.component';
import BrowseSongs from '../user/browse.songs.component';
import SetSpotifyToken from './set.spotify.token.component';
import Chat from '../user/chat.component';

export default class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: undefined,
            messages: [],
            messageText: '',
        };
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();
        if (user) { this.setState({ currentUser: user }); }
    }

    handleMessageSend = () => {
        if (this.state.userName) {
            this.clientRef.sendMessage("/app/chat.sendMessage", JSON.stringify({sender: this.state.userName, content: this.state.messageText, type: 'CHAT'}));
            this.setState({messageText: ''})
        }
    }

    handleChange = name => event => {
        this.setState({ [name]: event.target.value });
    };

    handleMessageReceived = msg => {
        this.setState({messages: this.state.messages.concat(msg)});
    }

    renderNav(user) {
        if (user && user.role.name == "USER") { // nav bar for regular users
            return (
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <ul className="navbar-nav mr-auto">
                        <li>
                            <Link to={'/'} className="nav-link"> <img src={Image} width="20" height="20" alt="cam" /> Songify </Link></li>
                        <li><Link to={'/my-playlists'} className="nav-link"> My Playlists </Link></li>
                        <li><Link to={'/browse'} className="nav-link"> Browse Playlists </Link></li>
                        <li><Link to={'/new-playlist'} className="nav-link" data-cy="newplaylistbutton">New Playlist</Link></li>
                        <li><Link to={'songs'} className="nav-link" data-cy="songspagebtn">Songs</Link></li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                    <li><Link to={'/chat'} className="nav-link">Chat</Link></li>
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
                        <li><Link to={'/'} className="nav-link"> <img src={Image} width="20" height="20" alt="cam" /> Songify </Link></li>
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
                        <li><Link to={'/'} className="nav-link"> <img src={Image} width="20" height="20" alt="cam" /> Songify </Link></li>
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
                        <Route exact path='/'><Redirect to='/home' /></Route>
                        <Route path='/access-denied' component={AccessDenied}></Route>
                        <Route path='/home' component={Home} />
                        <Route path='/register' component={Register} />
                        <Route path='/login' component={Login} />
                        <Route path='/profile' component={Profile} />
                        <Route path='/success-spotify/:accessToken' component={SetSpotifyToken} />
                        {/* USER ROUTES : */}
                        <Route path='/my-playlists' component={MyPlaylists} />
                        <Route path='/new-playlist' component={AddPlaylist} />
                        <Route path='/playlist/:id' component={SpecificPlaylist} />
                        <Route path='/delete-playlist/:id' component={DeletePlaylist}/ >
                        <Route path='/browse' component={BrowsePlaylists} />
                        <Route path='/songs' component={BrowseSongs} />
                        <Route exact path="/chat" render={(props) => <Chat {...props} />} />
                        {/* ADMIN ROUTES : */}
                        <Route path='/users' component={Users} />
                        <Route path='/update-user/:id' component={UpdateUser} />
                        <Route path='/roles' component={Roles} />
                        <Route path='/update-role/:id' component={UpdateRole} />
                        <Route path="/add-role" component={AddRole} />
                    </Switch>

                </Router>
            </div>
        )
    }
}
