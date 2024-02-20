import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import AttendantListComponent from "./AttendantListComponent";

export default function Update() {

  let navigate = useNavigate();

  const [attendants, setAttendants] = useState({
    firstName: "",
    username: "",
    email: "",
    phoneNumber: "",
    lastName: "",
    password: "",
    notes: "",
    pronoun: '',
    workingDays: []
  });
  // const [attendantError, setAttendantError] = useState("");


  const { firstName, lastName, email, phoneNumber, pronoun, username, password, notes, workingDays } = attendants;

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    if (type === 'checkbox') {
      // Update state for multi-checkbox inputs
      setAttendants(prevFormData => ({
        ...prevFormData,
        workingDays: checked
          ? [...prevFormData.workingDays, value]
          : prevFormData.workingDays.filter(day => day !== value),
      }));
    } else {
      // Update state for other input types
      setAttendants({
        ...attendants,
        [name]: type === 'radio' ? value : e.target.value,
      });
    }
  };
  const { id } = useParams();

  useEffect(() => {
    loadAttendants();
  },
    []
  );

  const jwt = localStorage.getItem('jwt'); // Retrieve the JWT token from local storage

  const authAxios = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      Authorization: `Bearer ${jwt}`
    }
  });

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await authAxios.put(`/roomAttendant/update/${id}`, attendants);
      navigate("/landing/attendants")
    } catch (error) {
      if (error.response && error.response.status === 403) {
        // 403 error - Unauthorized, navigate to login page
        navigate('/login');
      } else {
        console.error("Error updating attendant:", error);
      }
      // Handle error (e.g., by showing a message to the user)
    }
  };


  const loadAttendants = async (e) => {
    try {
      const result = await authAxios.get(`/roomAttendant/update/${id}`);
      setAttendants(result.data)
    } catch (error) {
      if (error.response && error.response.status === 403) {
        // 403 error - Unauthorized, navigate to login page
        navigate('/landing');
      } else {
        console.error("Error loading attendant:", error);
      }
      // Handle error (e.g., by showing a message to the user)
    }
  };

  return (
    <div className="add">
      <div className="container">
        <div className="row">
          <div className="col-md-20 offset-md-  rounded p-10 mt-2 shadow">
            <h2 className="text-center m-2">Update</h2>


            <form onSubmit={(e) => onSubmit(e)}>
              <div >

                <label htmlFor="Pronoun" className="form-label "><h5>Pronoun:  </h5></label>
                <input type="radio" id="he/him" name="pronoun" value={attendants.pronoun} onChange={(e) => handleChange(e)} />He/Him
                <input type="radio" id="she/her" name="pronoun" value={attendants.pronoun} onChange={(e) => handleChange(e)} />She/Her
                <input type="radio" id="they/them" name="pronoun" value={attendants.pronoun} onChange={(e) => handleChange(e)} />They/Them
              </div>
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label htmlFor="Name" className="form-label ">
                    <h6>FIRST NAME</h6>
                  </label>
                  <input
                    type={"text"}
                    className="form-control"

                    name="firstName"
                    defaultValue={firstName}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
                <div className="col-md-6 mb-3">
                  <label htmlFor="Name" className="form-label">
                    <h6>LAST NAME</h6>
                  </label>
                  <input
                    type={"text"}
                    className="form-control"

                    name="lastName"
                    defaultValue={lastName}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
              </div>
              <div className="row mb-3">

                <div className="col-md-6">
                  <label htmlFor="Email" className="form-label">
                    <h6> EMAIL</h6>
                  </label>
                  <input
                    type={"text"}
                    className="form-control"

                    name="email"
                    defaultValue={email}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
                <div className="col-md-4">
                  <label htmlFor="PhoneNumber" className="form-label">
                    <h6>PHONE NUMBER</h6>
                  </label>
                  <input
                    type={"tel"}
                    className="form-control"

                    name="phoneNumber"
                    defaultValue={phoneNumber}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
              </div>

              <div className="row mb-3">
                <div className="col-md-6">
                  <label htmlFor="Username" className="form-label">
                    <h6>USERNAME</h6>
                  </label>
                  <input
                    type={"text"}
                    className="form-control"

                    name="username"
                    defaultValue={username}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
                <div className="col-md-6">
                  <label htmlFor="Password" className="form-label">
                    <h6>PASSWORD</h6>
                  </label>
                  <input
                    type={"password"}
                    className="form-control"

                    name="password"
                    defaultValue={password}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
              </div>
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label htmlFor="Password" className="form-label">
                    <h6>NEW PASSWORD</h6>
                  </label>
                  <input
                    type={"password"}
                    className="form-control"

                    name="password"
                    // value={password}
                    onChange={(e) => handleChange(e)}
                  ></input>
                </div>
                <div className="col-md-6 mb-4">
                  <label htmlFor="notes" className="form-label ">
                    {/* NOTES */}
                  </label>
                  <textarea class="form-control h-100" defaultValue={notes} id="notes" name="notes" placeholder="Notes" onChange={(e) => handleChange(e)}></textarea>
                </div>
              </div>
              <div className="container" >
                <label htmlFor="workingDays" ><h5>Working Days</h5></label>
                {['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'].map((day) => (
                  <div className="form-check" key={day}>
                    <input
                      type="checkbox"
                      className="checkbox-inline"
                      id={day}
                      name="workingDays"
                      value={day}
                      checked={attendants.workingDays.includes(day)}
                      onChange={(e) => handleChange(e)}
                    />
                    <label className="form-check-label" htmlFor={day}>{day}</label>
                  </div>
                ))}
              </div>

              <div >
                <button type='submit' className='btn btn-outline-success align-center my-3'>Submit</button>
                <Link className='btn btn-outline-danger mx-2 align-center my-4' to='/landing/attendants'>Cancel</Link>
                <button type="reset" className='btn btn-outline-primary align-center my-6'> Reset</button>
              </div>
            </form>
          </div>
          {/* </div> */}
        </div>
      </div>
    </div>
  );
}


