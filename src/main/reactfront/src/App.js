import React from 'react';
import Nav from './Nav';
import Home from './Home';
import Sign from './Sign';
import { BrowserRouter,Route,Routes} from 'react-router-dom';
import Mypage from './Mypage';
import { Provider } from 'react-redux';
import store from './store'

const App = () => {
  return (
    <Provider store={store}>
      <BrowserRouter>
      <div>
        <Nav/>
        <Routes>
          <Route exact path='/' element={<Home/>}/>
          <Route path='/sign' element={<Sign/>}/>
          <Route path='/mypage' element={<Mypage/>}/>
        </Routes>
        {/* {user.isLogin ?<Mypage/>:<Sign/>} */}
      </div>
      </BrowserRouter>
    </Provider>
  );
};

export default App;