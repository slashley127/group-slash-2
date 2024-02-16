import React from 'react';
import { Link } from 'react-router-dom';

export default function Homepage() {
    return (
        <div>Homepage
            <div>
            <Link className='btn btn-primary' to='/landing/assignroom'>Assign Room</Link>
            </div>
        </div>
    )
}
