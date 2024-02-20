import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams, Link, useNavigate } from 'react-router-dom';
import AddCommentForm from '../comments/AddCommentForm'

export default function UpdateStatus() {
    const [user, setUser] = useState(null);
    const [assignedRoom, setAssignedRoom] = useState({
        room: {},
    });
    const [statuses, setStatuses] = useState([]);
    const [task, setTask] = useState([]);
    const { roomNumber } = assignedRoom.room;
    const { id } = useParams();
    let navigate = useNavigate();

    const jwt = localStorage.getItem('jwt');

    const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
          Authorization: `Bearer ${jwt}`
        }
    });

    const loadAssignedRoom = async () => {
        try {
            const response = await authAxios.get(`/assignedrooms/assignedroom/${id}`);
            setAssignedRoom(response.data);
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

    const loadStatuses = async () => {
        try {
            const response = await authAxios.get("/assignedrooms/statuses");
            const statusesArray = Object.entries(response.data);
            setStatuses(statusesArray);
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

    const loadTask = async () => {
        try {
            const response = await authAxios.get(`/assignedrooms/tasks/${id}`);
            setTask(response.data);
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

    useEffect(() => {
        loadAssignedRoom();
        loadStatuses();
        loadTask();
    }, [])

    const onInputChange = (e) => {
        const { name, value } = e.target;
        setAssignedRoom({ ...assignedRoom, [name]: value });
    }

    const onFormSubmit = async (e) => {
        e.preventDefault();
        try {
            await authAxios.put(`/assignedrooms/assignedroom/${id}`, assignedRoom);
            navigate("/landing/roomattendant")
        } catch (error) {
            alert(error.response.data.message);
        }
    }

    return (
        <div className='container'>
            <div className="row">
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Update Status</h2>
                    <form onSubmit={onFormSubmit}>
                        {/* Your form elements */}
                    </form>
                    {user && user.roles.includes('manager') && (
                        <div>
                            {/* Manager-specific content */}
                            <div className='mb-3'>
                                {/* Additional manager-specific fields */}
                            </div>
                        </div>
                    )}
                    <div>
                        <AddCommentForm assignRoomId={id} />
                    </div>
                </div>
            </div>
        </div>
    )
}
