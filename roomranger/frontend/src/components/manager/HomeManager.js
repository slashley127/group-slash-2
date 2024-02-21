import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { format } from 'date-fns';


export default function HomeManager() {

    const [assignedRooms, setAssignedRooms] = useState([])
    const [statuses, setStatuses] = useState({});
    const [tasks, setTasks] = useState({});
    const [roomAttendantFilter, setRoomAttendantFilter] = useState('');
    const [roomNumberFilter, setRoomNumberFilter] = useState('');
    const [statusFilter, setStatusFilter] = useState('');


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

const roomAttendantFirstNames = [...new Set(assignedRooms.map(room => room.roomAttendant.firstName))];

const filteredAssignedRooms = assignedRooms.filter(
    assignedRoom => assignedRoom.roomAttendant.firstName.includes(roomAttendantFilter)
        && assignedRoom.room.roomNumber.includes(roomNumberFilter)
        && assignedRoom.status.includes(statusFilter)
);
    const statusColors = {
        'NOT_STARTED': 'red',
        'IN_PROGRESS': 'blue',
        'SERVICE_REFUSED': 'black',
        'READY': 'green',
        'INSPECTED': 'magenta'
    };

return (
    <div className='container'>
        <div className='row mt-3'>
            <div className='col'>
                <Link className='btn btn-primary' to='/landing/assignroomform'>Assign Room</Link>
                <Link className='btn btn-primary' to='/landing/translator'>Translator</Link>
            </div>
            <div className='col'>
                <div className='form-group'>
                    <label htmlFor="roomNumberFilter">Filter by room number:</label>
                    <input
                        id='roomNumberFilter'
                        type='text'
                        className='form-control custom-input'
                        value={roomNumberFilter}
                        onChange={e => setRoomNumberFilter(e.target.value)}
                    />
                </div>
            </div>
            <div className='col'>
                <div className='form-group'>
                    <label htmlFor="roomAttendantFilter">Filter by room attendant:</label>
                    <select
                        id='roomAttendantFilter'
                        className='form-control custom-input'
                        value={roomAttendantFilter}
                        onChange={e => setRoomAttendantFilter(e.target.value)}
                    >
                        <option value="">All</option>
                        {roomAttendantFirstNames.map((name, index) => (
                            <option key={index} value={name}>{name}</option>
                        ))}
                    </select>
                </div>
            </div>
            <div className='col'>
                <div className='form-group'>
                    <label htmlFor="statusFilter">Filter by status:</label>
                    <select
                        id='statusFilter'
                        className='form-control custom-input'
                        value={statusFilter}
                        onChange={e => setStatusFilter(e.target.value)}
                    >
                        <option value="">All</option>
                        <option value="NOT_STARTED">Not Started</option>
                        <option value="IN_PROGRESS">In Progress</option>
                        <option value="SERVICE_REFUSED">Service Refused</option>
                        <option value="READY">Ready</option>
                        <option value="INSPECTED">Inspected</option>
                    </select>
                </div>
            </div>
        </div>
        <table className='table border shadow'>
            <thead style={{ textAlign: 'left' }}>
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
            <tbody style={{ textAlign: 'left' }}>
                {filteredAssignedRooms.map((assignedRoom, index) => (
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
                            {/* <Link className='btn btn-primary mx-2' to={`assignedrooms/viewassignedroom/${assignedRoom.id}`}>View</Link> */}
                            <Link className='btn btn-outline-primary mx-2' to={`/landing/editassignedroom/${assignedRoom.id}`}>Edit</Link>
                            {/* <Link className='btn btn-danger mx-2' onClick={()=> deleteRoom(assignedRoom.id)}>Delete</Link> */}
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div >
)
}
