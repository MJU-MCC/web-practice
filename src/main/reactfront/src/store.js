import { configureStore } from '@reduxjs/toolkit';
import userReducer from './userSlice';

const store = configureStore({
    reducer: {
    user: userReducer, // userSlice를 store에 포함시킵니다.
    // 다른 리듀서들도 있다면 이곳에 추가합니다.
},
});

export default store;
