import React from 'react'
import { Link } from 'react-router-dom';
import { useAuth } from './security/AuthContext';
import OpeningPage from './OpeningPage';

export default function NavBar() {

    const authContext=useAuth();
    const isAuthenticated=authContext.isAuthenticated

    function logout(){
        authContext.logout()
    }
    return (
        <div className='nbar'>
            <nav class="navbar navbar-expand-sm navbar-dark">
            <div className=' navbar-nav text-left'>

                    <a href='https://roomranger.com' className='navbar-brand fw-bold'><h2>RoomRanger</h2></a>
                    </div>
             <div className='collapse navbar-collapse'>
                <ul class="navbar-nav">
                    <li class="nav-item">
                    { isAuthenticated && <Link className='nav-link fw-bold' href="#">Home Page</Link>}

                    </li>
                    <li class="nav-item">
                       { isAuthenticated && <Link class="nav-link active fw-bold" aria-current="page" to="/rooms">Rooms</Link>}
                    </li>
                    <li class="nav-item">
                    { isAuthenticated && <Link className='nav-link fw-bold' href="#">Assign Room</Link>}
                    </li>
                    <li class="nav-item">
                    { isAuthenticated &&
                    <Link class="nav-link active fw-bold" aria-current="page" to="/attendantListComponent">Room Attendant</Link>}
                    </li>
                    <li class="nav-item">
                       { isAuthenticated && <Link class="nav-link fw-bold" href="#">Leave Request</Link>}
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
        </div>
    );
}
