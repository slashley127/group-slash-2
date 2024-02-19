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
    const loadLeaveRequest = async () =>{
        const response = await axios.get(`http://localhost:8080/leave/${id}/edit`);
        setLeaveRequest(response.data);
    }
 return (
    <div>EditRequest</div>
  )
}
