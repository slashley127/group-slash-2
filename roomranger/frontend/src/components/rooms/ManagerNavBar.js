import React from 'react'
import { Link } from 'react-router-dom';

export default function ManagerNavBar() {
    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-primary navbar-dark">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <a className="nav-link" href="#">Home Page</a>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link active" aria-current="page" to="/rooms">Rooms</Link>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Assign Room</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Room Attendant</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Leave Request</a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
