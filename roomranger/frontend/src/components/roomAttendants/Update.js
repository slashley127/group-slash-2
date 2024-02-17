import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import AttendantListComponent from "./AttendantListComponent";

export default function Update() {

  let navigate=useNavigate();

  const [attendants, setAttendants] = useState({
    firstName: "",
    username: "",
    email: "",
    phoneNumber:"",
    lastName:"",
    password:"",
    notes:""
});
  // const [attendantError, setAttendantError] = useState("");
 

  const { firstName, lastName, email, phoneNumber, pronoun, username, password,notes } = attendants;

  const onInputChange = (e) => {
    setAttendants({ ...attendants, [e.target.name]: e.target.value });
  };
const{id}=useParams();

useEffect(()=> {
  loadAttendants();},
[]
);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/roomAttendant/update/${id}`, attendants);
    navigate("/")};


    const loadAttendants = async (e) => {
      const result= await axios.get(`http://localhost:8080/roomAttendant/update/${id}`);
       setAttendants(result.data)};
      
      
    
    

  // const loadUser = async () => {
  //   const result = await axios.get(`http://localhost:8080/roomAttendant/update/${id}`);
  //   setAttendant(result.data);
  // };

  return (
    <div className="list">
    <div className="container">
      <div className="row">
        {/* <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow"> */}
          <h2 className="text-center m-4">Edit Attendant</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="row">
              <div className="col-md-6 mb-3">
              <label htmlFor="Name" className="form-label ">
                <h6>FIRST NAME</h6>
              </label>
              <input
                type={"text"}
                className="form-control"
              
                name="firstName"
                value={firstName}
                onChange={(e) => onInputChange(e)}
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
                value={lastName}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            </div>
            <div className="row mb-3">
            {/* <div className="col-md-6"> */}
            {/* <label htmlFor="Pronoun" className="form-label col-md-1">Pronoun:</label> */}
            {/* <select className="col-md-1"name="pronoun">
                <option value="Choose" >Pronoun</option>
                <option value="He/Him" >He/Him</option>
                <option value="She/Her" >She/Her</option>
                <option value="They/Them" >They/Them</option>
            </select> */} 
            {/* </div> */}
            <div className="col-md-6">
              <label htmlFor="Email" className="form-label">
               <h6> EMAIL</h6>
              </label>
              <input
                type={"text"}
                className="form-control"
              
                name="email"
                value={email}
                onChange={(e) => onInputChange(e)}
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
                value={phoneNumber}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            </div>
            
            {/* <div className="form-row" >
              <label for="">Days Working</label>
            <div className="form-check form-check-inline ">
  <input className="form-check-input" type="checkbox" id="sunday" value="Sunday" onChange={(e) => onInputChange(e)} />
  <label className="form-check-label" for="sunday">Sunday</label>
</div>
<div className="form-check form-check-inline">
  <input className="form-check-input" type="checkbox" id="monday" value="Monday" onChange={(e) => onInputChange(e)}/>
  <label className="form-check-label" for="monday">Monday</label>
</div>
<div className="form-check form-check-inline">
  <input className="form-check-input" type="checkbox" id="tuesday" value="Tuesday" onChange={(e) => onInputChange(e)} />
  <label className="form-check-label" for="tuesday">3 Tuesday</label>
</div>
</div> */}
          <div className="row mb-3">
          <div className="col-md-6">
              <label htmlFor="Username" className="form-label">
                <h6>USERNAME</h6>
              </label>
              <input
                type={"text"}
                className="form-control"
              
                name="username"
                value= {username}
                onChange={(e) => onInputChange(e)}
              ></input>
          </div>
          <div className="col-md-6">
              <label htmlFor="Password" className="form-label">
                <h6>NEW PASSWORD</h6>
              </label>
              <input
                type={"password"}
                className="form-control"
                
                name="password"
                //value={password}
                onChange={(e) => onInputChange(e)}
              ></input>
          </div>
          </div>
          <div className="col-md-6 mb-3">
              <label htmlFor="Password" className="form-label">
                <h6>CONFIRM PASSWORD</h6>
              </label>
              <input
                type={"password"}
                className="form-control"
               
                name="password"
                // value={password}
                onChange={(e) => onInputChange(e)}
              ></input>
          </div>
          <div className="form-group mb-3">
              <label for="notes"><h6>NOTES</h6></label>
              <textarea className="form-control" id="notes" rows="3"></textarea>
          </div>
<div className="text-center">
          <button type='submit' className='btn btn-outline-primary align-center my-4'>Submit</button>
         
          <Link className='btn btn-outline-danger mx-2 align-center my-4' to='/'>Cancel</Link>
          </div >
          </form>
        </div>
      {/* </div> */}
    </div>
    </div>
  );
}



