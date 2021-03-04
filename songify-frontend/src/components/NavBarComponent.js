import React from 'react';
import { Navbar, Nav, Button } from 'react-bootstrap';


class HeaderComponent extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Navbar bg="primary" variant="dark">
                <Navbar.Brand href="">Songify</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="">Home</Nav.Link>
                    <Nav.Link href="">Search</Nav.Link>
                    <Nav.Link href="">Your Library</Nav.Link>
                </Nav>
                <Button variant="outline-light">Sign In</Button>

            </Navbar>
        )
    }
}

export default HeaderComponent;