import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom';
import connection from "../../axios";

const Login = () => {
    const [appUserName, setAppUserName] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        // Przechowywanie danych logowania w localStorage
        localStorage.setItem('username', appUserName);
        localStorage.setItem('password', password);

        connection.post("/api/appuser/login", { appUserName, password })
            .then(response => {
                console.log("Logowanie udane:", response);
                navigate("/");  // Przekieruj na stronę Home po udanym logowaniu
            })
            .catch(err => {
                console.error("Błąd logowania:", err);
                if (err.response && err.response.status === 401) {
                    setError("Błędna nazwa użytkownika lub hasło.");
                } else {
                    setError("Wystąpił błąd. Spróbuj ponownie.");
                }
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={appUserName}
                onChange={(e) => setAppUserName(e.target.value)}
                placeholder="Nazwa użytkownika"
                required
            />
            <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Hasło"
                required
            />
            <button type="submit">Zaloguj się</button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </form>
    );
};
export default Login;