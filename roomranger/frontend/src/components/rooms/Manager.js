import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import ManagerNavBar from "./ManagerNavBar"; // Corrected import statement

export default function Manager() {
  const [rooms, setRooms] = useState([]);
  const [types, setTypes] = useState({});
  const { id } = useParams();

  useEffect(() => {
    loadRooms();
  }, []) // Empty dependency array means the effect runs once after the initial render

  // Fetch rooms and typeName from backend
  const loadRooms = async () => {
    const typesResponse = await axios.get('http://localhost:8080/rooms/types');
    setTypes(typesResponse.data);
    const roomsResponse = await axios.get("http://localhost:8080/rooms");
    setRooms(roomsResponse.data);
  }

  const deletRoom = async (id) => {
    await axios.delete(`http://localhost:8080/rooms/room/${id}`);
    loadRooms();
  }

  return (
    <div className="container">
      <ManagerNavBar />
      <div className="py-4">
        {/* Here to give to link to addRoom */}
        <Link className='btn btn-primary' to='/rooms/addroom'>Add Room</Link>
        <table className="table boder shadow">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Room Number</th>
              <th scope="col">Room Type</th>
              <th scope="col">Is Available</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {rooms.map((room, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{room.roomNumber}</td>
                {/* types is an object after passing from spring boot */}
                <td>{types[room.roomType]}</td>
                <td>{room.available ? "Yes" : "No"}</td>
                <td>
                  <Link className='btn btn-primary mx-2' to={`/rooms/viewroom/${room.id}`}>View</Link>
                  <Link className='btn btn-outline-primary mx-2' to={`/rooms/editroom/${room.id}`}>Edit</Link>
                  <button className='btn btn-danger mx-2' onClick={() => deletRoom(room.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
