import React, { Component } from 'react';
import PlaylistService from '../../services/rest/playlist.service';

export default class AddPlaylist extends Component {

    constructor(props) {
        super(props)
        this.state = {
            playlistTitle: "",
            playlistDesc: "",
        }

        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleDescChange = this.handleDescChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    handleTitleChange(event) {
        this.setState({ playlistTitle: event.target.value });
    }

    handleDescChange(event) {
        this.setState({ playlistDesc: event.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
        PlaylistService.addPlaylist(this.state.playlistTitle, this.state.playlistDesc).then((response) => {
            if(response.data == ""){
                alert('New playlists cannot share names with existing playlists!');
            } else {
                alert('Playlist Created, redirecting now.');
                this.props.history.push("/playlist/" + response.data.id);
            }
        })
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-md-7 col-lg-5">
                    <div className="login-wrap p-4 p-md-5">
                        <h3 className="text-center mb-4">New Playlist</h3>
                        <form onSubmit={this.handleSubmit} action="#" className="login-form">
                            <label>Title:</label>
                            <div className="form-group">
                                <input 
                                type="text" 
                                value={this.state.playlistTitle} 
                                onChange={this.handleTitleChange} 
                                data-cy="playlisttitleinput"/>
                            </div>
                            <label>Playlist Description:</label>
                            <div class="form-group">
                                <input 
                                type="text" 
                                value={this.state.playlistDesc} 
                                onChange={this.handleDescChange} 
                                data-cy="playlistdescinput"/>
                            </div>
                            <div className="form-group">
                                <button type="submit" className="form-control btn btn-primary rounded submit px-3">Create</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}
