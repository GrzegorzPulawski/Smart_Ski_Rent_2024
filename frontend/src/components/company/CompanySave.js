import React, { useState } from 'react';
import connection from "../../axios";

const CompanyForm = () => {
    const [companyName, setCompanyName] = useState('');
    const [companyNIP, setCompanyNIP] = useState('');
    const [nameUser, setNameUser] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await connection.post('/api/company/save', {
                companyName,
                companyNIP,
                nameUser,
            });

            // Zaktualizuj stan sukcesu
            setSuccess('Dane firmy zostały zapisane pomyślnie!');
            setError('');
        } catch (error) {
            setError('Wystąpił błąd. Użytkownik o imieniu '+nameUser+' już istnieje w bazie danych');
            setSuccess('');
        }
    };

    return (
        <div>
            <h2>Dodaj Dane Firmy</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="companyName">Nazwa Firmy:</label>
                    <input
                        type="text"
                        id="companyName"
                        value={companyName}
                        onChange={(e) => setCompanyName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="companyNIP">NIP Firmy:</label>
                    <input
                        type="number"
                        id="companyNIP"
                        value={companyNIP}
                        onChange={(e) => setCompanyNIP(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="companyNIP">Login użytkownika:</label>
                    <input
                        type="text"
                        id="nameUser"
                        value={nameUser}
                        onChange={(e) => setNameUser(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Zapisz</button>
            </form>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {success && <p style={{ color: 'green' }}>{success}</p>}
        </div>
    );
};

export default CompanyForm;