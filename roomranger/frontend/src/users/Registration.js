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
  const isUsernameUnique = async (username) => {
    try {
      const response = await axios.get(`http://localhost:8080/user/exists/${username}`);
      return !response.data; // Assuming the response is a boolean indicating uniqueness
    } catch (error) {
      console.error("Error checking username uniqueness:", error);
      return false; // Return false in case of an error
    }
  };
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
    // Calculate age based on the entered date of birth
  const currentDate = new Date();
  const enteredDob = new Date(dob);
  const userAge = currentDate.getFullYear() - enteredDob.getFullYear();

  if (userAge < 15) {
    // Display an error message or take appropriate action
    alert("Sorry, you must be at least 15 years old to register.");
    return;
  }
  // Call the isUsernameUnique function with the actual username
  
  

  // Check for password complexity
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

  if (!passwordRegex.test(password)) {
    // Display an error message for password complexity
    alert("Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 8 characters long.");
    return;
  }
  // Check if the username is unique
  const isUnique = await isUsernameUnique(username);

  if (!isUnique) {
    // Display an error message for non-unique username
    alert("Username is already taken. Please choose a different one.");
    return;
  }

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
