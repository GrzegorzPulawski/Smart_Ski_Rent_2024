import classes from './Login.module.css';
import React, { useState } from "react";
import connection, { configureAxios } from "../../axios";

import {useNavigate} from "react-router-dom";

const Login = () => {
    const [appUserName, setAppUserName] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault(); // Zatrzymanie domyślnej akcji formularza

        configureAxios(appUserName, password); // Konfiguruj Axios

        try {
            const response = await connection.post('/api/appusers/login');

            // Zapisz dane logowania w localStorage
            localStorage.setItem('appUserName', appUserName);
            localStorage.setItem('role', response.data.role); // Zapisz rolę użytkownika

            console.log('Zalogowano pomyślnie:', response.data);
            // Przekierowanie po udanym logowaniu
            window.location.href = "/"; // lub inna strona
        } catch (error) {
            console.error('Błąd logowania:', error);
            setErrorMessage("Niepoprawne dane logowania.");
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
