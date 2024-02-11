import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavBar from './components/NavBar';
import Home from './components/rooms/Home';

import {BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import AddRoom from './components/rooms/AddRoom';
import EditRoom from './components/rooms/EditRoom';
import ViewRoom from './components/rooms/ViewRoom';
import AttendantListComponent from './components/roomAttendants/AttendantListComponent';
import AddAttendant from './components/roomAttendants/AddAttendant';
import Update from './components/roomAttendants/Update';
import Profile from './components/roomAttendants/Profile';
// import { Header } from './components/Header';
import { Footer } from './components/Footer';
import LoginPage  from './components/loginPage/LoginPage';
import ManagerHomePage from './components/ManagerHomePage';
import RoomAttendantPage from './components/RoomAttendantPage';
import ErrorComponent from './components/ErrorComponent';
import  AuthProvider, { useAuth } from './components/security/AuthContext';
import OpeningPage from './components/OpeningPage';
import LogoutComponent from './components/LogoutComponent';

function AuthenticatedRoute({children}){
  const authContext=useAuth()
  if(authContext.isAuthenticated)
    return children

    return <Navigate to={"/login"}/>
}

function App() {
  return (
    <div className="App">
      <AuthProvider>
      <Router>
        <NavBar />
        <Routes>
          {/* give path /addroom to the AddRoom page */}
          <Route exact path="/rooms/addroom" element={<AddRoom />} />
          <Route exact path="/rooms" element={<Home />} />
          <Route exact path="/rooms/editroom/:id" element={<EditRoom/>} />
          <Route exact path="/rooms/viewroom/:id" element={<ViewRoom/>} />
          <Route exact path="/login" element={<LoginPage/>}/>
        <Route exact path="/login/managerHomePage" element={
        <AuthenticatedRoute>  <ManagerHomePage/></AuthenticatedRoute>}></Route>
        <Route exact path="/roomAttendantPage" element={<RoomAttendantPage/>}></Route>
        <Route exact path="/attendantListComponent" element={<AttendantListComponent />} />
        <Route exact path="/add" element={<AddAttendant />} />
        <Route exact path="/update/:id" element={<Update/>} />
        <Route exact path="/profile/:id" element={<Profile/>} />
        <Route exact path="*" element={<ErrorComponent/>} />
        <Route exact path="/" element={<OpeningPage/>} />
        <Route exact path="/logout" element={<LogoutComponent/>} />
        </Routes>
        <Footer/>
      </Router>
      </AuthProvider>
    </div>
  );
}

export default App;
