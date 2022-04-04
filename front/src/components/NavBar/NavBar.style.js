import styled from "styled-components";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";

const SiteLogo = styled.img`
    height: 4rem;
    margin-right: 10px;
`;

const NavigationBar = styled(Navbar)`
    height: 90px;
    background-color: #1e2f23;
    padding: 0 2rem;
    box-shadow: 5px 7px 6px rgba(0, 0, 0, 0.5);
`;

const NavMenu = styled(Nav.Link)`
    text-decoration: none;
    transition: 0.3s all;
    color: #fff !important;
    flex-direction: flex;
    font-size: 25px;
    padding: 0 1rem;
    :hover {
        color: #5B8F6A !important;
    }
`;



export {
    SiteLogo,
    NavigationBar,
    NavMenu,
}