import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { format } from 'date-fns';

export default function HomeManager() {
    const [assignedRooms, setAssignedRooms] = useState([]);
    const [statuses, setStatuses] = useState({});
    const [tasks, setTasks] = useState({});
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        loadAssignedRooms();
    }, []);

    // Setting JWT in the HTTP Request Header under Authorization field
    const jwt = localStorage.getItem('jwt');

    const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
            Authorization: `Bearer ${jwt}`
        }
    });

    const loadAssignedRooms = async () => {
        try {
            const statusesResponse = await authAxios.get('/assignedrooms/statuses');
            setStatuses(statusesResponse.data);
            const tasksResponse = await authAxios.get('/assignedrooms/tasks');
            setTasks(tasksResponse.data);
            const result = await authAxios.get("/assignedrooms");
            setAssignedRooms(result.data);
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

    const statusColors = {
        'NOT_STARTED': 'red',
        'IN_PROGRESS': 'blue',
        'SERVICE_REFUSED': 'black',
        'READY': 'green',
        'INSPECTED': 'magenta'
    };

    return (
        <div className='container'>
            <div className='py=4'>
                <Link className='btn btn-primary' to='/landing/assignroomform'>Assign Room</Link>
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
                        {assignedRooms.map((assignedRoom, index) => (
                            <tr key={"assignedroom" + index}>
                                <th scope="row" key={index}>{index + 1}</th>
                                <td>{assignedRoom.room.roomNumber}</td>
                                <td>{assignedRoom.roomAttendant.firstName}</td>
                                <td>{assignedRoom.guest}</td>
                                <td>{assignedRoom.numberOfGuests}</td>
                                <td>{format(new Date(assignedRoom.checkIn), 'MMM dd, yyyy')}</td>
                                <td>{format(new Date(assignedRoom.checkOut), 'MMM dd, yyyy')}</td>
                                <td>{tasks[assignedRoom.task]}</td>
                                <td>{assignedRoom.note}</td>
                                <td style={{ color: statusColors[assignedRoom.status] }}>{statuses[assignedRoom.status]}</td>
                                <td>
                                    <Link className='btn btn-outline-primary mx-2' to={`/landing/editassignedroom/${assignedRoom.id}`}>Edit</Link>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
