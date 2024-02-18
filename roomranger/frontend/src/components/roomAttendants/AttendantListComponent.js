import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import NavBar from '../NavBar';
import './Attendant.css';
import { useAuth } from '../security/AuthContext';


export default function AttendantListComponent() {

 const [attendants,setAttendants]=useState([]) ;
 const [days, setDays] = useState({});
 //const authContext=useAuth() 
 //const username=authContext.username

    const { id } = useParams();
   
    useEffect(()=> {
       loadAttendants();}, 
    []
  );
    const loadAttendants=async()=>{
      const attendantsResponse = await axios.get("http://localhost:8080/roomAttendant");
      setAttendants(attendantsResponse.data)};
    

    const deleteAttendant = async(id) =>{
      await axios.get(`http://localhost:8080/roomAttendant/delete/${id}`);
      loadAttendants();
    }
  return (
    <div className='list'> 
    <div className='container'>
      <div className='py-8'>
      <h2 className='text-center'> Room Attendants List</h2></div>
      <form>

      {/* <div className='py-2'>
      <h2 className='text-center'> Room Attendants</h2> */}
      <div className='py-2 '></div>
      <table className='table border table-bordered table-striped shadow '>
      <thead>
      <tr>
      <th scope='col' className='text-center'>#</th>
      <th scope='col' className='text-center'> EMP ID </th>
      <th scope='col' className='col-md-2 text-center'> NAME </th>
      <th scope='col' className='col-md-4 text-center'> EMAIL </th>
      <th scope='col' className='col-md-2 text-center'> SCHEDULE </th>
      <th scope='col' className=' col-md-3 text-center'> ACTION </th>
      </tr>
      </thead>
      <tbody>
            {
            attendants.map((roomAttendant, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{roomAttendant.id}</td>
                <td>{roomAttendant.firstName}{' '}{roomAttendant.lastName}</td>
                <td>{roomAttendant.email}</td>
                {/* <td >{roomAttendant.notes}</td> */}
                <td>{roomAttendant.workingDays}</td>
                <td align='center'><Link className="btn btn-outline-success mx-2" to={`/landing/attendants/profile/${roomAttendant.id}`}>View</Link>
        <Link className="btn btn-outline-primary mx-2 "to={`/landing/attendants/update/${roomAttendant.id}`} >EDIT</Link>        
        <button className="btn btn-outline-danger mx-2" onClick={()=> deleteAttendant(roomAttendant.id)}>Delete</button>
                </td>
              </tr>
         ))}
            </tbody>
      </table>
      <Link className="btn btn-outline-success my-2 my-sm-0" to='/landing/attendants/add'>Add New Attendant</Link>
      </form>
      </div>
      
      </div>
  );
}

