import classes from "./Logout.module.css";
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import connection from "../../axios";

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            // Sprawdzenie, czy dane logowania są dostępne w localStorage
            const appUserName = localStorage.getItem('appUserName');
            const password = localStorage.getItem('password');

            if (appUserName && password) {
                // Opcjonalne wywołanie API do wylogowania
                // await connection.post('/api/appusers/logout', {}, {
                //     auth: {
                //         username: appUserName,
                //         password: password
                //     }
                // });

                // Usunięcie danych logowania z localStorage
                localStorage.removeItem('appUserName');
                localStorage.removeItem('password');
                setMessage("Poprawnie wylogowano.");

                // Przekierowanie po 2 sekundach
                setTimeout(() => {
                    navigate("/api/appusers/login"); // Upewnij się, że ścieżka jest poprawna
                }, 2000);
            } else {
                setMessage("Brak danych logowania.");
            }
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

