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

    editUser(id){
        this.props.history.push(`/update-user/${id}`);
    }

    deleteUser(id){
        UserService.deleteUser(id).then((response)=>{
            this.setState({ users: this.state.users.filter((user) => user.id !== id)}); 
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
                            <td>Role ID</td>
                            <td>Role Name</td>
                            <td>Actions</td>
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
                                    <td>{user.password}</td>
                                    <td>{user.role.id}</td>
                                    <td>{user.role.name}</td>
                                    <td>              
                                        <button type="button" class="btn btn-primary" onClick={() => this.editUser(user.id)} >Edit</button>
                                        <button type="button" class="btn btn-danger" onClick={() => this.deleteUser(user.id)} >Delete</button>
                                    </td>
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