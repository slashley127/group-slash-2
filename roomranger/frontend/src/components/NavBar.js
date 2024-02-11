import React from 'react'
import { Link, Outlet } from 'react-router-dom';

export default function NavBar() {
    return (
        <div>
            <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <Link class="nav-link" aria-current="page" to="/landing">Home Page</Link>
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link" aria-current="page" to="/landing/rooms">Rooms</Link>
                        {/* <Link class="nav-link" aria-current="page" to="/attendant">Rooms</Link> */}
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link" aria-current="page" to="/landing/leave">Leave Request</Link>
                    </li>
                </ul>
            </nav>
            <Outlet />
        </div>
    );
}
