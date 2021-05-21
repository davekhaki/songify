import React from 'react';
import { Card, CardDeck } from 'react-bootstrap';

import PlaylistService from '../../services/playlist.service';

class PopularPlaylistComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            popularPlaylists:[], 
        }
    }

    componentDidMount(){
        PlaylistService.getPopularPlaylists().then((response)=>{
            console.log(response.data.content);
            this.setState({ popularPlaylists: response.data.content })
        });
    }

    render (){
        return (
            <div>
                <h1>Most Popular Playlists Of All Time</h1>
                <CardDeck>
                    {this.state.popularPlaylists.map(playlist =>
                        <Card key = {playlist.id} className="bg-dark text-white">
                            <Card.Img variant="top" src="http://via.placeholder.com/640x360" />
                            <Card.Body>
                                <Card.Title>{playlist.title}</Card.Title>
                                <Card.Text>{playlist.description}</Card.Text>
                            </Card.Body>
                            <Card.Footer>
                                <small className="text-muted">Created By {playlist.createdBy.username}</small>
                            </Card.Footer>
                        </Card>
                        )}
                </CardDeck>
            </div>
        )
    }
}

export default PopularPlaylistComponent