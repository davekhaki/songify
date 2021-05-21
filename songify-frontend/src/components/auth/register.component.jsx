import { Component } from 'react';
import UserService from '../../services/user.service';

class RegisterForm extends Component{

    constructor(props){
        super(props)
        this.state = {
            username: "",
            password: "",
            email: "",
        }

        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount(){

    }

    handleUsernameChange(event){
        this.setState({username: event.target.value});
    }

    handlePasswordChange(event){
        this.setState({password: event.target.value});
    }

    handleEmailChange(event){
        this.setState({email: event.target.value});
    }



    handleSubmit(event){
        event.preventDefault();
        UserService.addUser(this.state.username, this.state.password, this.state.email);
    }

    render (){
        return (
            <form onSubmit={this.handleSubmit}>
                <label>Username:
                    <input type="text" value={this.state.username} onChange={this.handleUsernameChange} />
                </label>
                <label>Password:
                    <input type="text" value={this.state.password} onChange={this.handlePasswordChange} />
                </label>
                <label>Email:
                    <input type="text" value={this.state.email} onChange={this.handleEmailChange} />
                </label>
                <input type="submit" value="Sign Up" />
            </form>
        )
    }
}

export default RegisterForm