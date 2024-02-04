import React, { useEffect, useState } from 'react';
import '../assets/styles/VotePage.scss'
import axios from 'axios';
import { Link } from 'react-router-dom';

const VotePage = () => {

    const [voteName,setVoteName]=useState()
    const [team,setTeam]=useState([])
    const [options,setOptions]=useState([])

    useEffect(()=>{
        try{
            axios.get("http://localhost:8080/mcc/vote/form/{voteName}")
        .then(res=>{
            const voteTitle=res.data.title
            setVoteName(voteTitle)
            const voteTeam=res.data.teams||[]
            setTeam(voteTeam)
            console.log(voteTeam)
            const voteOption=res.data.evaluations||[]
            setOptions(voteOption)
            console.log(voteOption)
        })
    }catch(error){
        console.error("Error",error)
        
    }
},[])
    return (
        <div>
            <p>{voteName}</p>
            {team.map((teamList,index)=>(
                <p key={index}>{teamList}</p>
            ))}
            {options.map((option,index)=>(
                <p key={index}>{option}</p>
            ))}
            
        </div>
    );
};

export default VotePage;