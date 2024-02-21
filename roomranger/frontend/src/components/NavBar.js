import React from 'react'
import { Link, Outlet } from 'react-router-dom';
import { useAuth } from './security/AuthContext';

export default function NavBar() {
    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated
    const isManager = authContext.isManager

    function logout() {
        authContext.logout()
    }
    return (
        <div className='nbar'>
            <nav className="navbar navbar-expand-sm navbar-dark">
                <div className='navbar-nav text-left'>
                    <a href='/landing'  className='navbar-brand fw-bold'><h2>RoomRanger</h2></a>
                </div>
                <div className='collapse navbar-collapse'>
                <ul className="navbar-nav">
                    <li className="nav-item">
                    { isAuthenticated && <Link className='nav-link fw-bold' aria-current="page" to="/landing">Home Page</Link >}
                    </li>
                    <li className="nav-item">
                    { isAuthenticated && <Link className='nav-link fw-bold' aria-current="page" to="/landing/rooms">Rooms</Link>}
                    </li>
                    {isManager &&
                    <li className="nav-item">
                      { isAuthenticated && <Link className='nav-link fw-bold' aria-current="page" to="/landing/attendants">Room Attendant</Link>}
                    </li>}
                    <li className="nav-item">
                    { isAuthenticated && <Link className='nav-link fw-bold' aria-current="page" to="/landing/leave">Leave Request</Link>}
                    </li>
                    <li className="nav-item">
                    { isAuthenticated &&  <Link className='nav-link fw-bold' aria-current="page" to="/landing/roomattendant">Home Attendant</Link>}
                    </li>
                    <li className="nav-item">
                    { isAuthenticated && <Link className='nav-link fw-bold' aria-current="page" to="/landing/groupchat">Group Chat</Link>}
                    </li>
                    </ul>
                    </div>
                    <ul className='navbar-nav'>
                <li className='nav-item'>
                    { !isAuthenticated && <Link className='nav-link' to='/login'><h5>Log In</h5></Link>}
                </li>
                <li className='nav-item'>
                    { isAuthenticated && <Link className='nav-link' to='/logout' onClick={logout}><h5>Log Out</h5></Link>}
                </li>
              </ul>

            </nav>
            <Outlet />
        </div>
    );
}
