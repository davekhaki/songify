import React, { Component } from 'react';
import UserService from '../../services/rest/user.service.js';

export default class UpdateUser extends Component {

    constructor(props) {
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

    componentDidMount() {
        UserService.getUserById(this.state.id).then((response) => {
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
        UserService.updateUser(user, this.state.id).then(() => {
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

    cancel() {
        this.props.history.push("/users");
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">Update</h3>
                        <form onSubmit={this.updateUserDetails} action="#" className="login-form">
                            <label>Email:</label>
                            <div className="form-group">
                                <input type="text" value={this.state.email} onChange={this.changeEmailHandler} className="form-control rounded-left" />
                            </div>
                            <label>Username:</label>
                            <div class="form-group d-flex">                                
                                <input type="text" value={this.state.username} onChange={this.changeUsernameHandler} className="form-control rounded-left" />
                            </div>
                            <div className="form-group">
                                <button type="submit" className="form-control btn btn-success rounded submit px-3">Edit</button>
                            </div>
                            <div className="form-group">
                                <button onClick={this.cancel.bind(this)} className="form-control btn btn-danger rounded submit px-3">Cancel</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        )
    }
}
