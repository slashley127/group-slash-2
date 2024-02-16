
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Home from "./pages/Home.js";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Registration from "./users/Registration";
import LoginPage from "./components/loginPage/LoginPage";
import About from "./users/About";
import AddRoom from "./components/rooms/AddRoom";
import EditRoom from "./components/rooms/EditRoom";
import ViewRoom from "./components/rooms/ViewRoom";
import HomeRoom from "./components/rooms/HomeRoom";
import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';

//import NavBar from './components/NavBar';
import AttendantListComponent from './components/roomAttendants/AttendantListComponent';
import AddAttendant from './components/roomAttendants/AddAttendant';
import Update from './components/roomAttendants/Update';
import Profile from './components/roomAttendants/Profile';
//import { Header } from './components/Header';
//import { Footer } from './components/Footer';
//import LoginPage from './components/loginPage/LoginPage';
// import ManagerHomePage from './components/ManagerHomePage';
//import Homepage from './components/manager/Homepage';
//import ErrorComponent from './components/ErrorComponent';

//import RouteHome from './components/Route';
 // import HomeAttendant from './components/attendantRolePage/HomeAttendant';
// import LeaveList from './components/leaveRequest/LeaveList';
import LeaveForm from './components/leaveRequest/LeaveForm';
import LeaveList from './components/leaveRequest/LeaveList';
import EditRequest from './components/leaveRequest/EditRequest';
//import AssignRoom from './components/manager/AssignRoom';

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
        <Container fluid>
          <Row>
            <Col md={6}>
              <Routes>
                <Route exact path="/registration" element={<Registration />} />
                <Route exact path="/loginPage" element={<LoginPage />} />
                <Route exact path="/about" element={<About />} /> 
                <Route exact path="/" element={<Home />} />
                <Route exact path="/rooms/addRoom" element={<AddRoom />} />
                <Route exact path="/rooms/editRoom" element={<EditRoom />} />
                <Route exact path="/rooms/viewRoom" element={<ViewRoom />} />
                <Route exact path="/rooms/HomeRoom" element={<HomeRoom />} />

              </Routes>
            </Col>
            <Col md={6}>
              {/* Content for the second column */}
            </Col>
          </Row>
        </Container>
        </Router>
    </div>
  );
}

export default App;