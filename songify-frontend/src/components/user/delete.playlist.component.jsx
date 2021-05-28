import React, { Component } from 'react';
import PlaylistService from '../../services/playlist.service';

export default class DeletePlaylist extends Component {

    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
        }
        
    }

    componentDidMount(){
        PlaylistService.deletePlaylist(this.state.id)
        this.props.history.push("/home")
    }

    render() {
        return (
            <div>
            </div>
        )
    }
}