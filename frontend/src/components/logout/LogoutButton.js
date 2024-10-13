import {useNavigate} from "react-router-dom";
import React from "react";
import {useState} from "react";

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = () => {
        // Usuwanie danych uwierzytelniających z localStorage
        localStorage.removeItem('username');
        localStorage.removeItem('password');
        localStorage.removeItem('token');
        setMessage("Poprawnie wylogowano.");

        setTimeout(() => {
            navigate("/login"); // Przekierowanie na stronę logowania
        }, 2000); // Opóźnienie 2 sekundy
    };
    return(
        <div>
        <button onClick={handleLogout}>Potwierdź wylogowanie</button>
    {message && <p style={{ color: 'green' }}>{message}</p>}
        </div>
    );
};
export default LogoutButton;