import { useNavigate } from 'react-router-dom';
import classes from "./Login.module.css";
import React from "react";
import connection from "../../axios";
import {useState} from "react";

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const loginHandle = async (e) => {
        e.preventDefault(); // Zatrzymaj domyślne zachowanie formularza

        const authString = `Basic ${btoa(`${username}:${password}`)}`;

        try {
            const response = await connection.post('/login', {}, {
                headers: {
                    'Authorization': authString
                }
            });

            // Przechowuj token lub inne dane użytkownika (możesz użyć localStorage)
            console.log('Logged in successfully:', response.data);
            // Możesz również przekierować użytkownika do innej strony
            navigate('/home'); // Zmień na odpowiednią stronę po zalogowaniu
        } catch (error) {
            setError('Error logging in: ' + (error.response ? error.response.data : error.message));
            console.error('Error logging in:', error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className={classes.LoginContainer}>
            <form onSubmit={loginHandle}>
                <div>
                    <label htmlFor="username">Nazwa użytkownika:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">Hasło:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className={classes.LoginButton}>Zaloguj się</button>
                {error && <p className={classes.Error}>{error}</p>}
            </form>

            <div className="ActionButtons">
                <button
                    type="button"
                    onClick={() => navigate('/companySave')}
                    className={classes.ActionButton}
                >
                    Wprowadź dane firmy
                </button>
                <button
                    type="button"
                    onClick={() => navigate('/logout')}
                    className={classes.ActionButton}
                >
                    Wylogowanie
                </button>
            </div>
            <footer className={classes.Footer}>
                Program napisała firma Mandragora. Kontakt w celu zakupu: tel.502109609
            </footer>
        </div>
    );
};

export default Login;