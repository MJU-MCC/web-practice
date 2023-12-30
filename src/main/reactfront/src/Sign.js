import React from 'react';
import './assets/styles/Sign.scss'

const Sign = () => {
    return (
        <div className='login'>
            <p id='login-title'>Login</p>
            <form id='login-form'>
                <p>ID</p>
                <input type='text' name='id'></input>
                <p>PASSWORD</p>
                <input type='password' name='password'></input><br/>
                <button type='submit'>Login</button>
            </form>
        </div>
    );
};

export default Sign;