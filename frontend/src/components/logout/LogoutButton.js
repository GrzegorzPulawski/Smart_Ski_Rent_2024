import {useNavigate} from "react-router-dom";
import React from "react";

const LogoutButton = () => {
    const navigate = useNavigate();

    const handleLogout = ()=> {
        localStorage.removeItem('token');
        navigate("/");
    };

    return(
        <button onClick={handleLogout}>Wyloguj się</button>
    );
};
export default LogoutButton;