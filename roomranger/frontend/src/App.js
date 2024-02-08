import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavBar from './components/NavBar';
import Home from './components/rooms/Home';
import HomeManager from './components/manager/HomeManager';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import AddRoom from './components/rooms/AddRoom';
import EditRoom from './components/rooms/EditRoom';
import ViewRoom from './components/rooms/ViewRoom';
import AssignRoom from './components/manager/AssignRoomForm';
import Footer
 from './components/Footer';
function App() {
  return (
    <div className="App">
      <Router>
        <NavBar />

        <Routes>
          {/* give path /addroom to the AddRoom page */}
          <Route exact path="/rooms/addroom" element={<AddRoom />} />
          <Route exact path="manager" element={<HomeManager />} />
          <Route exact path="manager/assignroom" element={<AssignRoom />} />
          <Route exact path="/rooms" element={<Home />} />
          <Route exact path="/rooms/editroom/:id" element={<EditRoom/>} />
          <Route exact path="/rooms/viewroom/:id" element={<ViewRoom/>} />
        </Routes>
      </Router>
      <Footer/>
    </div>
  );
}

export default App;
