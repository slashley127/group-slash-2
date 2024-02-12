import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

export default function EditAssignedRoom() {
    let navigate = useNavigate()
    const {id} = useParams()
    const [rooms, setRooms] = useState([])
    // const [roomAttendants, setRoomAttendants] = useState([])
    const [tasks, setTasks] = useState([])
    const [statuses, setStatuses] = useState([])
    const [assignedRoom, setAssignedRoom] = useState({
        room: "",
        roomAttendant: "",
        guest: "",
        numberOfGuests: 0,
        checkIn: "",
        checkOut: "",
        task: "",
        notes: "",
        status: ""
    })

    const [assignedRoomError, setAssignedRoomError] = useState("");

    const {
        room,
        roomAttendant,
        guest,
        numberOfGuests,
        checkIn,
        checkOut,
        task,
        notes,
        status
    } = assignedRoom

    useEffect(() => {
        fetchTasks();
        fetchStatuses();
        fetchRooms();
        loadAssignedRoom();
    }, [])


    const onInputChange = (e) => {
        const { name, value } = e.target;
        if (name === "room") {
          setAssignedRoom({
            ...assignedRoom,
            room: {
              id: JSON.parse(value).id,
              roomNumber: JSON.parse(value).roomNumber,
              roomType: JSON.parse(value).roomType,
              available: false,
            },
          });
          return;
        }
        setAssignedRoom({ ...assignedRoom, [name]: value });
      };

    const fetchTasks = async () => {
        const tasksResponse = await axios.get(`http://localhost:8080/assignedrooms/tasks`)
        const tasksArray = Object.entries(tasksResponse.data);
        setTasks(tasksArray)
    }

    const fetchStatuses = async () => {
        const statusesResponse = await axios.get(`http://localhost:8080/assignedrooms/statuses`)
        const statusesArray = Object.entries(statusesResponse.data);
        setStatuses(statusesArray)
    }

    const fetchRooms = async () => {
        try {
          const roomsResponse = await axios.get(`http://localhost:8080/rooms`);
          const roomsArray = Object.entries(roomsResponse.data);
          setRooms(roomsArray);
        } catch (error) {
          console.error("Error fetching room numbers", error);
        }
      };

    // const fetchRoomAttendants = async () => {
    //     const roomAttendantsResponse = await axios.get('http://localhost:8080/manager/roomattendant')
    //     const roomAttendantsArray = Object.entries(roomAttendantsResponse.data);
    //     setRoomAttendants(roomAttendantsArray)
    // }




//REVIEW NEEDED
    const onFormSubmit = async (e) => {
        e.preventDefault();
        await axios.put(
            `http://localhost:8080/assignedrooms/assignedroom/${id}`, assignedRoom);
            navigate("/assignedrooms");
          };

          const loadAssignedRoom = async () => {
            const result = await axios.get(`http://localhost:8080/assignedrooms/assignedroom/${id}`)
            setAssignedRoom(result.data)
          }
//REVIEW NEEDED

    return (
        <div className='container'>
            <div className="row">
                <div className='col-md-6 offset-md border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Edit Assigned Room</h2>
                    <form onSubmit={(e) => onFormSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor="RoomNumber" className='form-label'>
                            Room Number: {assignedRoom.room.roomNumber}
              </label>
            </div>
                        <div className='mb-3'>
                            <label htmlFor="roomAttendant" className='form-label'>
                                Room Attendant
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Choose room attendant"
                                name="roomAttendant"
                                value={roomAttendant}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="guest" className='form-label'>
                                Guest
                            </label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder="Enter guest name"
                                name="guest"
                                value={guest}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="numberOfGuests" className='form-label'>
                                Number of Guests
                            </label>
                            <input
                                type={"number"}
                                className="form-control"
                                placeholder="Enter number of guests"
                                name="numberOfGuests"
                                value={numberOfGuests}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="checkIn" className='form-label'>
                                CheckIn
                            </label>
                            <input
                                type={"date"}
                                className="form-control"
                                placeholder="Enter check-in day"
                                name="checkIn"
                                value={checkIn}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="checkOut" className='form-label'>
                                CheckOut
                            </label>
                            <input
                                type={"date"}
                                className="form-control"
                                placeholder="Enter check-out day"
                                name="checkOut"
                                value={checkOut}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="cleaningTask" className='form-label'>
                                Cleaning task
                            </label>
                            <select
                                name="task"
                                value={task}
                                onChange={(e) => onInputChange(e)}>
                                <option value="" disabled>Select cleaning task</option>
                                {tasks.map(([name, displayName]) => (
                                    <option key={name} value={name}>{displayName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="notes" className='form-label'>
                                Notes
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Notes"
                                name="notes"
                                value={notes}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Status" className='form-label'>
                                Status
                            </label>
                            <select
                                name="status"
                                value={status}
                                onChange={(e) => onInputChange(e)}>
                                <option value="" disabled>Select cleaning status</option>
                                {statuses.map(([name, displayName]) => (
                                    <option key={name} value={name}>{displayName}</option>
                                ))}
                            </select>
                        </div>
                        <button type='submit' className='btn btn-outline-primary'>Submit</button>
                        <Link className='btn btn-outline-danger mx-2' to="/assignedrooms">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    );
                                }