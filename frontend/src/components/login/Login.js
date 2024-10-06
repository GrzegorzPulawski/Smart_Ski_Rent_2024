import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom';
import connection from "../../axios";
import {Button, Col} from "react-bootstrap";
import classes from "../renting/Renting.module.css";

const Login = () => {
    const [appUserName, setAppUserName] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [error, setError] = useState('')
    const[successMessage, setSuccesMessage] = useState('');


    const handleSubmit = (event) => {
        event.preventDefault();

        // Przechowywanie danych logowania w localStorage
        localStorage.setItem('username', appUserName);
        localStorage.setItem('password', password);

        connection.post("/api/appuser/login", { appUserName, password })
            .then(response => {
                console.log("Logowanie udane:", response);
                setSuccesMessage("Zalogowano poprawnie")
                getCompanyData(appUserName);
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

    const getCompanyData = (loginUser) => {
       // connection.get(`/api/company/data`, { params: { loginUser } })
        connection.get(`/api/company/data/${loginUser}`)
            .then(response => {
                console.log("Dane firmy pobrane:", response.data);

                // Przechowywanie danych firmy w localStorage lub innym miejscu
                localStorage.setItem('companyData', JSON.stringify(response.data));

            })
            .catch(err => {
                console.error("Błąd pobierania danych firmy:", err);
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
            <Col>
                <Button variant="primary" onClick={() => navigate('/companySave')} className={classes.RentingButton}>
                    Wprowdź dane firmy
                </Button>
            </Col>
        </form>
    );
};
export default Login;