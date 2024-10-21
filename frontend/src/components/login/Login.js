import classes from './Login.module.css';
import React, { useState } from "react";
import {useNavigate} from "react-router-dom";
import connection from "../../axios";

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
            const response = await connection.post('/api/appusers/login', {}, {
                headers: {
                    'Authorization': `Basic ${token}`, // Add Basic Auth header
                    'Content-Type': 'application/json',
                }
            });

            // Check for successful response
            if (response.status === 200) {
                // Optionally handle any data returned, e.g., JWT token if used
                localStorage.setItem('authToken', token); // Store the token in localStorage

                // Redirect or update application state
                navigate("/dashboard"); // Redirect to the dashboard or another page
            }
        } catch (errorMessage) {
            console.error('Error during login:', errorMessage);
            if (errorMessage.response) {
                // The request was made and the server responded with a status code
                if (errorMessage.response.status === 401) {
                    setErrorMessage('Zły login lub hasło.'); // Specific message for Unauthorized
                } else {
                    setErrorMessage('Wystąpił błąd. Spróbuj ponownie'); // General message
                }
            } else {
                // The request was made but no response was received
                setErrorMessage('Błąd z połączeniem. Sprawdź internet');
            }
        }


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
                {errorMessage && <p style={{color: 'red'}}>{errorMessage}</p>}
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
};
export default Login;
