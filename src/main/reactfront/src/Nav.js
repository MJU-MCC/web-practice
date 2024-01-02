import React from 'react';
import Logo from './assets/images/IMG_1027.PNG'
import './assets/styles/Nav.scss'
import {Link} from 'react-router-dom'
const Nav = () => {
    return (
        <div className='Navbar'>
            <Link to='/'>
            <img id='logo' src={Logo} alt='logo'></img>
            </Link>
            <p id='about'>About</p>
            <p id='study'>Study</p>
            <p id='project'>Project</p>
            <p id='vote'>Vote</p>
            <Link to='/mypage'>
                <p id='mypage'>My Page</p>
            </Link>
            <Link to='/sign'>
                <p id='login'>Login</p>
            </Link>
            
        </div>
    );
};

export default Nav;