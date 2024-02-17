import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import NavBar from './components/NavBar';
import HomeRoom from './components/rooms/HomeRoom';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddRoom from "./components/rooms/AddRoom";
import EditRoom from "./components/rooms/EditRoom";
import ViewRoom from "./components/rooms/ViewRoom";
import AssignRoom from './components/manager/AssignRoomForm';
import EditAssignedRoom from './components/manager/EditAssignedRoom';
import AttendantListComponent from './components/roomAttendants/AttendantListComponent';
import AddAttendant from './components/roomAttendants/AddAttendant';
import Update from './components/roomAttendants/Update';
import Profile from './components/roomAttendants/Profile';
import { Footer } from './components/Footer';
import LoginPage from "./components/loginPage/LoginPage";
import HomeManager from './components/manager/HomeManager';
import ErrorComponent from './components/ErrorComponent';
import RouteHome from './components/Route';
import LeaveForm from './components/leaveRequest/LeaveForm';
import LeaveList from './components/leaveRequest/LeaveList';
import EditRequest from './components/leaveRequest/EditRequest';
import Home from "./components/Home";
import Registration from "./components/Registration";
import About from "./components/About";
import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
function App() {
  const [isDarkMode, setIsDarkMode] = useState(false);

  useEffect(() => {
    document.body.className = isDarkMode ? 'dark-theme' : 'light-theme';
  }, [isDarkMode]);

  const toggleTheme = () => {
    setIsDarkMode((prevMode) => !prevMode);
  };

  return (
    <div className="App">
      <button onClick={toggleTheme}>Dark/Light</button>
      <Router>
        <NavBar />
        <Routes>
        <Route exact path="/" element={<Home />} />
          <Route exact path="/register" element={<Registration />} />
          <Route exact path="/login" element={<LoginPage />} />
          <Route exact path="/about" element={<About />} />  
           <Route exact path="/rooms/addRoom" element={<AddRoom />} />
          <Route exact path="/rooms/editRoom/:id" element={<EditRoom />} />
          <Route exact path="/rooms/viewRoom" element={<ViewRoom />} />
          <Route exact path="/rooms/HomeRoom" element={<HomeRoom />} />
          <Route exact path="/attendantListComponent" element={<AttendantListComponent />} />
          <Route exact path="/addAttendant" element={<AddAttendant />} />
          <Route exact path="/update/:id" element={<Update />} />
          <Route exact path="/profile/:id" element={<Profile />} />
          <Route exact path="/assignedrooms" element={<HomeManager />} />
          <Route exact path="/assignedrooms/assignroomform" element={<AssignRoom />} />
          <Route exact path="/assignedrooms/editassignedroom/:id" element={<EditAssignedRoom />} />
          <Route exact path="/leaveform" element={<LeaveForm />} />
          <Route exact path="/leavelist" element={<LeaveList />} />
          <Route exact path="/editrequest" element={<EditRequest />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;