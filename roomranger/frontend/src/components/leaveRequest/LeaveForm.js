import { wait } from '@testing-library/user-event/dist/utils';
import axios from 'axios';
import { addDays, differenceInDays, isWeekend} from 'date-fns';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function LeaveForm({firstname = "Luna", lastname = "Liu"}) { 
   
    let navigate = useNavigate();
    const[leaveRequest, setLeaveRequest] = useState({
        firstName:"",
        lastName:"",
        roomAttendant:"",
        // initialDays: 20,
        // duration: 0,
        remainingDays:20,
        startDate: "",
        endDate: "",
        // submittedDate: "",
        // status:"Pending",
        reason: "",
    });
    const calculateDuration = (startDate, endDate) => {
        if(!startDate || !endDate) return 0;
        const start = new Date(startDate);
        const end = new Date(endDate);
        const diffTime = Math.abs(end - start);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
        // console.log("diffTime" + diffTime);
        // console.log("diffDays" + diffDays);
        // setLeaveRequest({ ...leaveRequest, duration: diffDays })
        return diffDays;
    }
    const calculateRemaingDays = () =>{
        // const{remainingDays, duration} = leaveRequest;
        // setLeaveRequest({...leaveRequest,remainingDays: remainingDays - duration});
    }
    // const calculateRemainingDays = () =>{
    //     const{startDate,endDate,remainingDays} = leaveRequest;
    //     const totalWorkDays = differenceInDays(endDate,startDate) + 1;
    //     const totalWeekendDays = Array.from({length: totalWorkDays}).filter((_,index) =>{
    //         const currentDate = addDays(startDate,index);
    //         return isWeekend(currentDate);
    //     }).length;
    //     const totalHolidays = Array.from({ length: totalWorkDays }).filter((_, index) => {
    //         const currentDate = addDays(startDate, index);
    //         return isHoliday(currentDate); // 假设你有一个判断日期是否是节假日的函数
    //     }).length;
    //     const totalWorkDaysExcludeWeekendsAndHolidays = totalWorkDays - totalWeekendDays - totalHolidays;
    //      remainingDays = remainingDays - totalWorkDaysExcludeWeekendsAndHolidays;
    //      return remainingDays;
    // }
    // function getSubmissionTimeStamp(){
    //     this.today = new Date();
    //     const date = `${today.getFullYear()}-${today.getMonth() + 1}-${today.getDate()}`;
    //     const time = `${today.getHours()}:${today.getMinutes()}:${today.getSeconds()}`;
    //     const dateTime = `${data} / ${time}`;
    //     return dateTime;
    // }
    const onInputChange =(e)=>{
        const{name,value} = e.target;
        console.log("^^^^", name, value, typeof value);
        setLeaveRequest({...leaveRequest,[name]:value});
    }
    const onFormSubmit = async(e) =>{
        e.preventDefault();
        try{ 
            await axios.post("http://localhost:8080/leave/add", leaveRequest);
            navigate("/landing/leave");
            // console.log(leaveRequest);
        }catch(error){
            // console.log(error.response.data);
            alert(error.response.data.message);
        }
    }
    
    return (
        <div className="container mt-5 m-lg-auto p-5 shadow">
            <section className="leave-request">
                <header className="text-center mb-5">
                    {/* <i className="bi bi-person-fill display-1 text-primary"></i> */}
                    <h2 className="display-5">Leave Request Form</h2>
                </header>
                <form id="LeaveRequestForm" onSubmit={onFormSubmit}>
                    <div className="row mb-3">
                        <label className="form-label">Name</label>
                        <div className="col">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="First Name"
                                name="firstName" 
                                value={leaveRequest.firstName}
                                onChange={onInputChange}/>
                        </div>
                        <div className="col">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Last Name"
                                name="lastName"
                                value={leaveRequest.lastName}
                                onChange={onInputChange} />
                        </div>
                    </div>
                    <div className="row mb-3">
                        <div className="col">
                            <label className="form-label">Employee ID</label>
                            <input type="number" className="form-control"
                                name="roomAttendant"
                                placeholder="Your Employee ID"
                                value={leaveRequest.roomAttendant}
                                onChange={onInputChange} />
                        </div>
                        <div className="col">
                            <label className="form-label">Remaining leave days</label>
                            <label className="form-control">{leaveRequest.remainingDays - calculateDuration(leaveRequest.startDate, leaveRequest.endDate)}</label>
                            {/* <label className="form-control">{leaveRequest.roomAttendant.remainingDays}</label> */}
                            {/* <input type="number" className="form-control"
                                name="remainingDays"
                                placeholder="Your remaining days of vacation" 
                                value={leaveRequest.remainingDays} /> */}
                        </div>
                    </div>
                    <div className="row mb-3">
                        <div className="col">
                            <label className="form-label">Start date<small className="text-muted">
                                (incl. 1st day)</small>
                            </label>
                            <input type="date"
                                className="form-control"
                                name="startDate"
                                value={leaveRequest.startDate}
                                onChange={onInputChange} />
                        </div>
                        <div className="col">
                            <label className="form-label">End date<small className="text-muted">
                                (incl. last day)</small>
                            </label>
                            <input type="date"
                                className="form-control"
                                name="endDate"
                                value={leaveRequest.endDate}
                                onChange={onInputChange} />
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div>
                            <label className="form-label">Leave Reason</label>
                            <textarea
                                // type="textarea"
                                className="form-control"
                                name="reason" 
                                value={leaveRequest.reason}
                                onChange={onInputChange}/>
                        </div>
                    </div>
                    <button type='submit' className='btn btn-outline-primary'>Submit</button>
                    <Link className='btn btn-outline-danger mx-2' to='/landing/leave'>Cancel</Link >
                </form>
            </section>
        </div>
    )
}
