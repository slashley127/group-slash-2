import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams} from 'react-router-dom';

export default function HomeRoom() {
  const [rooms, setRooms] = useState([]);
  const [types, setTypes] = useState({});
  // const {id} = useParams();

  useEffect(() => {
    loadRooms();
  }, []) // Empty dependency array means the effect runs once after the initial render

  //Fetch rooms and typeName from backend
  const loadRooms = async () => {
    const typesResponse = await axios.get('http://localhost:8080/rooms/types');
    setTypes(typesResponse.data);
    // console.log(typesResponse.data);
    const roomsResponse = await axios.get("http://localhost:8080/rooms")
    // console.log("******" ,roomsResponse.data)
    setRooms(roomsResponse.data);
  }

  const deletRoom = async(id) =>{
   
    const response = await axios.delete(`http://localhost:8080/rooms/room/${id}`);
    alert (response.data); // alert(`Room with number '' has been deleted successfully!`)
    loadRooms();
  }

  return (
    <div className="container">
      <div className="py-4">
        {/* Here to give to link to addRoom */}
        <Link className='btn btn-primary' to='/landing/rooms/addroom'>Add Room</Link>
        <table className=" table table-striped shadow">
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
            {
              rooms.map((room, index) => (
                <tr>
                  <th scope="row" key={index}>{index + 1}</th>
                  <td>{room.roomNumber}</td>
                  {/* types is an object after passing from spring boot */}
                  <td>{types[room.roomType]}</td>
                  <td>{room.available ? "Yes" : "No"}</td>
                  <td>
                    <Link className='btn btn-outline-success mx-2' to={`/landing/rooms/viewroom/${room.id}`}>View</Link>
                    <Link className='btn btn-outline-primary mx-2' to={`/landing/rooms/editroom/${room.id}`}>Edit</Link>
                    <button className='btn btn-outline-danger mx-2' onClick={()=> deletRoom(room.id)}>Delete</button>
                  </td>
                </tr>
              ))
            }
          </tbody>
        </table>
      </div>
    </div>
  )
}
