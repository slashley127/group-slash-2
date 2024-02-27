import React from "react"
import { Link } from "react-router-dom"

function LogoutComponent(){
    return(
        <div className="container mt-4 d-flex justify-content-center align-items-center ">
            <h1> You are logged out!!  </h1>
            <div>
            <span>
            <h1>Thank you for using our App. Come back soon!</h1>
            </span>
            </div>
            <Link className='btn btn-primary my-2' to={'/landing/attendants'}>Back to Home</Link>

        </div>
    )
}
export default LogoutComponent