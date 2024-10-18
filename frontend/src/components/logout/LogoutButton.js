import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import classes from "./Logout.module.css";
import connection from "../../axios"; // Zakładam, że masz odpowiedni plik CSS

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            await connection.post('/api/appusers/logout', {}, {
                auth: {
                    username: localStorage.getItem('username'),
                    password: localStorage.getItem('password')
                }
            });

            localStorage.removeItem('username');
            localStorage.removeItem('password');
            setMessage("Poprawnie wylogowano.");

            setTimeout(() => {
                navigate("/api/appusers/login");
            }, 2000);
        } catch (error) {
            setMessage("Wystąpił błąd podczas wylogowania.");
            console.error("Błąd wylogowania:", error);
        }
    };

    return (
        <div>
            <button onClick={handleLogout} className={classes.ActionButton}>
                Potwierdź wylogowanie
            </button>
            {message && <p style={{ color: message === "Poprawnie wylogowano." ? 'green' : 'red' }}>{message}</p>}
        </div>
    );
};

export default LogoutButton;

