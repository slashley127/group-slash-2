import React, { useState } from 'react';
import { Dropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './Homepage.css'; 

const HomePage = () => {
  const [selectedRole, setSelectedRole] = useState('');

  const handleRoleSelect = (role) => {
    console.log('Selected Role:', role);
    setSelectedRole(role);
  };

  return (
    <div className="home-page-container">
      <h1>RoomRanger</h1>
      <div className="container mt-4 d-flex justify-content-center align-items-center">
        <Dropdown>
          <Dropdown.Toggle variant="Black" id="dropdown-basic">
            I am: {selectedRole} 
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item onClick={() => handleRoleSelect('RoomAttendant')}>
              Room Attendant
            </Dropdown.Item>
            <Dropdown.Item onClick={() => handleRoleSelect('Manager')}>
              Manager
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>

        {selectedRole === 'RoomAttendant' && (
          <Link to="/login" className="dropdown-item">Login</Link>
        )}

        {selectedRole === 'Manager' && (
          <ul className="dropdown-item">
            <Link to="/registration" className="dropdown-item">Registration</Link>
            <Link to="/login" className="dropdown-item">Login</Link>
          </ul>
        )}
      </div>

      <footer className="home-page-footer">
        
        <Link to="/about" >About us</Link>
        © 2024 RoomRanger. All Rights Reserved.
      </footer>
    </div>
  );
};
export default HomePage;
