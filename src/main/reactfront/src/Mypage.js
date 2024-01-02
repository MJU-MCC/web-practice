import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { clearUser } from './userSlice';
import {useNavigate } from 'react-router-dom';
import axios from 'axios';

const Mypage = () => {
    const user=useSelector((state)=>state.user)
    const dispatch=useDispatch()
    const navigate=useNavigate()

    const LogoutFunc=()=>{

        axios.get('/mcc/logout')
        .then((res)=>{
            console.log(res.data)
            if(res.data.success){
                dispatch(clearUser())
                console.log('Logout')
                navigate('/')
            }
        })

    }
    return (
        <div>
            <h1>My Page</h1>
            <p>{user.name}님, 안녕하세요</p>
            <button onClick={LogoutFunc}>Logout</button>
        </div>
    );
};

export default Mypage;