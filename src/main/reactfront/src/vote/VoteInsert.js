import axios from 'axios';
import React, { useCallback, useState } from 'react';
import { useNavigate } from 'react-router-dom';


const VoteInsert = () => {
    const [voteName, setVoteName]=useState([
        {id:1, text:""},
    ])
    const [inputVoteName, setInputVoteName]=useState('')

    const [teamName, setTeamName]=useState([])
    const [inputTeam, setInputTeam]=useState('')
    const [nextTeamId,setNextTeamId]=useState(2)

    const [option,setOption]=useState([])
    const [inputOption,setInputOption]=useState('')
    const [nextOptionId, setNextOptionId]=useState(2)

    const navigate=useNavigate()

    const onTitleChange=useCallback((e)=>{
        setInputVoteName(e.target.value)
    },[setInputVoteName])

    const onTeamNameChange=useCallback((e)=>{
        setInputTeam(e.target.value)
    },[setInputTeam])

    const onOptionChange=useCallback((e)=>{
        setInputOption(e.target.value)
    },[setInputOption])

    const onTitleSubmit=useCallback((e)=>{
        e.preventDefault()
        setVoteName(inputVoteName)
    },[inputVoteName,voteName])

    const onTeamSubmit=useCallback((e)=>{
        e.preventDefault()
        if(inputTeam.trim()==='') return
        const nextTeams=teamName.concat({
            id:nextTeamId,
            text:inputTeam,
        })
        setNextTeamId(nextTeamId+1)
        setTeamName(nextTeams)
        setInputTeam('')
    },[inputTeam,teamName,nextTeamId])

    const onOptionSubmit=useCallback((e)=>{
        e.preventDefault()
        if(inputOption.trim()==='') return
        const nextOptions=option.concat({
            id:nextOptionId,
            text:inputOption,
        })
        setNextOptionId(nextOptionId+1)
        setOption(nextOptions)
        setInputOption('')
    })

    const onTeamRemove=(id)=>{
        const nextTeams=teamName.filter((value)=>value.id!==id)
        setTeamName(nextTeams)
    }

    const onOptionRemove=(id)=>{
        const nextOptions=option.filter((value)=>value.id!==id)
        setOption(nextOptions)
    }

    const teamList=teamName.map((value)=>(
        <div key={value.id}>
            <p>{value.text}</p><hr/>
            <button onClick={()=>onTeamRemove(value.id)}>-</button>
        </div>
    ))

    const optionList=option.map((value)=>(
        <div key={value.id}>
            <p>{value.text}</p><hr/>
            <button onClick={()=>onOptionRemove(value.id)}>-</button>
        </div>
    ))

    const voteSend=(e)=>{
        e.preventDefault()
        const voteData={
            vote:{
                voteName:inputVoteName,
                evaluation:option.map(optionItem=>optionItem.text),
            },
            team:teamName.map(teamItem=>({teamItem:teamItem.text})),
        }
        axios.post("./mcc/vote/regist",voteData)
        .then((res)=>{
            console.log(res.data)
        })
        .catch((error)=>{
            console.error(error)
        })
    }

    const onSubmit=(e)=>{
        e.preventDefault()
        voteSend(e)
        navigate('/vote')
    }

    return (
        <div>
            <form onSubmit={onSubmit}>
                <input type='text' id='title' placeholder='투표 제목' onChange={onTitleChange} value={inputVoteName}></input>
                <br></br><br></br>
                <div>
                    <fieldset>
                        <legend><input type='text' placeholder='팀명' value={inputTeam} onChange={onTeamNameChange} ></input></legend>
                        {teamList}
                        <button type='submit' onClick={onTeamSubmit}>+</button>
                    </fieldset>
                </div>
                <br/><br/>
                <div>
                    <fieldset>
                        <legend><input type='text' placeholder='평가 항목' value={inputOption} onChange={onOptionChange}></input></legend>
                        {optionList}
                        <button type='submit' onClick={onOptionSubmit}>+</button>
                    </fieldset>
                </div>
                <br/><br/>
                <button type='submit' onClick={onSubmit}>등록</button>
            </form>
        </div>
    );
};

export default VoteInsert;