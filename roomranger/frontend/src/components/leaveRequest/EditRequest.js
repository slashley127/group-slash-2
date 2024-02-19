import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

export default function EditRequest() {

    let navigate = useNavigate();
    const { id } = useParams();
    const [leaveRequest, setLeaveRequest] = useState({
        firstName:"",
        lastName:"",
        roomAttendant:"",
        remainingDays:"",
        startDate: "",
        endDate: "",
        reason: "",
    })

    useEffect(()=>{
        loadLeaveRequest();
    },[])

    const jwt = localStorage.getItem('jwt');

    const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
          Authorization: `Bearer ${jwt}`
        }
      });
    const loadLeaveRequest = async () =>{
        const response = await authAxios.get(`/leave/${id}/edit`);
        setLeaveRequest(response.data);
    }
 return (
    <div>EditRequest</div>
  )
}
