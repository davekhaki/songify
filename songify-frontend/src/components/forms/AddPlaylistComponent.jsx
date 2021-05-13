import React from 'react';
import PlaylistService from '../../services/PlaylistService';

class AddPlaylistComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            playlistTitle: "",
            playlistDesc: "",
        }

        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleDescChange = this.handleDescChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount(){

    }

    handleTitleChange(event){
        this.setState({playlistTitle: event.target.value});
    }

    handleDescChange(event){
        this.setState({playlistDesc: event.target.value});
    }

    handleSubmit(event){
        event.preventDefault();
        PlaylistService.addPlaylist(this.state.playlistTitle, this.state.playlistDesc)
    }

    render (){
        return (
            <form onSubmit={this.handleSubmit}>
                <label>Playlist Title:
                    <input type="text" value={this.state.playlistTitle} onChange={this.handleTitleChange} />
                </label>
                <label>Playlist Description:
                    <input type="text" value={this.state.playlistDesc} onChange={this.handleDescChange} />
                </label>
                <input type="submit" value="Create Playlist" />
            </form>
        )
    }
}

export default AddPlaylistComponent