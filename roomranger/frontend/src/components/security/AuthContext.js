import { createContext, useContext, useState } from "react";

export const AuthContext=createContext();
export const useAuth=()=>useContext(AuthContext)

export default function AuthProvider({children}){

    const [number,setNumber]=useState(0)
    const[isAuthenticated,setAuthenticated]=useState(false)
    const[isManager,setManager]=useState(false)
    const[username,setUsername]=useState(null)
    // setInterval=(()=>setNumber(number+1),10000)

    function login(jwt,loginSuccess){
        if(loginSuccess==='true'){
            localStorage.setItem('jwt', jwt); // Save the JWT in local storage

            const decodedJwt = parseJwt(jwt);
            if (decodedJwt) {
              // Store username and roles in local storage
              localStorage.setItem('username', decodedJwt.sub); 
              localStorage.setItem('roles', decodedJwt.roles);
            }
            console.log("UserName:"+decodedJwt.sub)
            console.log("Role:"+decodedJwt.roles)
            setUsername(decodedJwt.sub)
            setAuthenticated(true)
            if(decodedJwt.roles==='manager'){
              setManager(true)
              console.log("Setting Manager True")
            }
            else{
              setManager(false)
            }
            console.log("Printing IsManager:"+isManager)
            console.log("Printing IsAuthenticated:"+isAuthenticated)
             
        }else{
             setAuthenticated(false)
             setUsername(null)
             setManager(false)
           }
    }

    function logout(){
       localStorage.removeItem('jwt');
       localStorage.removeItem('username');
       localStorage.removeItem('roles');
       setAuthenticated(false);
       setManager(false)
       
       setUsername(null);
    }

    function parseJwt(token) {
        try {
          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
          const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
          }).join(''));
    
          return JSON.parse(jsonPayload);
        } catch (e) {
          console.error("Error parsing JWT", e);
          return null;
        }
      }

    return(
        <AuthContext.Provider value={{number,isAuthenticated,isManager,login,logout,username}}>
            {children}
        </AuthContext.Provider>
    )
}
 