import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";
import AttendantListComponent from "./AttendantListComponent";

export default function Profile() {

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

  const {firstName, lastName, email, phoneNumber, pronoun, username, password,notes } = attendant;
  const {id}= useParams();

  useEffect(()=> {
    loadAttendant();}
)

  const loadAttendant = async () => {
    const result = await axios.get(`http://localhost:8080/roomAttendant/profile/${id}`);
    setAttendant(result.data)};
  

  return (
//     <div className="container">
//     <div className="d-flex align-items-center justify-content-center">
//       <div className="col-md-10 offset-md-1  rounded p-4 mt-2 shadow">
//         <h2 className="text-center m-2">Add New Attendant</h2>
//            <div className="col-md-6 mb-3 align-center ">
//               <label htmlFor="Name" className="form-label"> FIRST NAME
//               </label>
//               <input
//                 type={"text"}
//                 className="text-center"
//                 name="firstName"
//                 value={firstName}
//               ></input>
//            </div>
//             <div className="col-md-6 mb-3">
//               <label htmlFor="Name" className="form-label">
//                 LAST NAME
//               </label>
//               <input
//                 type={"text"}
//                 className="form-control"
//                 name="lastName"
//                 value={lastName}
              
//               ></input>
//             </div>
//             </div>

//         </div>
//         </div>
        




//   )
<div className="list">
<div className='container'>
<div className='row'>
    <div className='col-md-6 offset-md-3 border rounded p-4 mt-2  shadow'>
        <h3 className='text-center m-10 '>
            {attendant.firstName} {lastName}</h3>
        <div className='card'>
            <div className='card-header'>
                <ul className='list-group list-group-flush mt-4'>
                <li className=' list-group-item text-center h-75'>
                        <b> ID: </b>
                        {attendant.id}
                    </li>
                    <li className='list-group-item text-center mt-3'>
                        <b> First Name: </b>
                        {attendant.firstName}
                    </li>
                    <li className='list-group-item text-center mt-3'>
                        <b> Last Name: </b>
                        {attendant.lastName}
                    </li>
                    <li className='list-group-item text-center mt-3'>
                        <b> Email: </b>
                        {attendant.email}
                    </li>
                    <li className='list-group-item text-center mt-3'>
                        <b> Phone Number: </b>
                        {attendant.phoneNumber}
                    </li>
                    {/* <li className='list-group-item text-center'>
                        <b> Pronoun: </b>
                        {attendant.pronoun}
                    </li> */}
                    <li className='list-group-item text-center mt-3'>
                        <b> Username </b>
                        {attendant.username}
                    </li>


                </ul>
            </div>
        </div>
        <Link className='btn btn-primary my-2' to={'/landing/attendants'}>Back to Home</Link>
    </div>
</div>
</div>
</div>

)

}
