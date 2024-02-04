// import React, { useEffect } from 'react';
// import {useRecoilState} from 'recoil'
// import {errorState} from 'utils/recoil/error'
// import {useRouter} from 'next/router'

// const ErrorPage = () => {
//     const [errorMessage,setErrorMessage]=useRecoilState(errorState)
//     const router=useRouter()

//     useEffect(()=>{ //error 페이지로 넘어오면 url 초기화
//         router.replace(`/`)
//     },[])

//     const goHome=()=>{
//         setErrorMessage('')
//         router.push('/')
//     }
//     return (
//         <div>
//             <div>Error</div>
//             <div>{errorMessage==='DK404'
//                     ?'잘못된 요청입니다!'
//                     :'데이터 요청에 실패하였습니다.'}
//             </div>
//             <div>{errorMessage}</div>

//             <div onClick={goHome}>
//                 <input type='button' value={'🏠'}></input>
//             </div>
//         </div>
//     );
// };

// export default ErrorPage;