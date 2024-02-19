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
      <div className="container mt-4 d-flex justify-content-center align-items-center ">
        <Dropdown>
          <Dropdown.Toggle variant="Black" id="dropdown-basic">
            I am: {selectedRole}
          </Dropdown.Toggle>

          <Dropdown.Menu>
            {(selectedRole !== 'Manager') && (
              <Dropdown.Item onClick={() => handleRoleSelect('Manager')}>
                Manager
              </Dropdown.Item>
            )}
            {(selectedRole !== 'RoomAttendant') && (
              <Dropdown.Item onClick={() => handleRoleSelect('RoomAttendant')}>
                Room Attendant
              </Dropdown.Item>
            )}
            {(selectedRole === 'Manager') && (
              <>
                <Dropdown.Item>
                  <Link to="/register" className="dropdown-link">Registration</Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/login" className="dropdown-link">Login</Link>
                </Dropdown.Item>
              </>
            )}
            {(selectedRole === 'RoomAttendant') && (
              <Dropdown.Item>
                <Link to="/login" className="dropdown-link">Login</Link>
              </Dropdown.Item>
            )}
          </Dropdown.Menu>
        </Dropdown>
      </div>

  
    </div>
  );
};

export default HomePage;