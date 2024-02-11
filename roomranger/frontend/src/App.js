import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavBar from './components/NavBar';
import RouteHome from './components/Route';
import HomeRoom from './components/rooms/HomeRoom';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddRoom from './components/rooms/AddRoom';
import EditRoom from './components/rooms/EditRoom';
import ViewRoom from './components/rooms/ViewRoom';
// import HomeAttendant from './components/attendantRolePage/HomeAttendant';
import LeaveList from './components/leaveRequest/LeaveList';
import LeaveForm from './components/leaveRequest/LeaveForm';
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
              <Route path='login' element={<Login />} />
              <Route path='register' element={<Register />} /> 
           </Route> */}
          {/* </Route> */}


          <Route path="/landing" element={<NavBar />}>
            <Route path="rooms" element={<RouteHome />}>
              <Route index element={<HomeRoom />} />
              {/* give path /addroom to the AddRoom page */}
              <Route path="addroom" element={<AddRoom />} />
              <Route path="editroom/:id" element={<EditRoom />} />
              <Route path="viewroom/:id" element={<ViewRoom />} />
            </Route>
            <Route exact path="leave" element={<LeaveForm />} />
          </Route>
          {/* <Route exact path="/attendant" element={<HomeAttendant />} />  */}
        </Routes>
      </Router>
    </div>
  );
}

export default App;
