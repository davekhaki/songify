import React from 'react';
import UserService from './../../services/UserService.js';

class UsersComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            users:[]
        }
    }

    componentDidMount(){
        UserService.getUsers().then((response)=>{
            this.setState({ users: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className = "text-center"> Users List</h1>
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
                                    <td>{user.pass_hash}</td>
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