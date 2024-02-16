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
import EditAssignedRoom from './components/manager/EditAssignedRoom';
import AttendantListComponent from './components/roomAttendants/AttendantListComponent';
import AddAttendant from './components/roomAttendants/AddAttendant';
import Update from './components/roomAttendants/Update';
import Profile from './components/roomAttendants/Profile';
// import { Footer } from './components/Footer';

function App() {
  return (
    <div className="App">
      <Router>
        <NavBar />

        <Routes>
          {/* give path /addroom to the AddRoom page */}
          {/* <Route exact path="/login" element={<LoginPage/>}/> */}
<Route exact path="/attendantListComponent" element={<AttendantListComponent />} />

 <Route exact path="/add" element={<AddAttendant />} />
        <Route exact path="/update/:id" element={<Update/>} />
        <Route exact path="/profile/:id" element={<Profile/>} />

          <Route exact path="/rooms/addroom" element={<AddRoom />} />
          <Route exact path="assignedrooms" element={<HomeManager />} />
          <Route exact path="assignedrooms/assignroomform" element={<AssignRoom />} />
          <Route exact path="assignedrooms/editassignedroom/:id" element={<EditAssignedRoom/>} />
          <Route exact path="/rooms" element={<Home />} />
          <Route exact path="/rooms/editroom/:id" element={<EditRoom/>} />
          <Route exact path="/rooms/viewroom/:id" element={<ViewRoom/>} />

        </Routes>
      </Router>
    {/* <Footer/> */}
    </div>

  );
}

export default App;
