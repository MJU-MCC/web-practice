import React, { useEffect, useState } from 'react';
import './assets/styles/Vote.scss'
import { Link } from 'react-router-dom';
import axios from 'axios';

const Vote = () => {

    const [voteList, setVoteList]=useState([])

    useEffect(()=>{
        try{
            axios.get("http://localhost:3000/mcc/vote/list")
                .then(res=>{
                    const voteArray= res.data.votes || []
                    setVoteList(voteArray)
                    console.log(voteArray)
            })
        }catch(error){
            console.error("Error fetching vote list",error)
        }
    },[])
    
    return (
        <div className='voteMain'>
            <div className='votes'>
            <Link to='/votepage' style={{textDecoration:'none'}}>
                {voteList.map((voteName,index)=>(
                    <p id='title' key={index}> ☑︎ {voteName}</p>
                ))}
            </Link>
            </div>
            <div>
                <form>
                    <Link to='/teaminsert'>
                        <button type='submit'>New Vote</button>
                    </Link>
                </form>
            </div>
        </div>
    );
};

export default Vote;