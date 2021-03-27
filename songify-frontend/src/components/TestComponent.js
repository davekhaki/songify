import React from 'react';
import TokenService from '../services/external/TokenService';
import GenreService from '../services/external/GenreService';

class TestComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            token:'',
            genres:[]
        }
    }

    componentDidMount(){
        TokenService.getToken().then((response)=>{
            this.setState({ token: response.data.access_token})
        });

        GenreService.getGenres().then((response) =>{
            this.setState({ genres: response.data.categories.items})
            console.log(this.state.genres);
        })
    }

    render (){
        return (
            <div>
                {this.state.token}
                <h1 className = "text-center"> Genre list from spotify API</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>href</td>
                            <td>icon (URL)</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.genres.map(
                                genre =>
                                <tr key={genre.id}>
                                    <td>{genre.id}</td>
                                    <td>{genre.name}</td>
                                    <td>{genre.href}</td>
                                    <td>{genre.icons[2]}</td>
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        )
    }
}

export default TestComponent