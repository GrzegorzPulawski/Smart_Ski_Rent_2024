import React, { useState } from 'react';
import connection from "../../axios_helper";
import {useNavigate} from "react-router-dom";
import {Col} from "react-bootstrap";

const CreateUser = () => {
    const [appUserName, setAppUserName] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState(''); // Może lepiej zrobić z tego pole select
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage ]= useState('');


       let navigate = useNavigate();
        const handleDeleteClick = ()=>{
            navigate('/deleteUser');
    }
        const handleListUserClick = ()=>{
            navigate('/userList')
        }

    const handleSubmit = async (event) => {
        event.preventDefault();

        const appUser = {
            appUserName,
            password,
            role,
        };
        try {
            const response = await connection.post('/api/appusers/devel/register', appUser);
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
                <h2>Rejestracja Użytkownika - opcja dostępna tylko dla administratora</h2>
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
                            value={role}
                            onChange={(e) => setRole(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Załóż konto</button>
                    <Col>
                        <button type="submit" onClick={handleDeleteClick}>
                            Usuń konto
                        </button>
                    </Col>
                    <Col>
                        <button type="submit" onClick={handleListUserClick}>
                         Pokaż użytkowników
                        </button>
                    </Col>
                </form>
                {successMessage && <p style={{color: 'green'}}>{"Założono konto poprawnie"}</p>}
                {error && <p style={{color: 'red'}}>{"Nie masz uprawnień"}</p>}
            </div>
        );
    };

export default CreateUser;
