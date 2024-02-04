import React from 'react';
import Nav from './Nav';
import Home from './Home';
import Sign from './Sign';
import Vote from './Vote';
import { BrowserRouter,Route,Routes} from 'react-router-dom';
import Mypage from './Mypage';
import { Provider } from 'react-redux';
import store from './store'
import VoteInsert from './vote/VoteInsert';
import VotePage from './vote/VotePage';
import NotFound from './pages/404';
import Footer from './Footer';
import './assets/styles/default.scss'
import 'bootstrap/dist/css/bootstrap.min.css';
import Project from './Project';




const App = () => {
  return (
    <Provider store={store}>
      <BrowserRouter>
      <div className='default'>
        <Nav/>
        <Routes className='main'>
          <Route exact path='/' element={<Home/>}/>
          <Route path='/sign' element={<Sign/>}/>
          <Route path='/mypage' element={<Mypage/>}/>
          <Route path='/vote' element={<Vote/>}/>
          <Route path='/teaminsert' element={<VoteInsert/>}/>
          <Route path='/votepage' element={<VotePage/>}/>
          <Route path='/project' element={<Project/>}/>
        </Routes>
        <Footer/>
      </div>
      </BrowserRouter>
    </Provider>
    
  );
};
export default App;