import React from "react";
import { useAuth } from "./security/AuthContext";
import HomeManager from "./manager/HomeManager";
import HomeAttendant from "./attendantRolePage/HomeAttendant";

function Landing() {
    const {isManager} = useAuth();
    return isManager ? <HomeManager /> : <HomeAttendant />;
}

export default Landing;