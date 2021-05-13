import React from 'react';

import LoginService from '../../services/LoginService';

class LoginComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedIn: true,
            username: "",
            password: ""
        };

        this.usernameChange = this.usernameChange.bind(this);
        this.passwordChange = this.passwordChange.bind(this);
        this.loginSubmit = this.loginSubmit.bind(this);
    }

    static getDerivedStateFromProps(props, state){
        return {loggedIn: props.loggedIn };
    }

    usernameChange(event) {
        this.setState({username: event.target.value})
    }

    passwordChange(event){
        this.setState({password: event.target.value})
    }

    loginSubmit(){
        console.log("fuck");
        console.log(this.state.loggedIn);
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">Login</h3>
                        <form onSubmit={this.loginSubmit} action="#" className="login-form">
                            <div className="form-group">
                                <input type="text" value={this.state.username} onChange={this.usernameChange} className="form-control rounded-left" placeholder="Username" required="" />
                            </div>
                            <div class="form-group d-flex">
                                <input type="password" value={this.state.password} onChange={this.passwordChange} className="form-control rounded-left" placeholder="Password" required="" />
                            </div>
                            <div className="form-group">
                                <button type="submit" className="form-control btn btn-primary rounded submit px-3">Login</button>
                            </div>
                            <div className="form-group d-md-flex">
                                <div className="w-100">
                                <a href="">Forgot Password</a>
                                </div>
                                <div className="w-100">
                                <a href="/register">Create Account</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

export default LoginComponent