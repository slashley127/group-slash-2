import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AttendantListComponent from "./AttendantListComponent";
import './Attendant.css';

export default function AddAttendant() {
  
  let navigate=useNavigate();

  const [attendant, setAttendant] = useState({
    firstName: "",
    username: "",
    email: "",
    phoneNumber:"",
    lastName:"",
    password:"",
    notes:""
});
  const [attendantError, setAttendantError] = useState("");

  const { id,firstName, lastName, email, phoneNumber, pronoun, username, password,notes } = attendant;

  const onInputChange = (e) => {
    setAttendant({ ...attendant, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try{
    await axios.post("http://localhost:8080/roomAttendant/add", attendant);
    navigate("/")}
    catch (error) {
      setAttendantError(error.response.data.roomAttendant); 
    }
  };

  return (
    <div className="add">
    <div className="container">
      <div className="row">
        <div className="col-md-20 offset-md-  rounded p-10 mt-2 shadow">
          <h2 className="text-center m-2">Add New Attendant</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="row">
            <div className="col-md-6 mb-3">
              <label htmlFor="Name" className="form-label">
                {/* FIRST NAME */}
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="FirstName"
                name="firstName"
                value={firstName}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            <div className="col-md-6 mb-3">
              <label htmlFor="Name" className="form-label">
                {/* LAST NAME */}
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="LastName"
                name="lastName"
                value={lastName}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            </div>
            
            <div className="row">
          {/* <div>
            <label htmlFor="Pronoun" className="form-label col-md-1"></label>
            <select name="pronoun" value={pronoun} onChange={(e) => onInputChange(e)} >
                <option value="Choose" >Pronoun</option>
                <option value="He/Him" >He/Him</option>
                <option value="She/Her" >She/Her</option>
                <option value="They/Them" >They/Them</option>
            </select> */}
            {/* </div> */}
            <div className="col-md-6 mb-3">
              <label htmlFor="Email" className="form-label">
                {/* E-mail */}
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="E-mail"
                name="email"
                value={email}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            <div className="col-md-4">
              <label htmlFor="PhoneNumber" className="form-label">
                {/* PHONE NUMBER */}
              </label>
              <input
                type={"tel"}
                className="form-control"
                placeholder="Phone Number"
                name="phoneNumber"
                value={phoneNumber}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            </div>
         


          <div className="row">
          <div className=" col-md-6 mb-3">
              <label htmlFor="Username" className="form-label">
                {/* Username */}
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Username"
                name="username"
                value= {username}
                onChange={(e) => onInputChange(e)}
              ></input>
          </div>
          <div className="col-md-6">
              <label htmlFor="Password" className="form-label">
                {/* Password */}
              </label>
              <input
                type={"password"}
                className="form-control"
                placeholder="Password"
                name="password"
                //value={password}
                onChange={(e) => onInputChange(e)}
              ></input>
          </div>
          </div>
          <div className="row">
          <div className="col-md-6 mb-3">
              <label htmlFor="Password" className="form-label">
                {/* Confirm Password */}
              </label>
              <input
                type={"password"}
                className="form-control"
                placeholder="Verify Password"
                name="password"
                // value={password}
                onChange={(e) => onInputChange(e)}
              ></input>
          </div>
          <div class="form-group align-center">
              <label for="notes" className="col-md-9 mb-3 ">
                {/* NOTES */}
                </label>
              <textarea class="form-control w-50 p-2 " id="notes" rows="3" placeholder="Notes"></textarea>
          </div>
          </div>
          <div>
          <button type='submit' className='btn btn-outline-success align-center my-4'>Submit</button>
          <Link className='btn btn-outline-danger mx-2 align-center my-6' to='/' >Cancel</Link>
          <button type="reset" className='btn btn-outline-primary align-center my-6'> Reset</button>
          </div>
          </form>
        {/* </div> */}
      </div>
    </div>
    </div>
    </div>
  );
}



