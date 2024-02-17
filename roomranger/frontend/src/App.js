import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavBar from './components/NavBar';
import HomeRoom from './components/rooms/HomeRoom';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
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
// import Login from './components/users/Login';
// import Home from './components/pages/Home';
// import Register from './components/users/Register';
// import HomeNav from './components/layout/Navbar';


function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          {/* <Route element={<HomeNav />}> */}
          {/* <Route element={<RouteHome />}>
              <Route path='/' element={<Home />} />
              <Route path='login' element={<LoginPage />} />
              <Route path='register' element={<Register />} /> 
           </Route> */}
          {/* </Route> */}
          <Route path='/' element={<RouteHome />}>
            {/* <Route index element={<Register />} /> */}
            <Route path='login' element={<LoginPage />} />
          </Route>
          <Route path="/landing" element={<NavBar />} > 
            {/* <Route path='assignedrooms' element={<RouteHome />}> */}
              <Route index element={<HomeManager />} />
              <Route path="assignroomform" element={<AssignRoom />} />
              <Route exact path="editassignedroom/:id" element={<EditAssignedRoom />} />
            {/* </Route> */}
            <Route path="rooms" element={<RouteHome />}>
              <Route index element={<HomeRoom />} />
              <Route path="addroom" element={<AddRoom />} />
              <Route path="editroom/:id" element={<EditRoom />} />
              <Route path="viewroom/:id" element={<ViewRoom />} />
            </Route>
            <Route path="attendants" element={<RouteHome />}>
              <Route index element={<AttendantListComponent />} />
              <Route path="add" element={<AddAttendant />} />
              <Route path="update/:id" element={<Update />} />
              <Route path="profile/:id" element={<Profile />} />
              <Route exact path="*" element={<ErrorComponent />} />
            </Route>
            <Route path="leave" element={<RouteHome />} >
              <Route index element={<LeaveList />} />
              <Route path="edit/:id" element={<EditRequest />} />
              <Route path="form" element={<LeaveForm />} />
            </Route>
          </Route>
          {/* <Route exact path="/attendant" element={<HomeAttendant />} />  */}

        </Routes>
        <Footer />
      </Router>
      {/* <Footer/> */}
    </div>

  );
}

export default App;
