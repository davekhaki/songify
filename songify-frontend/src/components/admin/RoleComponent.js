import React from 'react';
import RoleService from './../../services/RoleService.js';

import { DataGrid } from '@material-ui/data-grid';

class RoleComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            roles:[]
        }
    }

    columns = [
        { field: 'id', headerName: 'ID' },
        { field: 'name', headerName: 'Name'}
    ];

    componentDidMount(){
        RoleService.getRoles().then((response)=>{
            this.setState({ roles: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className = "text-center"> Roles List</h1>
                <div style={{ height: 400, width: '100%' }}>
                    <DataGrid rows={this.state.roles} columns={this.columns} pageSize={5} checkboxSelection />
                </div>
            </div>
        )
    }
}

export default RoleComponent