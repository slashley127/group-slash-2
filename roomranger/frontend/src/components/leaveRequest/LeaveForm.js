import React from 'react'

export default function LeaveForm() {
    return (
        <div className="container mt-5 m-lg-auto p-5 shadow">
            <section className="leave-request">
                <header className="text-center mb-5">
                    <i className="bi bi-person-fill display-1 text-primary"></i>
                    <h1 className="display-5">Leave Request Form</h1>
                </header>
                <form id="LeaveRequestForm">
                    <div className="row mb-3">
                        <label className="form-label">Name</label>
                        <div className="col">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="First Name"
                                id="FirstName" />
                        </div>
                        <div className="col">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Last Name"
                                id="LastName" />
                        </div>
                    </div>
                    <div className="row mb-3">
                        <div className="col">
                            <label className="form-label">Employee ID</label>
                            <input type="number" className="form-control"
                                id="employeeId"
                                placeholder="Your Employee ID" />
                        </div>
                        <div className="col">
                            <label className="form-label">Remaining leave days</label>
                            <input type="number" className="form-control"
                                id="RemainingDays"
                                placeholder="Your remaining days of vacation" />
                        </div>
                    </div>
                    <div className="row mb-3">
                        <div className="col">
                            <label className="form-label">Start date<small className="text-muted">
                                (incl. 1st day)</small>
                            </label>
                            <input type="date"
                                className="form-control"
                                id="StartDate" />
                        </div>
                        <div className="col">
                            <label className="form-label">End date<small class="text-muted">
                                (incl. last day)</small>
                            </label>
                            <input type="date"
                                className="form-control"
                                id="EndDate" />
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label className="form-label">Leave Reason</label>
                        <div>
                            <input
                                type="text"
                                className="form-control"
                                id="LeaveReason" />
                        </div>
                    </div>
                    <button type='submit' className='btn btn-outline-primary'>Submit</button>
                    <button className='btn btn-outline-danger mx-2'>Cancel</button>
                </form>
            </section>
        </div>
    )
}
