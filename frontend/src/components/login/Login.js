import classes from './Login.module.css';
import React, { useState } from "react";
import {useNavigate} from "react-router-dom";

const Login = () => {
    const [appUserName, setAppUserName] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setErrorMessage(null); // Reset any previous errors

        try {
            // Base64 encode the username and password
            const token = btoa(`${appUserName}:${password}`);

            // Send a request to the backend to log in
            const response = await fetch('/api/appusers/login', {
                method: 'POST',
                headers: {
                    'Authorization': `Basic ${token}`, // Add Basic Auth header
                    'Content-Type': 'application/json',
                }
            });

            // Check for success
            if (response.ok) {
                // Optionally: store the token in localStorage to keep the user logged in
                localStorage.setItem('authToken', token);

                console.log('Login successful');
                // Redirect the user or update UI (e.g., navigate to another page)
            } else {
                // Handle errors (invalid login, etc.)
                const errorMsg = await response.text();
                setErrorMessage(`Zły login: ${errorMsg}`);
            }
        } catch (err) {
            setErrorMessage('Błąd podczas logowania');
            console.error('Login error:', err);
        }
    };


    return (
        <div>
            <h2>Logowanie</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Nazwa użytkownika:</label>
                    <input
                        type="text"
                        value={appUserName}
                        onChange={(e) => setAppUserName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Hasło:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Zaloguj</button>
            </form>
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        <button
            onClick={() => navigate('/companySave')}
            className={classes.ActionButton}
        >
            Wprowadź dane firmy
        </button>
        <button
            onClick={() => navigate('/logout')} // Przekierowanie do komponentu wylogowania
            className={classes.ActionButton}
        >
            Wylogowanie
        </button>
        <div className={classes.Footer}>
            Program napisała firma Mandragora. Kontakt w celu zakupu: tel.502109609
        </div>
    </div>

    );
};

export default Login;
