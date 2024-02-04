import React, { useState } from 'react';
import Icon from './assets/images/2.png'
import './assets/styles/Project.scss'
import Img from './assets/images/5.png'

const Project = () => {
    const [optionClick, setOptionClick]=useState();

    const clickHandler=(optionId)=>{
        setOptionClick(optionId)
    }
    return (
        <div className='project'>
            <img id='img' src={Icon}></img>
            <div className='option'>
                <div onClick={()=>clickHandler('total')}>
                    <p className={optionClick==='total'?'active':''}>전체</p>
                </div>
                <div onClick={()=>clickHandler('40기')}>
                    <p className={optionClick==='40기'?'active':''}>40기</p>
                </div>
            </div>

            <div className='grid-container'>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
                <img className='grid-item'src={Img}></img>
            </div>
        </div>
    );
};

export default Project;