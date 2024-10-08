import React, { useState } from 'react';
import connection from "../../axios";
import {useNavigate} from "react-router-dom";
import {Col} from "react-bootstrap";


const CreateUser = () => {
    const [appUserName, setAppUserName] = useState('');
    const [password, setPassword] = useState('');
    const [roleUser, setRoleUser] = useState(''); // Może lepiej zrobić z tego pole select
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage ]= useState('');

       let navigate = useNavigate();
        const handleDeleteClick = ()=>{
            navigate('/deleteUser');
    }
    const handleSubmit = async (event) => {
        event.preventDefault();

        const appUser = {
            appUserName,
            password,
            roleUser,
        };
        try {
            const response = await connection.post('/api/appuser/devel/register', appUser);
            setSuccessMessage('Zarejestrowano użytkownika pomyślnie');
            setError(''); // Wyczyść ewentualne błędy
        } catch (err) {
            console.error("Błąd rejestracji:", err);

            // Sprawdzenie, czy odpowiedź błędu istnieje i posiada dane
            if (err.response && err.response.data) {
                // Wyciągnij komunikat o błędzie
                setError(err.response.data.message || 'Wystąpił błąd podczas rejestracji.');
            } else {
                setError('Wystąpił błąd. Spróbuj ponownie.');
            }
        }
    }

        return (
            <div>
                <h2>Rejestracja Użytkownika</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Nadaj Nazwę użytkownika:</label>
                        <input
                            type="text"
                            value={appUserName}
                            onChange={(e) => setAppUserName(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>Nadaj Hasło:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>Nadaj Rolę Użytkownika:</label>
                        <input
                            value={roleUser}
                            onChange={(e) => setRoleUser(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Załóż konto</button>
                    <Col>
                        <button type="submit" onClick={handleDeleteClick}>
                            Usuń konto
                        </button>
                    </Col>
                </form>
                {successMessage && <p style={{color: 'green'}}>{successMessage}</p>}
                {error && <p style={{color: 'red'}}>{error}</p>}
            </div>
        );
    };

export default CreateUser;
