import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";

export default function Profile() {

    let navigate = useNavigate();

    const [attendant, setAttendant] = useState({
        id: '',
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '',
        pronoun: '',
        username: '',
        password: '',
        notes: '',
        workingDays: []
    });

    const { firstName, lastName, email, phoneNumber, pronoun, username, password, notes, workingDays } = attendant;
    const { id } = useParams();

    useEffect(() => {
        loadAttendant();
    }, [id]);
    
    const jwt = localStorage.getItem('jwt'); // Retrieve the JWT token from local storage

    const authAxios = axios.create({
      baseURL: "http://localhost:8080",
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    });

    const loadAttendant = async () => {
        const result = await authAxios.get(`/roomAttendant/profile/${id}`);
        setAttendant(result.data)
    };


    return (
        <div className="list">
            <div className='container'>
                <div className='row'>
                    <div className='col-md-6 offset-md-3 border rounded p-4 mt-2  shadow'>
                        <h3 className='text-center m-10 '>
                            {attendant.firstName} {lastName}</h3>
                        <div className='card'>
                            <div className='card-header'>
                                <ul className='list-group list-group-flush mt-4'>
                                    <li className=' list-group-item text-center h-75'>
                                        <b> Employee ID: </b>
                                        {attendant.id}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> First Name: </b>
                                        {attendant.firstName}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Last Name: </b>
                                        {attendant.lastName}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Pronoun: </b>
                                        {attendant.pronoun}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Email: </b>
                                        {attendant.email}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Phone Number: </b>
                                        {attendant.phoneNumber}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Schedule: </b>
                                        {attendant.workingDays && attendant.workingDays.length > 0
                                            ? attendant.workingDays.join(', ')
                                            : ''}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Username </b>
                                        {attendant.username}
                                    </li>
                                    <li className='list-group-item text-center mt-3'>
                                        <b> Password </b>
                                        {attendant.password}
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <Link className='btn btn-primary my-2' to={'/landing/attendants'}>Back to Home</Link>
                    </div>
                </div>
            </div>
        </div>

    )

}