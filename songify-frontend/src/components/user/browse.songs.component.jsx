import React, { Component } from 'react';
import SongService from '../../services/spotify/song.service';
import PlaylistService from '../../services/rest/playlist.service';
import Popover from '@material-ui/core/Popover';
import Button from '@material-ui/core/Button';

const SearchResultTable = (props) => {
    const [popUpMenu, setPopUpMenu] = React.useState(false);
    const items = props.songs;
    const playlists = props.playlists;
    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;

    return (
        <table className="table table-striped" data-cy="searchresulttable">
            <thead>
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Artist</th>
                    <th>Album Type</th>
                    <th>Album Name</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {items.map((song) => (
                    <tr key={song.id}>
                        <td width="10%"><img src={song.album.images[2].url} /></td>
                        <td width="20%">{song.name}</td>
                        <td width="20%">{song.artists[0].name}</td>
                        <td width="20%">{song.album.album_type}</td>
                        <td width="20%">{song.album.name}</td>
                        <td width="20%">
                            <Button aria-describedby={id} variant="contained" className="btn btn-primary" onClick={handleClick}>
                                Add To..
                            </Button>
                            <Popover
                                id={id}
                                open={open}
                                anchorEl={anchorEl}
                                onClose={handleClose}
                                anchorOrigin={{
                                    vertical: 'bottom',
                                    horizontal: 'center',
                                }}
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'center',
                                }}
                                
                            >
                                <ul className="list-group">
                                    {
                                        playlists.map(
                                            playlist => //arrow function in onclick passes it instead of running it
                                            <li className="list-group-item" onClick={() => {props.handle(playlist.id, song.id)}} id={playlist.id}>{playlist.title}</li>
                                            
                                        )
                                    }
                                </ul>
                            </Popover>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};


export default class BrowseSongs extends Component {

    constructor(props) {
        super(props)
        this.state = {
            searchTerm: "",
            songs: [],
            total: "",
            pageNr: 0,
            nextPageQuery: "",
            prevPageQuery: "",
            show: false,
            playlists: [],
        }

        this.onChangeSearchTerm = this.onChangeSearchTerm.bind(this);
        this.search = this.search.bind(this);
        this.nextPage = this.nextPage.bind(this);
        this.prevPage = this.prevPage.bind(this);
        this.handleAddToPlaylist = this.handleAddToPlaylist.bind(this);
    }

    componentDidMount() {
        PlaylistService.getMyPlaylists().then((response) => {
            this.setState({ playlists: response.data })
        })
    }

    handleAddToPlaylist(playlistId, songId) {
        PlaylistService.addSongToPlaylist(playlistId, songId).then((response)=>{
            console.log(response);
            console.log(response.status);
            if(response.status === 200){
                alert('Song added.');
            }
        })

    }

    onChangeSearchTerm(e) {
        this.setState({ searchTerm: e.target.value })
    }

    search() {
        SongService.searchForSongs(this.state.searchTerm).then((response) => {
            this.setState({ songs: response.data.tracks.items })
            this.setState({ total: response.data.tracks.total })
            this.setState({ pageNr: 0 })
            console.log(response.data.tracks)
            this.setState({ nextPageQuery: response.data.tracks.next })
            this.setState({ prevPageQuery: response.data.tracks.previous })
        })
    }

    nextPage() {
        SongService.getSongsByQueryUrl(this.state.nextPageQuery).then((response) => {
            this.setState({ songs: response.data.tracks.items })
            this.setState({ pageNr: this.state.pageNr + 1 })
            this.setState({ nextPageQuery: response.data.tracks.next })
            this.setState({ prevPageQuery: response.data.tracks.previous })
        })
    }

    prevPage() {
        if (this.state.pageNr == 0) {
            return alert('No previous page available')
        }
        else if (this.state.pageNr == 1) {
            this.search()
        }
        else {
            SongService.getSongsByQueryUrl(this.state.prevPageQuery).then((response) => {
                this.setState({ songs: response.data.tracks.items })
                this.setState({ pageNr: this.state.pageNr - 1 })
                this.setState({ nextPageQuery: response.data.tracks.next })
                this.setState({ prevPageQuery: response.data.tracks.previous })
            })
        }
    }

    render() {
        return (
            <div>
                <div className="input-group col-md-6 col-lg-4 " data-cy="searchbarinput">
                    <input
                        autoFocus
                        type="text"
                        value={this.state.searchTerm}
                        onChange={this.onChangeSearchTerm}
                        className="form-control rounded-left"
                        placeholder="Name"
                        required=""
                        data-cy="searchterminput" />
                    <button onClick={this.search} className="btn btn-primary rounded submit px-3" data-cy="searchbtn">Search</button>
                </div>
                <div>
                    <span>Total Results Found: {this.state.total}</span>
                    <button onClick={this.prevPage} className="btn btn-primary rounded submit px-3" data-cy="prevpagebtn">Previous Page</button>
                    <button onClick={this.nextPage} className="btn btn-primary rounded submit px-3" data-cy="nextpagebtn">Next Page</button>
                </div>
                <SearchResultTable songs={this.state.songs} playlists={this.state.playlists} handle={this.handleAddToPlaylist}></SearchResultTable>
            </div>
        )
    }
}