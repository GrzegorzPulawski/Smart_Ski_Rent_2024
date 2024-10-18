
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import connection from "../../axios";

const LogoutButton = () => {
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            // Wywołanie endpointu wylogowania na backendzie
            await connection.post('/api/appusers/logout', {}, {
                auth: {
                    username: localStorage.getItem('username'),
                    password: localStorage.getItem('password')
                }
            });

            // Usuwanie danych uwierzytelniających z localStorage po poprawnym wylogowaniu
            localStorage.removeItem('username');
            localStorage.removeItem('password');
            localStorage.removeItem('token');
            setMessage("Poprawnie wylogowano.");

            // Opóźnienie i przekierowanie na stronę logowania
            setTimeout(() => {
                navigate("/login"); // Przekierowanie na stronę logowania
            }, 2000);
        } catch (error) {
            setMessage("Wystąpił błąd podczas wylogowania.");
            console.error("Błąd wylogowania:", error);
        }
    };

    return (
        <div>
            <button onClick={handleLogout}>Potwierdź wylogowanie</button>
            {message && <p style={{ color: message === "Poprawnie wylogowano." ? 'green' : 'red' }}>{message}</p>}
        </div>
    );
};

export default LogoutButton;
