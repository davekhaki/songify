import React, { Component } from "react";
import Image from '../../img/logo.png';

export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }

    render() {
        return (
            <div>
                <h1>Welcome to Songify</h1>
                <img src={Image} width="30%" height="30%" alt="cam" />
            </div>

        )
    }
}