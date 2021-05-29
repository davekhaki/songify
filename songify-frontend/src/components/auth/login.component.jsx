import React, { Component } from 'react';

import AuthService from '../../services/rest/auth/auth.service';

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
        
        this.state = {
            username: "",
            password: ""
        };
    }

    onChangeUsername(e){
        this.setState({username: e.target.value})
    }

    onChangePassword(e){
        this.setState({password: e.target.value})
    }

    handleLogin(e) {
        e.preventDefault();

        AuthService.login(this.state.username, this.state.password).then((response) => {
                console.log("RESEPONSE: " + response);
                this.props.history.push("/profile");
                window.location.reload();
            },
            error => {
                console.log(error);
            }
        )
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">Login</h3>
                        <form onSubmit={this.handleLogin} action="#" className="login-form">
                            <div className="form-group">
                                <input 
                                autoFocus 
                                type="text" 
                                value={this.state.username} 
                                onChange={this.onChangeUsername} 
                                className="form-control rounded-left" 
                                placeholder="Username" 
                                required="" 
                                data-cy="formusernameinput"/>
                            </div>
                            <div class="form-group d-flex">
                                <input 
                                type="password" 
                                value={this.state.password} 
                                onChange={this.onChangePassword} 
                                className="form-control rounded-left" 
                                placeholder="Password" 
                                required="" 
                                data-cy="formpasswordinput" />
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