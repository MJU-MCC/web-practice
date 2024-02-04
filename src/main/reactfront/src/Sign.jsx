import React, {useEffect, useState} from 'react';
import './assets/styles/Sign.scss'
import {useDispatch, useSelector} from 'react-redux'
import { loginUser } from './userSlice';
// import { Provider } from 'react-redux';
import axios from 'axios';
import {useNavigate} from 'react-router-dom'

const Sign = () => {
    //state를 변화시켜서 데이터를 변화시켜줘야한다.
const [memberNum, setmemberNum]=useState("")
const [memberPwd, setMemberPwd]=useState("")

const dispatch=useDispatch()
const navigate=useNavigate()

const [loading, setLoading]=useState(false)
const [msg, setMsg]=useState("") //통신 결과에 따라 유저에세 띄어줄 메시지 관리 state

//onChange라는 이벤트를 발생시켜서 state 값을 변경해 value 값을 바꾼다.
const onIdHandler=(e)=>{
    setmemberNum(e.target.value)
}
const onPwdHandler=(e)=>{
    setMemberPwd(e.target.value)
}

const onButtonHandler=()=>{
    console.log('click login')
}

const LoginFunc=(e)=>{
    e.preventDefault()
    if(!memberNum){
        return alert("ID를 입력하세요.")
    }else if(!memberPwd){
        return alert("Password를 입력하세요")
    }else{
        let body={
            memberNumber:memberNum,
            memberPassword:memberPwd,
        }
        axios.post("./mcc/login",body)
        .then((res)=>{
            try{
                console.log(res.data)
                if(res.data.code===200){
                    dispatch(loginUser(res.data.userInfo))
                    setMsg("")
                    navigate('/mypage')
                }

                if(res.data.code===400){
                    setMsg("회원 정보를 찾을 수 없습니다.")
                }
                setLoading(true)
            }catch(error){
                console.error("error occured: ",error)
            }
        })
        .then((res)=>{

            if(res.accessToken){
                localStorage.setItem('login-token',res.data.accessToken)
            }
        })

        .catch((error) => {
            console.error("axios error: ", error);
        });

        
        
    }
    
}
useEffect(()=>{
    if(msg){
        setTimeout(()=>{
            setMsg("")
            setLoading(false)
        },1500)
    }
},[msg])

useEffect(()=>{
    if(user&& user.isLogin){
        navigate('/mypage')
    }
})

let user=useSelector((state)=>{return state.user})

    return (
        <div className='login'>
            <form onSubmit={LoginFunc}>
                <p id='login-title'>Login</p>
                <p>ID</p>
                <input type='text' value={memberNum} onChange={onIdHandler} ></input>
                <p>PASSWORD</p>
                <input type='memberPwd' value={memberPwd} onChange={onPwdHandler}></input><br/>
                <button type='submit' onClick={onButtonHandler} disabled={loading}>Login</button><br/>
                {msg}
            </form>
        </div>
    );
};

export default Sign;