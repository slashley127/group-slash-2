import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

export default function EditRequest() {

  let navigate = useNavigate();
  const { id } = useParams();
  const [leaveRequest, setLeaveRequest] = useState({
    firstName: "",
    lastName: "",
    roomAttendant: "",
    remainingDays: "",
    startDate: "",
    endDate: "",
    reason: "",
  })

  useEffect(() => {
    loadLeaveRequest();
  }, [])

  const jwt = localStorage.getItem('jwt');

  const authAxios = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      Authorization: `Bearer ${jwt}`
    }
  });
  const loadLeaveRequest = async () => {
    try {
      const response = await authAxios.get(`/leave/${id}/edit`);
      setLeaveRequest(response.data);
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
  return (
    <div>EditRequest</div>
  )
}
