import React, { Component } from 'react';
import PlaylistService from '../../services/rest/playlist.service';
import AuthService from '../../services/rest/auth/auth.service';
import { Card } from 'react-bootstrap';
import SongService from '../../services/spotify/song.service';

export default class SpecificPlaylist extends Component {

    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            title: "",
            desc: "",
            createdBy: "",
            message: "",
            songsIds: [],
            songs: [],
        }
        this.removeSongFromPlaylist = this.removeSongFromPlaylist.bind(this);
    }

    componentDidMount() {
        PlaylistService.getPlaylistById(this.state.id).then((response) => {
            this.setState({
                title: response.data.title,
                desc: response.data.description,
                createdBy: response.data.createdBy,
                songIds: response.data.songs
            });
            // console.log(this.state.songIds);
            SongService.getSongsByIds(this.generateIdString(this.state.songIds)).then((response) => {
                this.setState({ songs: response.data.tracks })
            })
        });
    }

    generateIdString(ids) {
        var string = "";
        ids.forEach(id => {
            if (string === "") {
                string += id.spotifyId
            }
            else {
                string += ",";
                string += id.spotifyId
            }
        });
        return string;
    }

    openPreviewUrl(url) {
        window.open(url, '_blank').focus();
    }

    removeSongFromPlaylist(playlistId, songId) {
        PlaylistService.removeSongFromPlaylist(playlistId, songId).then((response) => {
            alert('song removed.')
            window.location.reload();
        })
    }

    renderDeleteButton(song){
        var currentUser = AuthService.getCurrentUser();
        if(currentUser == null){
            return <div></div>
        }
        if(currentUser.username == this.state.createdBy){
            return(
                <button className="btn btn-danger" onClick={() => { this.removeSongFromPlaylist(this.state.id, song.id) }}>Remove</button>
            )
        }
        else return <div></div>
    }

    render() {
        return (
            <div class="d-flex justify-content-center">
                <div className="col-lg-6">
                    <Card className="bg-dark text-white">
                        <Card.Img variant="top" src="http://via.placeholder.com/640x360" />
                        <Card.Body>
                            <Card.Title>{this.state.title}</Card.Title>
                            <Card.Text>{this.state.desc}</Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <small className="text-muted">Created By {this.state.createdBy}</small>
                        </Card.Footer>
                    </Card>
                    <h1>Songs:</h1>
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <td>Song Name</td>
                                <td>Artist</td>
                                <td>Preview</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.songs.map(
                                    song =>
                                        <tr key={song.id}>
                                            <td>{song.name}</td>
                                            <td>{song.artists[0].name}</td>
                                            <td onClick={() => { this.openPreviewUrl(song.preview_url) }}>Click To Open Preview</td>
                                            <td>{this.renderDeleteButton(song)}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}