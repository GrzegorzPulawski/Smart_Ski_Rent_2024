import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import connection from "../../axios";

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            await connection.post('/logout');
            setMessage('Poprawnie wylogowano');
            setTimeout(() => navigate('/login'), 5000); // Przekieruj na stronę logowania po 1 sekundzie
        } catch (error) {
            console.error('Error logging out:', error);
        }
    };

    return (
        <div>
            <button onClick={handleLogout}>Potwierdź wylogowanie</button>
            {message && <p style={{ color: 'green' }}>{message}</p>}
        </div>
    );
};

export default LogoutButton;

