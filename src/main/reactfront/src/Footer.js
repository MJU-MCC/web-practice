import React from 'react';
import './assets/styles/Footer.scss'

const Footer = () => {
    return (
        <div className='footer'>
            <div className='text'>
                <p>MCC Myungji Coding Club (명지대학교 코딩 동아리)</p>
                <p>Copyright©2024.MCC. All rights reserved.</p><span/>
                <div className='nav'>
                    <span>Notion</span>
                    <span>Instagram</span>
                    <span>Github</span>
                </div>
            </div>
        </div>
    );
};

export default Footer;