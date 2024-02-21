import React, { useState } from 'react';
import './LoginPage.css';
import { FaCircleUser, FaLock } from "react-icons/fa6";
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../security/AuthContext';

const LoginComponent = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  const authContext = useAuth();

  function handleUsernameChange(e) {
    setUsername(e.target.value);
  }

  function handlePasswordChange(e) {
    setPassword(e.target.value);
  }


  async function handleSubmitLogin(e) {
    e.preventDefault(); // Prevent the form from causing a page reload

    try {
      const response = await fetch('http://localhost:8080/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const data = await response.json(); // Extract the JWT from the response
        authContext.login(data.jwt, 'true');
        setShowSuccessMessage(true);
        setShowErrorMessage(false);
        navigate('/landing'); // Navigate to the landing page or dashboard
      } else {
        authContext.login('', 'false');
        setShowErrorMessage(true);
        setShowSuccessMessage(false);
      }
    } catch (error) {
      console.error('Login error:', error);
      authContext.login('', 'false');
      setShowErrorMessage(true);
      setShowSuccessMessage(false);
    }
  }

  return (
    <div className='design'>
      <div className='wrapper'>
        {showErrorMessage && <div className='errorMessage'>Authentication Failed. Please check your credentials.</div>}
        {showSuccessMessage && <div className='successMessage'>Authenticated Successfully.</div>}
        <form onSubmit={handleSubmitLogin}>
          <h1> Login </h1>
          <div className='input-box'>
            <input type='text' name='username' placeholder='Username' value={username} onChange={handleUsernameChange} required />
            <FaCircleUser className='icon' />
          </div>
          <div className='input-box'>
            <input type='password' name='password' placeholder='Password' onChange={handlePasswordChange} value={password} required />
            <FaLock className='icon' />
          </div>
          {/* <div className='remember-forgot'>
            <label><input type='checkbox' />Remember me</label>
            <a href='#'>Forgot password</a>
          </div> */}
          <button type='submit'>Login</button>
          {/* <div className='register-link'>
            <p>Don't have an account? <a href='#'>Register</a></p>
          </div> */}
        </form>
      </div>
    </div>
  );
}

export default LoginComponent;