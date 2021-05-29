import React, { Component } from 'react';
import UserService from '../../services/rest/user.service.js';
import AuthService from '../../services/rest/auth/auth.service';

const useSortableData = (items, config = null) => {
    const [sortConfig, setSortConfig] = React.useState(config);

    const sortedItems = React.useMemo(() => {
        let sortableItems = [...items];
        if (sortConfig !== null) {
            sortableItems.sort((a, b) => {
                if (a[sortConfig.key] < b[sortConfig.key]) {
                    return sortConfig.direction === 'ascending' ? -1 : 1;
                }
                if (a[sortConfig.key] > b[sortConfig.key]) {
                    return sortConfig.direction === 'ascending' ? 1 : -1;
                }
                return 0;
            });
        }
        return sortableItems;
    }, [items, sortConfig]);

    const requestSort = (key) => {
        let direction = 'ascending';
        if (
            sortConfig &&
            sortConfig.key === key &&
            sortConfig.direction === 'ascending'
        ) {
            direction = 'descending';
        }
        setSortConfig({ key, direction });
    };

    return { items: sortedItems, requestSort, sortConfig };
};

const UserTable = (props) => {
    const { items, requestSort, sortConfig } = useSortableData(props.users);
    const getClassNamesFor = (name) => {
        if (!sortConfig) {
            return;
        }
        return sortConfig.key === name ? sortConfig.direction : undefined;
    };
    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>
                        <button type="button" onClick={() => requestSort('id')} className={getClassNamesFor('id')} >
                            ID
                </button>
                    </th>
                    <th>
                        <button type="button" onClick={() => requestSort('email')} className={getClassNamesFor('email')} >
                            Email
                </button>
                    </th>
                    <th>
                        <button type="button" onClick={() => requestSort('username')} className={getClassNamesFor('username')} >
                            Username
                </button>
                    </th>
                    <th> Password </th>
                    <th>
                        <button type="button" onClick={() => requestSort('username')} className={getClassNamesFor('username')} >
                            Role ID
                </button>
                    </th>
                    <th>
                        <button type="button" onClick={() => requestSort('username')} className={getClassNamesFor('username')} >
                            Role Name
                </button>
                    </th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {items.map((user) => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.email}</td>
                        <td>{user.username}</td>
                        <td>{user.password}</td>
                        <td>{user.role.id}</td>
                        <td>{user.role.name}</td>
                        <td>
                            <button type="button" className="btn btn-primary" onClick={() => props.edit(user.id)} >Edit</button>
                            <button type="button" className="btn btn-danger" onClick={() => props.delete(user.id)} >Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default class Users extends Component {

    constructor(props) {
        super(props)
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        let user = AuthService.getCurrentUser();
        if(user.role.name !== "ADMIN"){
            this.props.history.push('/access-denied');
        }

        UserService.getUsers().then((response) => {
            this.setState({ users: response.data })
        });
    }

    editUser(id) {
        window.location.href="/update-user/" + id;
    }
    
    deleteUser(id) {
        var r = window.confirm("Are you sure you want to delete this user?");
        if (r == true) {
            UserService.deleteUser(id).then(() => {
                this.setState({ users: this.state.users.filter((user) => user.id !== id) });
            });
        } else {
            window.location.reload();
        }
    
    }

    render() {
        return (
            <div>
                <UserTable users={this.state.users} edit={this.editUser} delete={this.deleteUser} />
            </div>
        )
    }
}
