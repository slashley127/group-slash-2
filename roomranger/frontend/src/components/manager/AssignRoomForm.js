//import axios from 'axios';
//import React, { useEffect, useState } from 'react'
//import { useNavigate } from 'react-router-dom';
//
//export default function AssignRoom() {
//
//let navigate=useNavigate()
//
//      const [roomNumbers, setRoomNumbers] = useState([])
//      const [roomAttendants, setRoomAttendants] = useState([])
//      const [tasks, setTasks] = useState([])
//      const [statuses, setStatuses] = useState([])
//
//      const [assignedRoom, setAssignedRoom]=useState({
//        room:"",
//        roomAttendant:"",
//        guest:"",
//        numberOfGuests:"",
//        checkIn:"",
//        checkOut:"",
//        task:"",
//        notes:"",
//        status:""
//      })
//
//      const [assignedRoomError, setAssignedRoomError] = useState("");
//
//      const{room, roomAttendant, guest, numberOfGuests,checkIn,checkOut,task,notes,status}=assignedRoom
//
//      const onInputChange=(e)=>{
//      setAssignedRoom({ ...assignedRoom, [e.target.name]: e.target.value});
//      }
//
//
//      const fetchRoomNumbers = async () => {
//        try {
//        const roomNumbersResponse = await axios.get('http://localhost:8080/api/manager')
//        const roomNumbersArray = Object.entries(roomNumbersResponse.data);
//        setRoomNumbers(roomNumbersArray)
//        console.log('Room Number fetched', roomNumbersArray);
//        } catch (error) {
//          console.error('Error fetching room numbers', error);
//        }
//      }
//
//      const fetchRoomAttendants = async () => {
//        const roomAttendantsResponse = await axios.get('http://localhost:8080/api/manager')
//        const roomAttendantsArray = Object.entries(roomAttendantsResponse.data);
//        setRoomAttendants(roomAttendantsArray)
//      }
//
//      const fetchtasks = async () => {
//        const tasksResponse = await axios.get('http://localhost:8080/api/manager/task/assignroom')
//        const tasksArray = Object.entries(tasksResponse.data);
//        setTasks(tasksArray)
//      }
//
//      const fetchStatuses = async () => {
//        const statusesResponse = await axios.get('http://localhost:8080/api/manager/assignroom')
//        const statusesArray = Object.entries(statusesResponse.data);
//        setStatuses(statusesArray)
//      }
//
//
//      const onFormSubmit=async(e)=>{
//      e.preventDefault();
//      try {
//      await axios.post("http://localhost:8080/api/manager/assignroom", assignedRoom, {
//        headers: {
//          'Content-Type': 'application/json',
//        },
//      });
//      navigate("/")
//      } catch (error) {if (error.response) {
//        setAssignedRoomError(error.response.data);
//        console.log(error.response.status);
//        console.log(error.response.headers);
//      } else if (error.request) {
//        console.log(error.request);
//      } else {
//        console.log('Error', error.message);
//      }
//    }
//  };
//
//
//  return (
//    <div className='container'>
//      <div className="row">
//        <div className='col-md-6 offset-md border rounded p-4 mt-2 shadow'>
//          <h2 className='text-center m-4'>Assign Room</h2>
//          <form onSubmit={(e) => onFormSubmit(e)}>
//          <div className='mb-3'>
//            <label htmlFor="room" className='form-label'>
//              Room Number
//            </label>
//            <select
//                className="form-control"
//                id="room"
//                value={room}
//                onChange={(e) => onInputChange(e)}
//              >
//                <option value="" disabled>Select room</option>
//                {roomNumbers.map((roomNumber) => (
//                  <option key={roomNumber} value={roomNumber}>
//                    {roomNumber}
//                  </option>
//                ))}
//              </select>
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="roomAttendant" className='form-label'>
//              Room Attendant
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Choose room attendant"
//            id="roomAttendant"
//            value={roomAttendant}
//            onChange={(e)=>onInputChange(e)}
//            />
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="guest" className='form-label'>
//              Guest
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Enter guest name"
//            id="guest"
//            value={guest}
//            onChange={(e)=>onInputChange(e)}
//            />
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="numberOfGuests" className='form-label'>
//              Number of Guests
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Enter number of guests"
//            id="numberOfGuests"
//            value={numberOfGuests}
//            onChange={(e)=>onInputChange(e)}
//            />
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="checkIn" className='form-label'>
//              CheckIn
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Enter check-in day"
//            id="checkIn"
//            value={checkIn}
//            onChange={(e)=>onInputChange(e)}
//            />
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="checkOut" className='form-label'>
//              CheckOut
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Enter check-out day"
//            id="checkOut"
//            value={checkOut}
//            onChange={(e)=>onInputChange(e)}
//            />
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="task" className='form-label'>
//              Cleaning Task
//            </label>
//            <select name='task' value={assignedRoom.task} onChange={onInputChange}>
//                                <option value="" disabled>Select a task</option>
//                                {tasks.map(([name, displayName]) => (
//                                    <option key={name} value={name}>{displayName}</option>
//                                ))}
//                            </select>
//          </div>
//          <div className='mb-3'>
//            <label htmlFor="notes" className='form-label'>
//              Notes
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Notes"
//            id="notes"
//            value={notes}
//            onChange={(e)=>onInputChange(e)}
//            />
//            </div>
//            <div className='mb-3'>
//            <label htmlFor="status" className='form-label'>
//              Status
//            </label>
//            <input
//            type={"text"}
//            className="form-control"
//            placeholder="Choose cleaning status"
//            id="status"
//            value={status}
//            onChange={(e)=>onInputChange(e)}
//            />
//          </div>
//          <button type='submit' className='btn btn-outline-primary'>Submit</button>
//          <button type='submit' className='btn btn-outline-danger mx-2'>Cancel</button>
//          </form>
//          </div>
//        </div>
//      </div>
//  );
//}
