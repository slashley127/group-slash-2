import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useParams, useNavigate } from 'react-router-dom';

export default function ViewRooms() {
    const [room, setRoom] = useState({
        roomNumber: "",
        roomType: "",
        available: false
    });
    const { id } = useParams();
    const [type, setType] = useState([]);
    const navigate = useNavigate();


    useEffect(() => {
        fetchType();
        loadRoom();
    })

    const jwt = localStorage.getItem('jwt');

    const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
            Authorization: `Bearer ${jwt}`
        }
    });

    const fetchType = async () => {
        try {
            const typeResponse = await authAxios.get(`/rooms/type/${id}`);
            setType(typeResponse.data);
        } catch (error) {
            if (error.response && error.response.status === 403) {
                // 403 error - Unauthorized, navigate to login page
                navigate('/login');
            } else {
                // Handle other errors
                console.error('Error:', error);
            }
        }

    };
    const loadRoom = async () => {
        try {
            const result = await authAxios.get(`/rooms/room/${id}`);
            setRoom(result.data)
        } catch (error) {
            if (error.response && error.response.status === 403) {
                // 403 error - Unauthorized, navigate to login page
                navigate('/login');
            } else {
                // Handle other errors
                console.error('Error:', error);
            }
        }
    }
    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Room Details</h2>
                    <div className='card'>
                        <div className='card-header'>
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
                    <Link className='btn btn-primary my-2' to={'/landing/rooms'}>Back to Home</Link>
                </div>
            </div>
        </div>
    )
}
