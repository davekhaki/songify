import React, { Component } from 'react';
import { Card, CardDeck } from 'react-bootstrap';
import PlaylistService from '../../services/rest/playlist.service';

export default class MyPlaylists extends Component {

    constructor(props) {
        super(props)
        this.state = {
            playlists: [],
            message: undefined
        }

        this.deleteButtonClick = this.deleteButtonClick.bind(this);
    }

    componentDidMount() {
        PlaylistService.getMyPlaylists().then((response) => {
            if (response.data.length == 0) {
                this.setState({ message: "You have no playlists" })
            } else this.setState({ playlists: response.data })
        });
    }

    deleteButtonClick(event){
        alert("Deleting playlist, are you sure?")
        this.props.history.push("/delete-playlist/" + event.target.value)
    }

    renderPlaylistsOrError() {
        if (this.state.message) {
            return (
                <h2>
                    {this.state.message}
                </h2>
            )
        }
        else return (
            <div>
                <h1>Your Playlists:</h1>
                <CardDeck>
                    {this.state.playlists.map(playlist =>
                        <Card key={playlist.id} className="bg-dark text-white">
                            <Card.Img variant="top" src="http://via.placeholder.com/640x360" />
                            <Card.Body>
                                <Card.Title>{playlist.title}</Card.Title>
                                <Card.Text>{playlist.description}</Card.Text>
                                <button onClick={this.deleteButtonClick} value={playlist.id} className=" btn btn-danger" data-cy={playlist.title}>Delete</button>
                            </Card.Body>
                            <Card.Footer>
                                <small className="text-muted">Created By {playlist.createdBy}</small>
                            </Card.Footer>
                        </Card>
                    )}
                </CardDeck>
            </div>
        )
    }

    render() {
        return (
            <div>
                {this.renderPlaylistsOrError()}
            </div>
        )
    }
}
