import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AttendantListComponent from "./AttendantListComponent";
import './Attendant.css';

export default function AddAttendant() {

  let navigate = useNavigate();

  const [attendant, setAttendant] = useState({
    id: '',
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    pronoun: '',
    username: '',
    password: '',
    notes: '',
    workingDays: []
  });
  const [attendantError, setAttendantError] = useState("");

  const { id,firstName, lastName, email, phoneNumber, pronoun, username, password,notes, workingDays} = attendant;

  // Handle changes to form inputs
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    if (type === 'checkbox') {
      // Update state for multi-checkbox inputs
      setAttendant(prevFormData => ({
        ...prevFormData,
        workingDays: checked
          ? [...prevFormData.workingDays, value]
          : prevFormData.workingDays.filter(day => day !== value),
      }));
    } else {
      // Update state for other input types
      setAttendant({
        ...attendant,
        [name]: type === 'radio' ? value : e.target.value,
      });
    }
  };

  const jwt = localStorage.getItem('jwt'); // Retrieve the JWT token from local storage

const authAxios = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Authorization: `Bearer ${jwt}`
  }
});
  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    try{
    await authAxios.post('/roomAttendant/add', attendant);
    navigate("/landing/attendants")}
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

            <form onSubmit={handleSubmit}>
            <div >
            
            <label htmlFor="Pronoun" className="form-label "><h5>Pronoun:  </h5></label>
             <input type="radio"  id="he/him" name="pronoun" value="He/Him" onChange={handleChange} />He/Him
             <input type="radio" id="she/her" name="pronoun" value="She/Her" onChange={handleChange} />She/Her
             <input type="radio" id="they/them" name="pronoun" value="They/Them"onChange={handleChange} />They/Them
            </div>
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
                    onChange={(e) => handleChange(e)}
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
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
              </div>

              <div className="row">
                


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
                    onChange={(e) => handleChange(e)}
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
                    onChange={(e) => handleChange(e)}
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
                    value={username}
                    onChange={(e) => handleChange(e)}
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
                    onChange={(e) => handleChange(e)}
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
                    onChange={(e) => handleChange(e)}
                  ></input>
             </div>
               
                <div className="col-md-6 mb-4">
                  <label htmlFor="notes" className="form-label ">
                    {/* NOTES */}
                  </label>
                  <textarea class="form-control h-100 " id="notes" name="notes" placeholder="Notes"  onChange={(e) => handleChange(e)}></textarea>
                </div>
                </div >
                <div className="container" >
                  <label  htmlFor="workingDays" className="form-label col-md-1 checkbox-inline"><h5>Working Days</h5></label>
                  {['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'].map((day) => (
                    <div className="form-check" key={day}>
                      <input
                        type="checkbox"
                        className="checkbox-inline"
                        id={day}
                        name="workingDays"
                        value={day}
                        checked={attendant.workingDays.includes(day)}
                        onChange={handleChange}
                      />
                      <label className="form-check-label" htmlFor={day}>{day}</label>
                    </div>
                  ))}
                </div>
              <div>
                <button type='submit' className='btn btn-outline-success align-center my-4'>Submit</button>
                <Link className='btn btn-outline-danger mx-2 align-center my-6' to='/landing/attendants' >Cancel</Link>
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


