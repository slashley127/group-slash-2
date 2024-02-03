import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'

export default function ViewRooms() {
    const [room, setRoom] = useState({
        roomNumber: "",
        roomType: "",
        available: false
    });
    const { id } = useParams();
    const [type, setType] = useState([]);

    useEffect(() => {
        fetchTypes();
        loadRoom();
    })

    const fetchTypes = async () => {
        const typeResponse = await axios.get(`http://localhost:8080/rooms/type/${id}`);
        setType(typeResponse.data);
    };
    const loadRoom = async () => {
        const result = await axios.get(`http://localhost:8080/rooms/room/${id}`);
        setRoom(result.data)
    }
    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Room Details</h2>
                    <div className='card'>
                        <div className='card-header'>
                            Details of Room id : {room.id}
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'>
                                    <b> Room Number: </b>
                                    {room.roomNumber}
                                </li>
                                <li className='list-group-item'>
                                    <b> Room Type: </b>
                                    {type}
                                </li>
                                <li className='list-group-item'>
                                    <b> Room Available: </b>
                                    {room.available ? "Yes" : "No"}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <Link className='btn btn-primary my-2' to={'/rooms'}>Back to Home</Link>
                </div>
            </div>
        </div>
    )
}
