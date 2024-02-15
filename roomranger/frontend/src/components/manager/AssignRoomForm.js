import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

export default function AssignRoom() {
  let navigate = useNavigate()
  const [rooms, setRooms] = useState([])
  const [roomAttendants, setRoomAttendants] = useState([])
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
    note: "",
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
    note,
    status
  } = assignedRoom

  useEffect(() => {
    fetchTasks();
    fetchStatuses();
    fetchRooms();
    fetchRoomAttendants();
  }, [])

  useEffect(() => {
    console.log("Assigned Room: ", assignedRoom);
  }, [assignedRoom]);


  const onInputChange = (e) => {
    const { name, value } = e.target;
    // if (name === "roomAttendant") {
    //   setAssignedRoom({
    //     ...assignedRoom,
    //     roomAttendant: {
    //       id: JSON.parse(value).id,
    //       firstName: JSON.parse(value).firstName,
    //       lastName: JSON.parse(value).lastName,
    //       username: JSON.parse(value).username,
    //       password: JSON.parse(value).password,
    //       phoneNumber: JSON.parse(value).phoneNumber,
    //       email: JSON.parse(value).email,
    //       notes: JSON.parse(value).notes,
    //     },
    //   });
    //   return;
    // }
    setAssignedRoom({ ...assignedRoom, [name]: value });
  };

  const onRoomNumberChange = (roomNumber) => {
    const room = rooms.filter((room) => room[1].roomNumber === roomNumber)[0][1]
    setAssignedRoom({
      ...assignedRoom,
      room: {
        id: room.id,
        roomNumber: room.roomNumber,
        roomType: room.roomType,
        available: false,
      },
    })
  }


  const onRoomAttendantNameChange = (firstName) => {
    const roomAttendant = roomAttendants.filter((roomAttendant) => roomAttendant[1].firstName === firstName)[0][1]
    setAssignedRoom({
      ...assignedRoom,
      roomAttendant: {
        id: roomAttendant.id,
        firstName: roomAttendant.firstName,
        lastName: roomAttendant.lastName,
        username: roomAttendant.username,
        password: roomAttendant.password,
        phoneNumber: roomAttendant.phoneNumber,
        email: roomAttendant.email,
        notes: roomAttendant.notes,
      },
    })
  }


  const fetchTasks = async () => {
    const tasksResponse = await axios.get('http://localhost:8080/assignedrooms/tasks')
    const tasksArray = Object.entries(tasksResponse.data);
    setTasks(tasksArray)
  }

  const fetchStatuses = async () => {
    const statusesResponse = await axios.get('http://localhost:8080/assignedrooms/statuses')
    const statusesArray = Object.entries(statusesResponse.data);
    setStatuses(statusesArray)
  }

  const fetchRooms = async () => {
    try {
      const roomsResponse = await axios.get("http://localhost:8080/rooms");
      const roomsArray = Object.entries(roomsResponse.data);
      setRooms(roomsArray);
    } catch (error) {
      console.error("Error fetching room numbers", error);
    }
  }

  const fetchRoomAttendants = async () => {
    try {
      const roomAttendantsResponse = await axios.get("http://localhost:8080/roomAttendant");
      const roomAttendantsArray = Object.entries(roomAttendantsResponse.data);
      setRoomAttendants(roomAttendantsArray);
    } catch (error) {
      console.error("Error fetching room attendants", error);
    }
  }

  const onFormSubmit = async (e) => {
    e.preventDefault();
    console.log("Assigned Room:", assignedRoom);
    try {
      const response = await axios.post(
        "http://localhost:8080/assignedrooms/create",
        assignedRoom,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response && response.data) {
        navigate("/assignedrooms");
      } else {
        console.error("Unexpected response format:", response);
      }
    } catch (error) {
      console.error("Error submitting the form:", error);
      console.log("Error response data:", error.response?.data);
      setAssignedRoomError(error.response?.data || "An error occurred");
    }
  };


  return (
    <div className='container'>
      <div className="row">
        <div className='col-md-6 offset-md border rounded p-4 mt-2 shadow'>
          <h2 className='text-center m-4'>Assign Room</h2>
          <form onSubmit={(e) => onFormSubmit(e)}>
            <div className='mb-3'>
              <label htmlFor="RoomNumber" className='form-label'>
                Room Number
              </label>
              <select
                className="form-control"
                name="room"
                value={room.roomNumber}
                onChange={(e) => onRoomNumberChange(e.target.value)}
              >
                <option value="" disabled>
                  Select room number
                </option>
                {rooms.map((room) => (
                  <option
                    key={`roomOption${room[1].id}`}
                    value={room[1].roomNumber}
                  >
                    {room[1].roomNumber}
                  </option>
                ))}
              </select>
            </div>
            <div className='mb-3'>
              <label htmlFor="firstName" className='form-label'>
                Room Attendant
              </label>
              <select
                className="form-control"
                name="roomAttendant"
                value={roomAttendant.firstName}
                onChange={(e) => onRoomAttendantNameChange(e.target.value)}
              >
                <option value="" disabled>
                  Select room attendant
                </option>
                {roomAttendants.map((roomAttendant) => (
                  <option
                    key={`roomAttendantOption${roomAttendant[1].id}`}
                    value={roomAttendant[1].firstName}
                  >
                    {roomAttendant[1].firstName}
                  </option>
                ))}
              </select>
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
              <label htmlFor="note" className='form-label'>
                Notes
              </label>
              <input
                type='text'
                className="form-control"
                placeholder="Notes"
                name="note"
                value={note}
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
            <Link className='btn btn-outline-danger mx-2' to="/manager">Cancel</Link>
          </form>
        </div>
      </div>
    </div>
  );
}
