import React from 'react';
import image from '../assets/images/IMG_1027.PNG'
import '../assets/styles/404Page.scss'
import { Link } from 'react-router-dom';

const NotFound = () => {
    return (
        <div className='error'>
            <div className='image_mcc'>
                <img src={image}></img>
            </div>
            <div className='text'>
                <p>Error: 404 잘못된 접근입니다.</p>
            </div>
            <div className='button'>
                <Link to='/'>
                    <input type='button' value={'Home'}></input>
                </Link>
            </div>
        </div>
    );
};

export default NotFound;