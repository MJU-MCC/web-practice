import {createSlice} from "@reduxjs/toolkit" //createSlice 함수는 Redux 슬라이스를(상태화 액션을 포함하는 Redux 스토어의 일부)만드는 프로세스를 간소화

export const userSlice=createSlice({
    name: "user",
    initialState:{
        name:"",
        id:"",
        isLoading:false,
        isLogin:null,
    },
    reducers:{
        loginUser:(state,action)=>{
            state.name=action.payload?.name ||''
            state.id=action.payload?.id ||''
            state.isLogin=true
        },

        clearUser:(state)=>{
            state.name=""
            state.id=""
            state.isLogin=false
        },
    },
})

//createSlice에서 생성된 액션 생성자를 내보낸다. 이들은 상태를 업데이트하기 위해 액션을 디스패치하는 데 사용
export const {loginUser,clearUser}=userSlice.actions
export default userSlice.reducer