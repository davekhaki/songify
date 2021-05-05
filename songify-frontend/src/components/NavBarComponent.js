import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import RoleComponent from './admin/RoleComponent';
import UsersComponent from './admin/UsersComponent';
import TestComponent from './TestComponent';
import LoginComponent from './auth/LoginComponent';
import UpdateUserComponent from './admin/UpdateUserComponent';

class HeaderComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
    }

    
    static getDerivedStateFromProps(props, state){
        return {loggedIn: props.loggedIn };
    }

    render() {
        return (
            <div id="nav">
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
                        <Route path='/users' component={UsersComponent} />
                        <Route path='/roles' component={RoleComponent} />
                        <Route path='/test' component={TestComponent} />
                        <Route path='/login' component={LoginComponent} />
                        <Route path='/update-user/:id' component={UpdateUserComponent} />
                    </Switch>

                </Router>

            </div>
        )
    }
}

export default HeaderComponent;