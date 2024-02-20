import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import NavBar from './components/NavBar';
import HomeRoom from './components/rooms/HomeRoom';
import { BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import AddRoom from './components/rooms/AddRoom';
import EditRoom from './components/rooms/EditRoom';
import ViewRoom from './components/rooms/ViewRoom';
import AssignRoom from './components/manager/AssignRoomForm';
import EditAssignedRoom from './components/manager/EditAssignedRoom';
import AttendantListComponent from './components/roomAttendants/AttendantListComponent';
import AddAttendant from './components/roomAttendants/AddAttendant';
import Update from './components/roomAttendants/Update';
import Profile from './components/roomAttendants/Profile';
import { Footer } from './components/Footer';
import LoginPage from './components/loginPage/LoginPage';
import HomeManager from './components/manager/HomeManager';
import ErrorComponent from './components/ErrorComponent';
import RouteHome from './components/Route';
import LeaveForm from './components/leaveRequest/LeaveForm';
import LeaveList from './components/leaveRequest/LeaveList';
import EditRequest from './components/leaveRequest/EditRequest';
import HomeAttendant from './components/attendantRolePage/HomeAttendant';
import UpdateStatus from './components/attendantRolePage/UpdateStatus';
import Home from './components/hompage/Home';
import Registration from './components/hompage/Registration';
import About from './components/About';
import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import  AuthProvider, { useAuth } from './components/security/AuthContext';
import GroupChatComponent from './components/groupChatPage/GroupChatComponent';

function AuthenticatedRoute({children}){
  const authContext=useAuth()
  if(authContext.isAuthenticated)
    return children

    return <Navigate to={"/login"}/>
}

function App() {
  const [isDarkMode, setIsDarkMode] = useState(false);

  useEffect(() => {
    document.body.className = isDarkMode ? 'dark-theme' : 'light-theme';
  }, [isDarkMode]);

  const toggleTheme = () => {
    setIsDarkMode((prevMode) => !prevMode);
  };

  return (
    <div className='App'>
      <AuthProvider>
      <div className='mode-toggle' onClick={toggleTheme}>
        <div className={`slider ${isDarkMode ? 'dark' : 'light'}`} />
        <span className='mode-text'>{isDarkMode ? 'Light' : 'Dark'}</span>
      </div>
      <Router>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='Register' element={<Registration />} />
          <Route path='login' element={<LoginPage />} />
          <Route path='about' element={<About />} />
          <Route path='/landing' element={<NavBar />} >
            <Route index element={<HomeManager />} />
            <Route path='assignroomform' element={<AssignRoom />} />
            <Route exact path='editassignedroom/:id' element={<EditAssignedRoom />} />
            <Route path='rooms' element={<RouteHome />}>
              <Route index element={<HomeRoom />} />
              <Route path='addroom' element={<AddRoom />} />
              <Route path='editroom/:id' element={<EditRoom />} />
              <Route path='viewroom/:id' element={<ViewRoom />} />
            </Route>
            <Route path='attendants' element={<RouteHome />}>
              <Route index element={<AttendantListComponent />} />
              <Route path='add' element={<AddAttendant />} />
              <Route path='update/:id' element={<Update />} />
              <Route path='profile/:id' element={<Profile />} />
              <Route exact path='*' element={<ErrorComponent />} />
            </Route>
            <Route path='leave' element={<RouteHome />} >
              <Route index element={<LeaveList />} />
              <Route path='edit/:id' element={<EditRequest />} />
              <Route path='form' element={<LeaveForm />} />
            </Route>
            <Route path="roomattendant" element={<RouteHome />} >
              <Route index element={<HomeAttendant />} />
              <Route path="assignedroom/:id" element={<UpdateStatus />} />
            </Route>
            <Route path="groupchat" element={<RouteHome />} >
              <Route index element={<GroupChatComponent />} />
            </Route>
          </Route>
        </Routes>
        <Footer />
      </Router>
      </AuthProvider> 
    </div >
  );
}

export default App;