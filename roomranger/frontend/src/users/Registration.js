import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Registration() {
  let navigate = useNavigate();

  const [loginCredentials, setLoginCredentials] = useState({
    password: "",
  });

  const [user, setUser] = useState({
    username: "",
    name: "",
    email: "",
    dob: "",
    password: "",
    role: "",
  });

  // Add the 'authenticated' state variable and its setter
  const [authenticated, setAuthenticated] = useState(false);

  const { username, name, email, dob, password, role } = user;
  const { password: loginPassword } = loginCredentials;

  const onLoginChange = (e) => {
    setLoginCredentials({ ...loginCredentials, [e.target.name]: e.target.value });
  };

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onLoginSubmit = (e) => {
    e.preventDefault();
    // Check the login credentials here (e.g., compare with a hardcoded password)
    const correctPassword = "roomranger@123";

    if (loginPassword === correctPassword) {
      // Proceed to the registration form
      // Set 'authenticated' to true
      setAuthenticated(true);
      alert("Login successful!");
    } else {
      alert("Incorrect password. Access denied.");
    }
  };

  const onRegistrationSubmit = async (e) => {
    e.preventDefault();

      await axios.post("http://localhost:8080/user", user);
      navigate("/");
    };
  return (
    <div className="container mt-5">
      {!authenticated && (
        <form onSubmit={onLoginSubmit}>
          <label htmlFor="password">Enter Manager Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={loginPassword}
            onChange={onLoginChange}
            required
          />
          <button type="submit">Login</button>
        </form>
      )}
      {authenticated && (
        <>
          <h2>Registration Form</h2>
          <form onSubmit={onRegistrationSubmit}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={name}
                onChange={onInputChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">
                Username
              </label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="username"
                value={username}
                onChange={onInputChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="role" className="form-label">
                Role
              </label>
              <select
                className="form-control"
                id="role"
                name="role"
                value={role}
                onChange={onInputChange}
                required
              >
                <option value="">Select Role</option>
                <option value="manager">Manager</option>
                <option value="roomattendant">Room Attendant</option>
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={email}
                onChange={onInputChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="dob" className="form-label">
                Date of Birth
              </label>
              <input
                type="date"
                className="form-control"
                id="dob"
                name="dob"
                value={dob}
                onChange={onInputChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={password}
                onChange={onInputChange}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary">
              Register
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </>
      )}
    </div>
  );
}
