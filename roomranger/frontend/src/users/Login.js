import React, { useState } from 'react'
import './LoginPage.css';
import { FaCircleUser, FaLock   } from "react-icons/fa6";
import { useNavigate } from 'react-router-dom';



const LoginComponent = () => {

const navigate=useNavigate();
    const[username,setUsername]=useState('');
    const[password,setPassword]=useState('');
    const[showSuccessMessage, setShowSuccessMessage]=useState(false);
    const[showErrorMessage, setShowErrorMessage]=useState(false);
   
     function handleUsernameChange(e){
      // console.log(e.target.value);
      setUsername(e.target.value)
    }
    function handlePasswordchange(e){
      // console.log(e.target.value)
    setPassword(e.target.value);
    }

    function handleSubmitLogin(){
      if(username==='samata' && password==='karka'){
      setShowSuccessMessage(true);
      setShowErrorMessage(false)
      navigate('/login/ManagerHomePage')
    
    }else{
      setShowErrorMessage(true)
      setShowSuccessMessage(false)
    }
  }
   
  return(
    
    <div className='design'>
    <div className='wrapper'>
    {showErrorMessage && <div className='errorMessage'> Authenticated Failed. Please check your credentials.</div>}
        {showSuccessMessage && <div className='successMessage'> Authenticated Successfully.</div>}
    <form>
        <h1> Login </h1>
        <div className='input-box'>
          <input type='text' name='username'placeholder='Username' value={username} onChange={handleUsernameChange} required />
          <FaCircleUser className='icon'/>
        </div>
        <div className='input-box'>
          <input type='password' name='password'placeholder='Password' onChange={handlePasswordchange} value={password} required />
          < FaLock className='icon'/> 
        </div>
        <div className='remember-forgot'>
          <label><input type='checkbox'/>Remember me</label>
          <a href='#'>Forgot password</a>
        </div>
        <button type='submit' onClick={handleSubmitLogin} >Login</button>
        <div className='register-link'>
          <p>Don't have an account?  <a href='#'>Register</a></p>
        </div>
      </form>
    </div>
    </div>
  )
}

export default LoginComponent