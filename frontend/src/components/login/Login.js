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

    const loginHandle = async (event) => {
        event.preventDefault();

        try {
            const response = await connection.post('/api/auth/login', {username,
                password},{
                    withCredentials: true,
            headers: {
                'Content-Type': 'application/json'}
            }
            );
            console.log(response.data);
            console.log('Login data:', { username, password });

        } catch (err) {
            setError('Invalid username or password');
            console.error('Login error:', err);
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