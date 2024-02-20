import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import './Registration.css';

export default function Registration() {
  let navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [user, setUser] = useState({
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
    if (name === 'username' && value.length < 3) {
      setError('Username must be at least 3 characters long.');
    } else if ((name === 'firstName' || name === 'lastName') && value.length < 2) {
      setError(`${name.charAt(0).toUpperCase() + name.slice(1)} must be at least 2 characters long.`);
    }
  };
  
  const jwt = localStorage.getItem('jwt');

  const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
          Authorization: `Bearer ${jwt}`
        }
  });

  const onRegistrationSubmit = async (e) => {
    e.preventDefault();

    // Check if password and confirm password match
    if (user.password !== user.confirmPassword) {
      alert("Password and confirm password do not match.");
      return;
    }

    try {
      const response = await authAxios.post("/user", user);
      setSuccessMessage(response.data);
      navigate("/login");
    } catch (error) {
      if (error.response && error.response.status === 400) {
        setError(error.response.data);
      } else {
        console.error("Error registering user:", error);
        setError("An unexpected error occurred.");
      }
    }
  };

  const { username, firstName, lastName, email, password, confirmPassword } = user;

  return (
    <div className="container mt-5 registration-form">
      <h2>Registration Form</h2>
      <form onSubmit={onRegistrationSubmit}>
        {error && <div className="alert alert-danger">{error}</div>}
        {successMessage && <div className="alert alert-success">{successMessage}</div>}
        <div className="row">
          <div className="col-md-6">
            <div className="mb-3">
              <label htmlFor="firstName" className="form-label">First Name</label>
              <input type="text" className="form-control" id="firstName" name="firstName" value={firstName} onChange={onInputChange} required />
            </div>
          </div>
          <div className="col-md-6">
            <div className="mb-3">
              <label htmlFor="lastName" className="form-label">Last Name</label>
              <input type="text" className="form-control" id="lastName" name="lastName" value={lastName} onChange={onInputChange} required />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-6">
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input type="text" className="form-control" id="username" name="username" value={username} onChange={onInputChange} required />
            </div>
          </div>
          <div className="col-md-6">
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input type="email" className="form-control" id="email" name="email" value={email} onChange={onInputChange} required />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-6">
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input type={showPassword ? "text" : "password"} className="form-control" id="password" name="password" value={password} onChange={onInputChange} required />
              <button className="btn btn-outline-success" type="button" onClick={togglePasswordVisibility}>
                {showPassword ? "Hide" : "Show"}
              </button>
            </div>
          </div>
          <div className="col-md-6">
            <div className="mb-3">
              <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
              <input type={showPassword ? "text" : "password"} className="form-control" id="confirmPassword" name="confirmPassword" value={confirmPassword} onChange={onInputChange} required />
              <button className="btn btn-outline-success" type="button" onClick={togglePasswordVisibility}>
                {showPassword ? "Hide" : "Show"}
              </button>
            </div>
          </div>
        </div>
        <div className="mb-3">
          <button type="submit" className="btn btn-success">Register</button>
          <Link className="btn btn-danger" to="/">Cancel</Link>
        </div>
      </form>
    </div>
  );
}