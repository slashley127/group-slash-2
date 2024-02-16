import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';

export default function LeaveList() {
  const [leaveList, setLeaveList] = useState([]);
  // const navigate = useNavigate();
  const [refreshId, setRefreshId] = useState(Symbol()); // This is used to render the list after approve or reject

  useEffect(() => {
    loadRequestList();
  }, [refreshId]);

  const loadRequestList = async () => {
    try {
      const response = await axios.get("http://localhost:8080/leave");
      const leaveRequests = response.data.filter(leave => {
        const startDateYear = new Date(leave.startDate).getFullYear();
        const currentYear = new Date().getFullYear();
        return startDateYear === currentYear;
      })
      setLeaveList(response.data);
    } catch (error) {
      console.error('Error fetching leave requests:', error);
    }
  }

  //for manager to approve leave request
  const approve = async (id) => {
    try {
      await axios.put(`http://localhost:8080/leave/${id}/approve`);
      alert("You have approved this leave request!")
      setRefreshId(Symbol());
      console.log(refreshId);
      // window.location.reload();
    } catch (error) {
      alert(error.response.data.message);
    }

  }
  //for manager to reject leave request
  const reject = async (id) => {
    try {
      await axios.put(`http://localhost:8080/leave/${id}/reject`);
      alert("You have rejected this leave request!")
      setRefreshId(Symbol());
    } catch (error) {
      alert(error.response.data.message);
    }

  }
  const getStatusColor = (status) => {
    switch (status) {
      case 'Approved':
        return 'green';
      case 'Pending':
        return 'orange';
      case 'Rejected':
        return 'red';
      default:
        return 'black';
    }
  }

  return (
    <div className='container' >
      {/* <div className='col-md-10 offset-md-1 border rounded p-4 mt-2 shadow'> */}
      <div className='text-center py-8'>
        <h2 className='text-dark'>Leave Request List</h2>
        {/* // <table className="table boder">  container mt-5 m-lg-auto p-5 shadow*/}
        <table className='table table-striped shadow'>
          <thead>
            <tr>
              <th scope='col'>#</th>
              <th scope='col'>Name</th>
              <th scope='col'>From Date</th>
              <th scope='col'>To Date</th>
              <th scope='col'>Days</th>
              <th scope='col'>Reason</th>
              <th scope='col'>Status</th>
              <th scope='col'>Action</th>
            </tr>
          </thead>
          <tbody>
            {
              leaveList.map((leaveRequest, index) => (
                <tr>
                  <th scope='row' key={index}>{index + 1}</th>
                  <td>{leaveRequest.firstName + " " + leaveRequest.lastName}</td>
                  <td>{leaveRequest.startDate}</td>
                  <td>{leaveRequest.endDate}</td>
                  <td>{leaveRequest.duration}</td>
                  <td>{leaveRequest.reason}</td>
                  <td style={{ backgroundColor: getStatusColor(leaveRequest.status) }}>{leaveRequest.status}</td>
                  <td>
                    <button className='btn btn-outline-primary mx-2' onClick={() => approve(leaveRequest.id)}>Approve</button>
                    <button className='btn btn-outline-danger mx-2' onClick={() => reject(leaveRequest.id)}>Reject</button>
                    {/* <Link className='btn btn-outline-primary mx-2' to={`/landing/leave/edit/${leaveRequest.id}`}>Edit</Link> */}
                  </td>
                </tr>
              ))
            }
          </tbody>
        </table>
        <Link className='btn btn-primary' to='/landing/leave/form'>New Leave Request</Link>
      </div>
    </div>
  )
}
