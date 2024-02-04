import React, { useState } from 'react';
import Logo from './assets/images/good.png'
import './assets/styles/Nav.scss'
import {Link} from 'react-router-dom'
import Button from 'react-bootstrap/Button';

const Nav = () => {
    const [activeLink, setActiveLink]=useState();

    const handleLinkClick=(linkId)=>{
        setActiveLink(linkId)
    }

    return (
        <div className='Navbar'>
            <Link to='/'  onClick={()=>handleLinkClick('home')}>
            <img id='logo' src={Logo} alt='logo' className={activeLink==='home'?'active':''}></img>
            </Link>
            <p id='about'>About</p>
            <p id='study'>Study</p>
            <Link to='/project'>
                <p id='project'>Project</p>
            </Link>
            <Link to='./vote' onClick={()=>handleLinkClick('vote')}>
                <p className={activeLink==='vote'?'active':''}>Vote</p>
            </Link>
            <Link to='/mypage' onClick={()=>handleLinkClick('mypage')}>
                <p className={activeLink==='mypage'?'active':''}>My Page</p>
            </Link>
            <Link to='/sign'>
                <Button type='button' className='btn btn-outline-primary' id='login'>Login</Button>
            </Link>
            
        </div>
    );
};

export default Nav;