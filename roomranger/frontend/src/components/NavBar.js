import React from 'react'
import { Link, Outlet } from 'react-router-dom';
import { useAuth } from './security/AuthContext';

export default function NavBar() {
    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated

    function logout() {
        authContext.logout()
    }
    return (
        <div className='nbar'>
            <nav className="navbar navbar-expand-sm navbar-dark bg-primary">
                <div className='text-left'>
                    <a href='/landing' className='navbar-brand '><h2>RoomRanger</h2></a>
                </div>
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" aria-current="page" to="/landing">Home Page</Link >
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" aria-current="page" to="/landing/rooms">Rooms</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" aria-current="page" to="/landing/attendants">Room Attendant</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" aria-current="page" to="/landing/leave">Leave Request</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" aria-current="page" to="/landing/roomattendant">Home Attendant</Link>
                    </li>
                    <li className='nav-item'>
                        {isAuthenticated && <Link className='nav-link' to='/login' onClick={logout}><h5>Log Out</h5></Link>}
                    </li>
                </ul>
            </nav>
            <Outlet />
        </div>
    );
}
