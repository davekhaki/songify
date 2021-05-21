import React, {Component} from 'react';
import PlaylistService from '../../services/playlist.service';
import { Card} from 'react-bootstrap';

export default class SpecificPlaylist extends Component{

    constructor(props){
        super(props)
        this.state = {
            id: this.props.match.params.id,
            title: "",
            desc: "",
            createdBy: "",
            message: "",
        }

    }

    componentDidMount(){
        PlaylistService.getPlaylistById(this.state.id).then((response)=>{
            this.setState({
                title: response.data.title,
                desc: response.data.description,
                createdBy: response.data.createdBy
            })
        });
    }


    render (){
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
            </div>
        )
    }
}