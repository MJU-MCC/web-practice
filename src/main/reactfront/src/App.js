import React from 'react';
import Nav from './Nav';
import Home from './Home';
import Sign from './Sign';
import { BrowserRouter,Route,Routes} from 'react-router-dom';

const App = () => {
  return (
    <BrowserRouter>
    <div>
      <Nav/>
      <Routes>
        <Route exact path='/' element={<Home/>}/>
        <Route path='/sign' element={<Sign/>}/>
      </Routes>
    </div>
    </BrowserRouter>
  );
};

export default App;