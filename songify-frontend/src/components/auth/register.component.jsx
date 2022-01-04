import { Component } from 'react';
import UserService from '../../services/rest/user.service';

class RegisterForm extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: "",
            email: "",
            isPasswordGood: false,
            passwordRegex: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,32}$/
        }

        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleUsernameChange(event) {
        this.setState({ username: event.target.value });
    }

    handlePasswordChange(event) {
        this.setState({ password: event.target.value });
    }

    handleEmailChange(event) {
        this.setState({ email: event.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
        if(!this.state.passwordRegex.test(this.state.password)){
            this.setState({ isPasswordGood: false});
        } else this.setState({ isPasswordGood: true});
        
        if (this.state.isPasswordGood == true) {
            UserService.addUser(this.state.username, this.state.password, this.state.email);
            alert("Account created.")
            this.props.history.push("/login");
        }
        else alert("A password must have atleast one lower and uppercase letter, a digit and 8-32 characters");

    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">Register</h3>
                        <form onSubmit={this.handleSubmit} action="#" className="register-form">
                            <label>Email:</label>
                            <div className="form-group">
                                <input 
                                type="text" 
                                value={this.state.email} 
                                onChange={this.handleEmailChange} 
                                className="form-control rounded-left" />
                            </div>
                            <label>Username:</label>
                            <div class="form-group">
                                <input 
                                type="text" 
                                value={this.state.username} 
                                onChange={this.handleUsernameChange} 
                                className="form-control rounded-left" />
                            </div>
                            <label>Password:</label>
                            <div className="form-group">
                                <input 
                                type="text" 
                                value={this.state.password} 
                                onChange={this.handlePasswordChange} 
                                className="form-control rounded-left" />
                            </div>
                            <div className="form-group">
                                <button type="submit" className="form-control btn btn-primary rounded submit px-3">Sign up</button>
                            </div>
                            <div className="form-group d-md-flex">
                                <div className="w-100">Already have an account?
                            <a href="/register"> Log in</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

export default RegisterForm