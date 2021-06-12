import React, { Component } from 'react';
import PlaylistService from '../../services/rest/playlist.service';
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

    }

    componentDidMount() {
        PlaylistService.getPlaylistById(this.state.id).then((response) => {
            console.log(response);
            this.setState({
                title: response.data.title,
                desc: response.data.description,
                createdBy: response.data.createdBy,
                songIds: response.data.songs
            });
            // console.log(this.state.songIds);
            SongService.getSongsByIds(this.generateIdString(this.state.songIds)).then((response) => {
                this.setState({ songs: response.data.tracks })
                console.log(this.state.songs);
            })
        });
    }

    generateIdString(ids){
        var string = "";
        ids.forEach(id => {
            if(string===""){
                string += id.spotifyId
            } 
            else {
            string += ",";
            string += id.spotifyId
            }
        });
        console.log(string)
        return string;
    }

    render() {
        return (
            <div className="center">
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
                            <td>Preview URL</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.songs.map(
                                song =>
                                    <tr key={song.id}>
                                        <td>{song.name}</td>
                                        <td>{song.artists[0].name}</td>
                                        <td>{song.preview_url}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}