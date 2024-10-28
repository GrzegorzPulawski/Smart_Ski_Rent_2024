import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import connection from "../../axios";
import classes from "./Login.module.css";


const Login = () => {
    const [appUserName, setAppUserName] = useState('');
    const [password, setPassword] = useState('');
    const [error, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await connection.post('/api/appusers/login', {
                appUserName,
                password,
            });

            const { jwt } = response.data; // Extract the token from response
            localStorage.setItem('token', jwt); // Store token in localStorage

            // Decode the JWT token to get the appUserName (or use appUserName from state if it's the same)
            const decodedToken = JSON.parse(atob(token.split('.')[1])); // Decode the JWT payload
            const appUserNameFromToken = decodedToken.sub; // Assuming 'sub' contains the username
            const userRole = decodedToken.role; // Extract user role if needed
            localStorage.setItem('role', userRole); // Store the role in localStorage

            // Call getCompanyData using the appUserName (either from token or from input)
            await getCompanyData(appUserNameFromToken || appUserName);

            // Navigate to a protected route after successful login
            setSuccessMessage("Login successful! Redirecting...");
            navigate('/dashboard');
        } catch (error) {
            setErrorMessage('Login failed: ' + (error.response?.data?.message || error.message));
        }
    };

    const getCompanyData = async (loginUser) => {
        try {
            const response = await connection.get(`/api/company/data/${loginUser}`);
            console.log("Company data retrieved:", response.data);

            // Store the company data in localStorage or use it elsewhere
            localStorage.setItem('companyData', JSON.stringify(response.data));
        } catch (err) {
            console.error("Error retrieving company data:", err);
            setErrorMessage("Could not retrieve company data.");
        }
    };
    return (
        <>
            <form onSubmit={handleLogin} className={classes.LoginForm}>
                <input
                    type="text"
                    value={appUserName}
                    onChange={(e) => setAppUserName(e.target.value)}
                    placeholder="Nazwa użytkownika"
                    required
                    className="InputField"
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Hasło"
                    required
                    className="InputField"
                />
                <button type="submit" className={classes.Button}>Zaloguj się</button>
                {error && <p className="ErrorText">{error}</p>}
                {successMessage && <p className="SuccessText">{successMessage}</p>}

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
            </form>

            <footer className={classes.Footer}>
                Program napisała firma Mandragora. Kontakt w celu zakupu: tel.502109609
            </footer>
        </>
    );
}

export default Login;