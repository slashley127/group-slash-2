import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function HomeManager() {

       const [assignedRooms, setAssignedRooms] = useState([])

       useEffect(()=>{
           loadAssignedRooms();
       }, []);

       const loadAssignedRooms=async()=>{
           const result=await axios.get("http://localhost:8080/assignedrooms")
           setAssignedRooms(result.data);
       }


return (
       <div className='container'>
           <div className='py=4'>
               <table className='table border shadow'>
                   <thead>
                       <tr>
                           <th scope="col">#</th>
                           <th scope="col">Room Number</th>
                           <th scope="col">Room Attendant</th>
                           <th scope="col">Guest</th>
                           <th scope="col">Number of Guests</th>
                           <th scope="col">CheckIn</th>
                           <th scope="col">CheckOut</th>
                           <th scope="col">Cleaning Task</th>
                           <th scope="col">Notes</th>
                           <th scope="col">Status</th>
                           <th scope="col">Action</th>
                       </tr>
                   </thead>
                   <tbody>
                       {assignedRooms.map((assignedRoom, index)=>(
                             <tr>
                           <th scope="row" key={index}>{index+1}</th>
                           <td>{assignedRoom.room.roomNumber}</td>
                           <td>{assignedRoom.roomAttendant}</td>
                           <td>{assignedRoom.guest}</td>
                           <td>{assignedRoom.numberOfGuests}</td>
                           <td>{assignedRoom.checkIn}</td>
                           <td>{assignedRoom.checkOut}</td>
                           <td>{assignedRoom.task}</td>
                           <td>{assignedRoom.notes}</td>
                           <td>{assignedRoom.status}</td>
                           <td>
                               <button className='btn btn-primary mx-2'>View</button>
                               <button className='btn btn-outline-primary mx-2'>Edit</button>
                               <button className='btn btn-danger mx-2'>Cancel</button>
                           </td>
                       </tr>
                       ))}
                   </tbody>
               </table>


           </div>

       </div>
   )
}
