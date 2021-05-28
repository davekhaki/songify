import React, { Component } from "react";

export default class AccessDenied extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }

    render() {
        return (
            <div>
                <h1 data-cy="accessdeniedmsg">Access Denied.</h1> 
            </div>

        )
    }
}