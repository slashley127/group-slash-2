import React from 'react'
import { Link } from 'react-router-dom';

export default function NavBar() {
    return (
        <div className='nbar'>
            <nav class="navbar navbar-expand-sm navbar-dark">
            <div className='text-left'>

                    <a href='https://roomranger.com' className='navbar-brand '><h2>RoomRanger</h2></a>
                    </div>
                <ul class="navbar-nav">
                    <li class="nav-item">
                    <Link class="nav-link active" aria-current="page" to="manager">Homepage</Link>
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link active" aria-current="page" to="/rooms">Rooms</Link>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Assign Room</a>
                    </li>
                    <li class="nav-item">
                    <Link class="nav-link active" aria-current="page" to="/attendantListComponent">Room Attendant</Link>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Leave Request</a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
