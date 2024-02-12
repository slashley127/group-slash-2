import React from 'react'
import { Link } from 'react-router-dom';

export default function NavBar() {
    return (
        <div className='nbar'>
            <nav className="navbar navbar-expand-sm navbar-dark">
            <div className='text-left'>

                    <a href='https://roomranger.com' className='navbar-brand '><h2>RoomRanger</h2></a>
                    </div>
                <ul className="navbar-nav">
                    <li className="nav-item">
                    <Link className="nav-link active" aria-current="page" to="/assignedrooms">Homepage</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link active" aria-current="page" to="/rooms">Rooms</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link active" aria-current="page" to="/assignedrooms/assignroomform">Assign Room</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link active" aria-current="page" to="/attendantListComponent">Room Attendant</Link>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Leave Request</a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
