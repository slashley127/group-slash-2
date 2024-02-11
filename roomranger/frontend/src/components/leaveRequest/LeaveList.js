import React, { useState } from 'react'

export default function LeaveList() {
  const[roomsAssigned, setRoomsAssigned] = useState([]);
  
  return (
    <div className='container' >
      {/* <div className='col-md-10 offset-md-1 border rounded p-4 mt-2 shadow'> */}
      <div className='text-center mb-5'>
        <h2>Leave Request List</h2>
        {/* // <table className="table boder"> */}
        <table className='container mt-5 m-lg-auto p-5 shadow'>
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">From Date</th>
              <th scope='col'>To Date</th>
              <th scope='col'>Days</th>
              <th scope='col'>Reason</th>
              <th scope='col'>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td scope="row">1</td>
              <td scope="row">Test</td>
              <td scope="row">Test</td>
              <td scope="row">Test</td>
              <td scope="row">Test</td>
              <td scope="row">Test</td>
              <td scope="row">
                <button className='btn btn-primary mx-2' >Approve</button>
                <button className='btn btn-danger'>Reject</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  )
}
