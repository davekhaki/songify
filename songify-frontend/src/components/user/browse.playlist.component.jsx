import React, { Component } from 'react';
import PlaylistService from '../../services/rest/playlist.service';

const SearchResultTable = (props) => {
    const items = props.playlists;

    return (
        <table className="table table-striped" data-cy="searchresulttable">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Creator</th>
                </tr>
            </thead>
            <tbody>
                {items.map((playlist) => (
                    //arrow function for onclick stops it from running on render
                    <tr key={playlist.id} onClick={() => { props.openPlaylist(playlist.id) }}> 
                        <td>{playlist.title}</td>
                        <td>{playlist.description}</td>
                        <td>{playlist.createdBy}</td>                       
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default class BrowsePlaylists extends Component {

    constructor(props) {
        super(props)
        this.state = {
            searchTerm: "",
            playlists: [],
        }

        this.onChangeSearchTerm = this.onChangeSearchTerm.bind(this);
        this.search = this.search.bind(this);
        this.openPlaylist = this.openPlaylist.bind(this);
    }

    onChangeSearchTerm(e){
        this.setState({searchTerm: e.target.value})
    }

    search(){
        PlaylistService.getPlaylistsByUsername(this.state.searchTerm).then((response)=>{
            this.setState({playlists: response.data})
        })
    }

    openPlaylist(id){
        this.props.history.push('/playlist/'+ id);
    }

    render() {
        return (
            <div>
                <div className="input-group col-md-6 col-lg-4" data-cy="searchbarinput">
                    <input
                        autoFocus
                        type="text"
                        value={this.state.searchTerm}
                        onChange={this.onChangeSearchTerm}
                        className="form-control rounded-left"
                        placeholder="Username"
                        required=""
                        data-cy="searchterminput" />
                    <button onClick={this.search} className="btn btn-primary rounded submit px-3" data-cy="searchbtn">Search</button>
                    
                </div>
                <SearchResultTable playlists={this.state.playlists} openPlaylist={this.openPlaylist}></SearchResultTable>
            </div>
        )
    }
}
