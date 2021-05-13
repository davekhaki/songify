import React from 'react';
import UserService from '../../services/UserService.js';

class UpdateUserComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            id: this.props.match.params.id,
            email: "",
            username: "",
            password: "",
        }

        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeUsernameHandler = this.changeUsernameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
    }

    componentDidMount(){
        UserService.getUserById(this.state.id).then((response)=>{
            let user = response.data;
            this.setState({
                email: user.email,
                username: user.username,
                password: user.password
            })
        });
    }

    updateUserDetails = (event) => {
        event.preventDefault();
        let user = {
            email: this.state.email,
            username: this.state.username,
            password: this.state.password
        }
        UserService.updateUser(user, this.state.id).then(()=> {
            alert('User Updated');
            this.props.history.push("/users");
        });
    }

    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }

    changeUsernameHandler = (event) => {
        this.setState({ username: event.target.value });
    }

    changePasswordHandler = (event) => {
        this.setState({ password: event.target.value });
    }

    cancel(){
        this.props.history.push("/users");
    }

    render (){
        return (
            <div>
                <h3 className="text-center mb-4">Update </h3>
                        <form onSubmit={this.updateUserDetails} action="#" className="login-form">
                            <div className="form-group">
                                Email:
                                <input type="text" value={this.state.email} onChange={this.changeEmailHandler} className="form-control rounded-left" />
                            </div>
                            <div class="form-group d-flex">
                                Username:
                                <input type="text" value={this.state.username} onChange={this.changeUsernameHandler} className="form-control rounded-left" />
                            </div>
                            <div class="form-group d-flex">
                                Password:
                                <input type="password" value={this.state.password} onChange={this.changePasswordHandler} className="form-control rounded-left" />
                            </div>
                            <div className="form-group">
                                <button type="submit" className="form-control btn btn-primary rounded submit px-3">Edit</button>
                            </div>
                            <div className="form-group">
                                <button onClick={this.cancel.bind(this)} className="form-control btn btn-primary rounded submit px-3">Cancel</button>
                            </div>
                        </form>
            </div>
        )
    }
}

export default UpdateUserComponent