import React from 'react';
import { Card, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import roomrangerImage from './roomranger.jpg';
const HomePage = () => {
  return (
    <div className="d-flex flex-column min-vh-100">
      {/* Main content */}
      <div className="container mt-4 flex-grow-1">
        {/* Image in the center */}
        <img
          src={roomrangerImage} 
          alt="RoomRanger"
          style={{ maxWidth: '600px', maxHeight: '400px' }} 
          className="img-fluid rounded mx-auto d-block mb-4"
        />

        {/* Cards for Registration, Login, About Us, Contact Us */}
        <div className="row mt-4">
          <div className="col-md-6">
            <Card>
              <Card.Body>
                <Card.Title>Registration</Card.Title>
                <Button as={Link} to="/registration" variant="primary">
                  Go to Registration
                </Button>
              </Card.Body>
            </Card>
          </div>
          <div className="col-md-6">
            <Card>
              <Card.Body>
                <Card.Title>Login</Card.Title>
                <Button as={Link} to="/login" variant="primary">
                  Go to Login
                </Button>
              </Card.Body>
            </Card>
          </div>
        </div>

        <div className="row mt-4">
          <div className="col-md-6">
            <Card>
              <Card.Body>
                <Card.Title>About Us</Card.Title>
                <Button as={Link} to="/about" variant="primary">
                  Learn More
                </Button>
              </Card.Body>
            </Card>
          </div>
          <div className="col-md-6">
            <Card>
              <Card.Body>
                <Card.Title>Contact Us</Card.Title>
                <Button as={Link} to="/contact" variant="primary">
                  Contact
                </Button>
              </Card.Body>
            </Card>
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-dark text-light text-center p-3 mt-4">
        © 2024 RoomRanger. All Rights Reserved.
      </footer>
    </div>
  );
};

export default HomePage;