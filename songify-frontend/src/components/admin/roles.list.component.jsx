import React from 'react';
import RoleService from '../../services/roles.service.js';

export default class Roles extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            roles: []
        }
    }

    componentDidMount() {
        RoleService.getRoles().then((response) => {
            this.setState({ roles: response.data })
        });
    }

    editRole(id){
        this.props.history.push(`/update-role/${id}`);
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Roles</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Actions</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.roles.map(
                                role =>
                                    <tr key={role.id}>
                                        <td>{role.id}</td>
                                        <td>{role.name}</td>
                                        <td>
                                            <button type="button" className="btn btn-primary" onClick={() => this.editRole(role.id)} >Edit</button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
                <div className="w-100">
                    <a href="/add-role">Add a new role</a>
                </div>
            </div>
        )
    }
}
