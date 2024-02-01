import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavBar from './components/NavBar';
import Home from './components/rooms/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
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
          <Route exact path="/addroom" element={<AddRoom />} />
          <Route exact path="/" element={<Home />} />
          <Route exact path="editroom/:id" element={<EditRoom/>} />
          <Route exact path="viewroom/:id" element={<ViewRoom/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
