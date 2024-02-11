import { createContext, useContext, useState } from "react";

export const AuthContext=createContext();
export const useAuth=()=>useContext(AuthContext)

export default function AuthProvider({children}){

    const [number,setNumber]=useState(0)
    const[isAuthenticated,setAuthenticated]=useState(false)
    const[username,setUsername]=useState(null)
    // setInterval=(()=>setNumber(number+1),10000)

    function login(username, password){
        if(username==='samata' && password==='karka'){
            setUsername(username)
             setAuthenticated(true)
            return true
             
        }else{
             setAuthenticated(false)
             setUsername(null)
             return false
            
           }
    }

    function logout(){
       setAuthenticated(false)
    }

    return(
        <AuthContext.Provider value={{number,isAuthenticated,login,logout,username}}>
            {children}
        </AuthContext.Provider>
    )
}
 