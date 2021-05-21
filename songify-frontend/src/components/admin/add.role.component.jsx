import React, { Component } from 'react';
import RoleService from '../../services/roles.service';

export default class AddRole extends Component {

    constructor(props) {
        super(props)
        this.state = {
            name: "",
        }

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleNameChange(event) {
        this.setState({ name: event.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
        RoleService.addRole(this.state.name).then(()=>{
            alert('Role added.')
            this.props.history.push("/roles");
        })
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">Add Role</h3>
                        <form onSubmit={this.handleSubmit} action="#" className="add-role-form">
                            <label>Name:</label>
                            <div className="form-group">
                                <input type="text" value={this.state.name} onChange={this.handleNameChange} />
                            </div>
                            <div className="form-group">
                                <button type="submit" className="form-control btn btn-primary rounded submit px-3">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}