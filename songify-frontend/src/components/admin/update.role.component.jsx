import React, { Component } from 'react';
import RolesService from "../../services/rest/roles.service";

export default class UpdateRole extends Component {

    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            name: "",
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        RolesService.getRoleById(this.state.id).then((response)=>{
            this.setState({ name: response.data.name })
        })
    }

    handleSubmit = (event) => {
        event.preventDefault();
        RolesService.updateRole(this.state.id, this.state.name).then(()=>{
            alert('Role Updated');
            this.props.history.push("/roles");
        })
    }

    changeNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    cancel() {
        this.props.history.push("/roles");
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">Update</h3>
                        <label>DISCLAIMER: UPDATING ROLE WILL ONLY WORK BEFORE IT IS ASSIGNED TO ANY USERS.</label>
                        <form onSubmit={this.handleSubmit} action="#" className="update-role-form">
                            <label>New Name:</label>
                            <div class="form-group d-flex">                                
                                <input type="text" value={this.state.username} onChange={this.changeUsernameHandler} className="form-control rounded-left" placeholder={this.state.name}/>
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