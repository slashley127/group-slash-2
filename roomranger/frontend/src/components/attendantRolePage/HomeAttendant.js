import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

export default function HomeAttendant() {
    const [roomsAssigned, setRoomsAssigned] = useState([]);


    useEffect(() => {
        loadRooms();
    }, []) // Empty dependency array means the effect runs once after the initial render

    //Fetch rooms and typeName from backend
    const loadRooms = async () => {
        const roomsResponse = await axios.get("http://localhost:8080/rooms")
        setRoomsAssigned(roomsResponse.data);
    }

    return (
        <div className="container">
            <div className="py-4">
                <table className="table boder shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Room Number</th>
                            <th scope="col">Guest</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            roomsAssigned.map((room, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{room.roomNumber}</td>
                                    {/* types is an object after passing from spring boot */}
                                    <td>Guest Name</td>
                                    <td>Status chosen</td>
                                    <td>
                                        <Link className='btn btn-outline-primary mx-2' to={`/rooms/editroom/${room.id}`}>Edit Status</Link>
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


