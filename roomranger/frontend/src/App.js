import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Home from "./pages/Home.js";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Registration from "./users/Registration";
import Login from "./users/Login";
import About from "./users/About";
import AddRoom from "./components/rooms/AddRoom";
import EditRoom from "./components/rooms/EditRoom";
import ViewRoom from "./components/rooms/ViewRoom";
import Manager from "./components/rooms/Manager";
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
      <button onClick={toggleTheme}>Toggle Theme</button>
      <Router>
        <Container fluid>
          <Row>
            <Col md={6}>
              <Routes>
                <Route exact path="/registration" element={<Registration />} />
                <Route exact path="/login" element={<Login />} />
                <Route exact path="/about" element={<About />} /> 
                <Route exact path="/" element={<Home />} />
                <Route exact path="/rooms/addRoom" element={<AddRoom />} />
                <Route exact path="/rooms/editRoom" element={<EditRoom />} />
                <Route exact path="/rooms/viewRoom" element={<ViewRoom />} />
                <Route exact path="/rooms/manager" element={<Manager />} />
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
