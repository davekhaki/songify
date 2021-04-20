import React from 'react';
import UserService from './../../services/UserService.js';

import { DataGrid } from '@material-ui/data-grid';

class UsersComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            users:[]
        }
    }

    columns = [
        { field: 'id', headerName: 'ID', width: 100 },
        { field: 'email', headerName: 'Email', width: 300 },
        { field: 'username', headerName: 'Username', width: 300 },
        { field: 'passHash', headerName: 'Hashed Password', width: 300 },
        { field: 'role', headerName: 'Role Name', width: 200 }
    ];

    componentDidMount(){
        UserService.getUsers().then((response)=>{
            this.setState({ users: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className = "text-center"> Users List</h1>

                <div style={{ height: 400, width: '100%' }}>
                    <DataGrid rows={this.state.users} columns={this.columns} pageSize={5} checkboxSelection />
                </div>

                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Email</td>
                            <td>Username</td>
                            <td>Password</td>
                            <td>Role</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user =>
                                <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.email}</td>
                                    <td>{user.username}</td>
                                    <td>{user.passHash}</td>
                                    <td>{user.role.name}</td>
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            
            </div>
        )
    }
}

export default UsersComponent