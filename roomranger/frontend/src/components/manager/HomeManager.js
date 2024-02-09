import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

export default function HomeManager() {

        const [assignedRooms, setAssignedRooms] = useState([])
        const [statuses, setStatuses] = useState({});
        const [tasks, setTasks] =  useState({});
        const {id} = useParams();


       useEffect(()=>{
           loadAssignedRooms();
       }, []);

       const loadAssignedRooms=async()=>{
            const statusesResponse = await axios.get('http://localhost:8080/assignedrooms/statuses');
            setStatuses(statusesResponse.data);
            const tasksResponse = await axios.get('http://localhost:8080/assignedrooms/tasks');
            setTasks(tasksResponse.data);
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
                           <td>{tasks[assignedRoom.task]}</td>
                           <td>{assignedRoom.notes}</td>
                           <td>{statuses[assignedRoom.status]}</td>
                           <td>
                           {/* <Link className='btn btn-primary mx-2' to={`assignedrooms/viewassignedroom/${assignedRoom.id}`}>View</Link>
                            <Link className='btn btn-outline-primary mx-2' to={`assignedrooms/editassignedroom/${assignedRoom.id}`}>Edit</Link>
                             <button className='btn btn-danger mx-2' onClick={()=> deleteRoom(assignedRoom.id)}>Delete</button> */}
                           </td>

                           {/* to do tomorrow: link buttons to pages and implement logic */}
                       </tr>
                       ))}
                   </tbody>
               </table>


           </div>

       </div>
   )
}
