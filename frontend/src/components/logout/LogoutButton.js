import React, { useState } from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            await axios.post('/logout'); // Wyślij żądanie wylogowania do backendu
            localStorage.removeItem('token'); // Usuń token z localStorage lub sessionStorage
            setMessage('Poprawnie wylogowano'); // Ustaw wiadomość o sukcesie
            setTimeout(() => navigate('/login'), 1000); // Przekieruj na stronę logowania po 1 sekundzie
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

