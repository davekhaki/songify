import React, { Component } from 'react';
import SongService from '../../services/spotify/song.service';

const SearchResultTable = (props) => {
    const [popUpMenu, setPopUpMenu] = React.useState(false);
    const items = props.songs;

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
                            <button onClick={()=> setPopUpMenu(!popUpMenu)}>
                                <li class="fas fa-bars"></li>
                            </button>
                            {popUpMenu && PopUpMenu()}
                        </td>                        
                    </tr>
                ))}
            </tbody>
        </table>
    );
};
  
  function PopUpMenu() {
    return (
      <ul className="drop-down">
        <li>Menu-item-1</li>
        <li>Menu-item-2</li>
        <li>Menu-item-3</li>
      </ul>
    );
  }

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
            show: false
        }

        this.onChangeSearchTerm = this.onChangeSearchTerm.bind(this);
        this.search = this.search.bind(this);
        this.nextPage = this.nextPage.bind(this);
        this.prevPage = this.prevPage.bind(this);
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
                    <button onClick={this.prevPage} className="btn btn-info rounded submit px-3" data-cy="prevpagebtn">Previous Page</button>
                    <button onClick={this.nextPage} className="btn btn-info rounded submit px-3" data-cy="nextpagebtn">Next Page</button>
                </div>
                <SearchResultTable songs={this.state.songs} ></SearchResultTable>
            </div>
        )
    }
}