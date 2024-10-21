import classes from './Logout.module.css';
import connection from "../../axios";
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            // Call the logout endpoint
            const response = await connection.post('/api/appusers/logout', null, {
                headers: {
                    'Authorization': localStorage.getItem('authToken') // Use token from localStorage
                }
            });

            // Check if logout was successful
            if (response.status === 200) {
                // Clear the auth token from localStorage
                localStorage.removeItem('authToken');
                setMessage("Poprawnie wylogowano.");

                // Optionally redirect to the login page after 2 seconds
                setTimeout(() => {
                    navigate("/login"); // Ensure the path matches your login route
                }, 2000);
            } else {
                setMessage("Błąd podczas wylogowania.");
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

