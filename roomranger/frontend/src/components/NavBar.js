import React from 'react'
import { Link } from 'react-router-dom';

export default function NavBar() {
    return (
        <div>
            <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <Link class="nav-link active" aria-current="page" to="/">Rooms</Link>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Assign Room</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Room Attendant</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Leave Request</a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
