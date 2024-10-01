import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom';
import connection from "../../axios";

const Login = () => {
    const [appUserName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        const credentials = { appUserName, password };

        connection().post("http://localhost:8080/api/appuser/login", credentials)
            .then(response => {
                console.log("Zalogowano pomyślnie:", response.data);
                localStorage.setItem('token', response.data.token); // Zapisz token w localStorage
                navigate("/")  // Przekieruj na stronę Home po udanym logowaniu
            })
            .catch(err=> {
                console.error("Błąd logowania:", err);
                if (err.response && err.response.status === 401) {
                    setError("Błędna nazwa użytkownika lub hasło."); // Komunikat o błędzie
                } else {
                    setError("Wystąpił błąd. Spróbuj ponownie."); // Ogólny komunikat o błędzie
                }
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={appUserName} onChange={(e) => setUsername(e.target.value)} placeholder="Nazwa użytkownika" required />
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Hasło" required />
            <button type="submit">Zaloguj się</button>
            {error && <p style={{ color: 'red' }}>{error}</p>} {/* Wyświetlenie komunikatu o błędzie */}

        </form>
    );
};

export default Login;
