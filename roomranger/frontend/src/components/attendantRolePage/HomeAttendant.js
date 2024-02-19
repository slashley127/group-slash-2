import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const HomeAttendant = () => {
  const [assignedRooms, setAssignedRooms] = useState([]);
  const [statuses, setStatuses] = useState({});
  const [tasks, setTasks] = useState({});

  const loadAssignedRooms = async () => {
    const statusesResponse = await axios.get('http://localhost:8080/assignedrooms/statuses');
    setStatuses(statusesResponse.data);
    console.log(statusesResponse.data)
    const tasksResponse = await axios.get('http://localhost:8080/assignedrooms/tasks');
    setTasks(tasksResponse.data);
    const response = await axios.get("http://localhost:8080/assignedrooms")
    setAssignedRooms(response.data);
  }

  useEffect(() => {
    loadAssignedRooms();
  }, [])
  
  const getStatusColor = (status) => {
    switch (status) {
      case 'NOT_STARTED':
        return 'red';
      case 'IN_PROGRESS':
        return 'lightblue';
      case 'SERVICE_REFUSED':
        return 'orange';
      case 'READY':
        return 'lightgreen';
      case 'INSPECTED':
        return 'green';
    }
  }
  let statusOrder = ['NOT_STARTED', 'IN_PROGRESS', 'READY', 'SERVICE_REFUSED', 'INSPECTED'];

  return (
    <div className='container'>
      <div className='py=4'>
        <table className='table table-striped  shadow'>
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Room Number</th>
              <th scope="col">Guest</th>
              <th scope="col">CheckIn</th>
              <th scope="col">CheckOut</th>
              <th scope="col">Cleaning Task</th>
              <th scope="col">Status</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {assignedRooms.sort((a, b) => statusOrder.indexOf(a.status) - statusOrder.indexOf(b.status)).map((assignedRoom, index) => (
              <tr>
                <th scope="row" key={index}>{index + 1}</th>
                <td>
                  <Link to={`/landing/roomattendant/assignedroom/${assignedRoom.id}`}>
                  {assignedRoom.room.roomNumber}
                  </Link>
                  </td>
                <td>{assignedRoom.guest}</td>
                <td>{assignedRoom.checkIn}</td>
                <td>{assignedRoom.checkOut}</td>
                <td>{tasks[assignedRoom.task]}</td>
                <td style={{ backgroundColor: getStatusColor(assignedRoom.status) }}>{statuses[assignedRoom.status]}</td>
                <td>
                  {/* {assignedRoom.status !== 'INSPECTED' && (
                    <Link className='btn btn-outline-primary' to={`/landing/roomattendant/assignedroom/${assignedRoom.id}`}>Update</Link>
                  )} */}
                  <Link className='btn btn-outline-primary' to={`/landing/roomattendant/assignedroom/${assignedRoom.id}`}>Update</Link >
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default HomeAttendant
