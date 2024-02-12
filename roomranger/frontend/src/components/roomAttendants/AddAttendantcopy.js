import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AttendantListComponent from "./AttendantListComponent";
import './Attendant.css';

export default function AddAttendant() {
  
  let navigate=useNavigate();
  const [daysinfo, setDaysInfo] = useState({
    days: [],
    response: [],
});
  const [attendant, setAttendant] = useState({
    firstName: "",
    username: "",
    email: "",
    phoneNumber:"",
    lastName:"",
    password:"",
    notes:"",
    days:"",
    pronoun:''
});
//const { value, checked } = e.target;
// const [days, setDays] = useState([]);
  const [attendantError, setAttendantError] = useState("");
  
  
  const handleChange = (e) => {
    // Destructuring
    const { value, checked } = e.target;
    const { days } = daysinfo;

    console.log(`${value} is ${checked}`);

    if (checked) {
      setDaysInfo({
        days: [...days, value],
          response: [...days, value],
      });
  }

  // Case 2  : The user unchecks the box
  else {
      setDaysInfo({
          days: days.filter(
              (e) => e !== value
          ),
          response: days.filter(
              (e) => e !== value
          ),
      });
  }
};


  const { id,firstName, lastName, email, phoneNumber, pronoun, username, password,notes,workingDays } = attendant;

  const onInputChange = (e) => {
    setAttendant({ ...attendant, [e.target.name]: e.target.value });
  };
//   function handleDaysChange(e) {
//     // if 
//     // (e.target.checked) {
//        setDays({...days, [e.target.value]:e.target.value});
//     } 
//     // else {
//     //    setDays(days.filter((item) => item !== e.target.value));
//     // }
// //  }

//  const fetchDays = async () => {
//   const daysResponse = await axios.get('http://localhost:8080/roomAttendant/workingDays');
//   const daysArray = Object.entries(daysResponse.data);
//   setDays(daysArray);
// };
// useEffect(() => {
//   fetchDays();
// }, [])

  const onSubmit = async (e) => {
    e.preventDefault();
    try{
    await axios.post("http://localhost:8080/roomAttendant/add", attendant);
    navigate("/attendantListComponent")}
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
            <div className="col-md-6 ">
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
            <div className="col-md-6">
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
          <div className="col-md-6  mb-3">
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
          
        
          <div className="col-md-6 mb-3">
              <label for="notes" className="form-label ">{/* NOTES */}</label>
              <input  type={"text"} className="form-control  p-4 " name="notes" placeholder="Notes" onChange={(e) => onInputChange(e)}/>
          </div>
          </div>
          <div >
            
             <label htmlFor="Pronoun" className="form-label">Pronoun:  </label>
             <input type="radio"  id="he/him" name="pronoun" value="He/Him" onChange={onInputChange} />He/Him
             <input type="radio" id="she/her" name="pronoun" value="She/Her" onChange={onInputChange} />She/Her
             <input type="radio" id="they/them" name="pronoun" value="They/Them"onChange={onInputChange} />They/Them

            </div>

           <div >
          <label  className="form-label">Working Days </label>
          <div> 
            <input value = "Monday" name="Working days" type = "checkbox" id="monday" onChange={handleChange} />
            <label htmlFor="monday">Monday</label>  
         </div>
         <div>
            <input value = "Tuesday" name="Working days" type = "checkbox" id="tuesday" onChange={handleChange} />Tuesday
            <label htmlFor="tuesday">Tuesday</label>  
         </div>
         <div>
            <input value = "Wednesday" name="Working days" type = "checkbox" id="wednesday" onChange={handleChange} />Wednesday
            <label htmlFor="wednesday">Wednesday</label>  
          </div>
         <div>
            <input value = "Thursday" name="Working days" type = "checkbox" id="thursday" onChange={handleChange} />Thursday
            <label htmlFor="thursday">Thursday</label>  

         </div>
         <div>
            <input value = "Friday" name="Working days" type = "checkbox" id="friday" onChange={handleChange} />
            <label htmlFor="friday">Friday</label>  

           
         </div>
      </div>
      {/* </div> */}
     <div>
          <button type='submit' className='btn btn-outline-success my-4'>Submit</button>
          <Link className='btn btn-outline-danger mx-2 align-center my-6' to='/attendantListComponent' >Cancel</Link>
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



