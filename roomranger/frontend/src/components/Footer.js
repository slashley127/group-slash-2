import React from 'react'
import { Link } from 'react-router-dom';
export const Footer = () => {
  return (
    <div className='container'>
        <footer className='footer'>
           <Link to='/about' >About us</Link>
            <span className='text-muted'>    All Rights reserved 2024 @WomenThatCode</span>
        </footer>
    </div>
  )
}
