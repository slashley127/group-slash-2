import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Registration() {
  let navigate = useNavigate();

  const [user, setUser] = useState({
    
    username: "",
    name: "",
    email: "",
    dob: "",
    password: "",
    role: "",
  });

  const {username, name, email, dob, password, role } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/user", user);
    navigate("/");
  };
  return (
    <div className="container mt-5">
      <h2>Registration Form</h2>
      <form onSubmit={onSubmit}>
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
    </div>
  );
}
