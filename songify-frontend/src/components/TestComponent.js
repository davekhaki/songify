// import React from 'react';
// import TokenService from '../services/external/TokenService';
// import GenreService from '../services/external/GenreService';

// class TestComponent extends React.Component{

//     constructor(props){
//         super(props)
//         this.state = {
//             test:[],
//             token:'',
//             genres:[],
            
//         }
//     }

//     componentDidMount(){
//         TokenService.getToken().then((response)=>{
//             console.log(response.data.access_token);
//             this.setState({ token: response.data.access_token})
//         });

//         GenreService.getGenres().then((response) =>{
//             this.setState({ genres: response.data.categories.items})
//             console.log(this.state.genres);
//         })

//         GenreService.test().then((response) =>{
//             this.setState({ test: response.data.items})
//             console.log(this.state.test);
//             console.log("____")
//             console.log(this.state.test[0].id);
//         })
//     }

//     render (){
//         return (
//             <div>
//                 <h1 className = "text-center">List of Avicii Songs:</h1>
//                 <table className = "table table-striped">
//                     <thead>
//                         <tr>
//                             <td>Name</td>
//                             <td>Id</td>
//                             <td>Release Date</td>
//                             <td>Artist</td>
//                         </tr>
//                     </thead>
//                     <tbody>

//                         {
//                             this.state.test.map(
//                                 test =>
//                                 <tr key={test.name}>
//                                     <td>{test.name}</td>
//                                     <td>{test.id}</td>
//                                     <td>{test.release_date}</td>
//                                     <td>{test.artists[0].name}</td>
//                                 </tr>
                                
//                             )
//                         }

//                     </tbody>
//                 </table>
//                 <h1 className="text-center">Genres</h1>
//                 <table className="table table-striped">
//                     <thead>
//                         <tr>
//                             <td>ID</td>
//                             <td>Name</td>
//                             <td>href</td>
//                             <td>icon (URL)</td>
//                         </tr>
//                     </thead>
//                     <tbody>
//                         {
//                             this.state.genres.map(
//                                 genre =>
//                                 <tr key={genre.id}>
//                                     <td>{genre.id}</td>
//                                     <td>{genre.name}</td>
//                                     <td>{genre.href}</td>
//                                     <td><img src={genre.icons[0].url}/></td>
//                                 </tr>
//                             )
//                         }

//                     </tbody>
//                 </table>
//             </div>
//         )
//     }
// }

// export default TestComponent