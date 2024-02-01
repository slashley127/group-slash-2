import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditRoom() {
    let navigate = useNavigate();
    const { id } = useParams()
    const [types, setTypes] = useState([]);
    const [room, setRoom] = useState({
        roomNumber: "",
        roomType: "",
        available: false
    });
    useEffect(() => {
        fetchTypes();
        loadRoom();
    }, [])
    // room input handler
    const onInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        const updatedValue = type === 'checkbox' ? checked : value;
        setRoom({ ...room, [name]: updatedValue });
    }
    // get the types
    const fetchTypes = async () => {
        const typesResponse = await axios.get('http://localhost:8080/rooms/types');
        const typesArray = Object.entries(typesResponse.data);
        setTypes(typesArray);
    };
    //fetch room
    const loadRoom = async () => {
        const result = await axios.get(`http://localhost:8080/rooms/room/${id}`)
        setRoom(result.data);
    }

    //form submit event handler
    const onFormSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`http://localhost:8080/rooms/room/${id}`, room);
        navigate("/");  //navigate to the home page
    }

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Edit Room</h2>
                    <form onSubmit={onFormSubmit}>
                        <div className='mb-3'>
                            <label htmlFor='RoomNumber' className='form-label'>
                                Room Number
                            </label>
                            <input type='text'
                                className='form-control'
                                placeholder='Enter the room number'
                                name='roomNumber'
                                value={room.roomNumber}
                                onChange={onInputChange} />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor='RoomType' className='form-label'>
                                Room Type
                            </label>
                            <select name='roomType' value={room.roomType} onChange={onInputChange}>
                                <option value="" disabled>Select a type</option>
                                {/* destructuring assignment  */}
                                {types.map(([name, displayName]) => (
                                    <option key={name} value={name}>{displayName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='mb-3'>
                            <label hemlFor='Available' >
                                Is Available
                                <input
                                    type="checkbox"
                                    name='available'
                                    checked={room.available}
                                    onChange={onInputChange}
                                />
                            </label>
                        </div>
                        <button type='submit' className='btn btn-outline-primary'>Submit</button>
                        <Link className='btn btn-outline-danger mx-2' to='/rooms'>Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}
