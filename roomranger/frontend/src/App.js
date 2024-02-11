import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavBar from './components/NavBar';
import Home from './components/rooms/Home';
import {Router, Routes, Route } from 'react-router-dom';
import AddRoom from './components/rooms/AddRoom';
import EditRoom from './components/rooms/EditRoom';
import ViewRoom from './components/rooms/ViewRoom';

function App() {
  return (
    <div className="App">
      <Router>
        <NavBar />
        <Routes>
          {/* give path /addroom to the AddRoom page */}
          <Route exact path="/rooms/addroom" element={<AddRoom />} />
          <Route exact path="/rooms" element={<Home />} />
          <Route exact path="/rooms/editroom/:id" element={<EditRoom/>} />
          <Route exact path="/rooms/viewroom/:id" element={<ViewRoom/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
