import React from 'react'
import { Outlet } from 'react-router-dom';

export default function Route() {
    return (
        <div>
            {/* <h1>This is the Outlet Layout Page</h1> */}
            <Outlet />
        </div>
    );
}
