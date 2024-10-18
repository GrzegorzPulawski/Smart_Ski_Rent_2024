import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import classes from './Login.module.css'; // Importuj klasy stylów
import { Col, Button } from 'react-bootstrap';
import connection from "../../axios";


const Login = () => {
    const [appUserName, setAppUserName] = useState('');
    const [password, setPassword] = useState('');
    const [nameUserCompany, setNameUserCompany] = useState(''); // Nowe pole
    const [error, setError] = useState('');
    const [successMessage, setSuccesMessage] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        // Przechowywanie danych logowania w localStorage
        localStorage.setItem('password', password);
        localStorage.setItem('nameUser', nameUserCompany);

        connection.post("/api/appusers/login", { appUserName, password })
            .then(response => {
                console.log("Logowanie udane:", response);

                const token = response.data.token; // Pobranie tokenu z odpowiedzi
                if (token) {
                    localStorage.setItem('token', token); // Zapis tokenu w localStorage
                }

                setSuccesMessage("Zalogowano poprawnie");

                getCompanyData(nameUserCompany);

                setTimeout(() => {
                    navigate("/"); // Przekierowanie na stronę główną
                }, 3000);
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

    const getCompanyData = (nameUserCompany) => {
        axios.get(`/api/company/findByUser`, { params: { nameUserCompany } })
            .then(response => {
                console.log("Dane firmy pobrane:", response.data);
                localStorage.setItem('companyData', JSON.stringify(response.data));
            })
            .catch(err => {
                console.error("Błąd pobierania danych firmy:", err);
                setError("Wystąpił błąd podczas pobierania danych firmy.");
            });
    };

    return (
        <>
            <form onSubmit={handleSubmit} className={classes.LoginForm}>
                <input
                    type="text"
                    value={appUserName}
                    onChange={(e) => setAppUserName(e.target.value)}
                    placeholder="Nazwa użytkownika"
                    required
                    className={classes.InputField}
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Hasło"
                    required
                    className={classes.InputField}
                />
                <input
                    type="text"
                    value={nameUserCompany}
                    onChange={(e) => setNameUserCompany(e.target.value)}  // Obsługa pola nameUser
                    placeholder="Wprowadź ponownie nazwę użytkownika"
                    required
                    className={classes.InputField}
                />
                {error && <p className={classes.ErrorText}>{error}</p>}
                <button type="submit" className={classes.SubmitButton}>Zaloguj się</button>
                {error && <p className={classes.ErrorText}>{error}</p>}
                <Col>
                    <Button variant="primary" onClick={() => navigate('/companySave')} className={classes.ActionButton}>
                        Wprowadź dane firmy
                    </Button>
                </Col>
                <Col>
                    <Button variant="outline-secondary" onClick={() => navigate('/logout')} className={classes.ActionButton}>
                        Wylogowanie
                    </Button>
                </Col>
            </form>
            <div className={classes.Footer}>
                Program napisała firma Mandragora. Kontakt w celu zakupu: tel.502109609
            </div>
        </>
    );
};

export default Login;
