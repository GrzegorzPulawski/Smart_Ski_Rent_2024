import classes from "./Logout.module.css";
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            // Remove the stored token from localStorage
            const authToken = localStorage.getItem('authToken');

            if (authToken) {
                // Clear the auth token from localStorage
                localStorage.removeItem('authToken');
                setMessage("Poprawnie wylogowano.");

                // Optionally, redirect to the login page after 2 seconds
                setTimeout(() => {
                    navigate("/login"); // Update the path to match your login route
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

