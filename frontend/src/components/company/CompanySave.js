import React, { useState } from 'react';
import connection from "../../axios";
import classes from "./CompanySave.module.css";

const CompanySave = () => {
    const [companyName, setCompanyName] = useState('');
    const [companyNIP, setCompanyNIP] = useState('');
    const [nameUserCompany, setNameUserCompany]  = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await connection.post('/api/company/save', {
                companyName,
                companyNIP,
                nameUserCompany,
            });

            // Zaktualizuj stan sukcesu
            setSuccess('Dane firmy zostały zapisane pomyślnie!');
            setError('');
        } catch (error) {
            setError('Wystąpił błąd. Firma o nazwie '+companyName+' już istnieje w bazie danych');
            setSuccess('');
        }
    };

    return (
        <div className={classes.CompanyForm}>
            <h2>Dodaj Dane Firmy</h2>
            <form onSubmit={handleSubmit} className={classes.Form}>
                <div className={classes.FormGroup}>
                    <label htmlFor="companyName">Nazwa Firmy:</label>
                    <input
                        type="text"
                        id="companyName"
                        value={companyName}
                        onChange={(e) => setCompanyName(e.target.value)}
                        className={classes.Input}
                        required
                    />
                </div>
                <div className={classes.FormGroup}>
                    <label htmlFor="companyNIP">NIP firmy</label>
                    <input
                        type="number"
                        id="companyNIP"
                        value={companyNIP}
                        onChange={(e) => setCompanyNIP(e.target.value)}
                        className={classes.Input}
                        required
                    />
                </div>
                <div className={classes.FormGroup}>
                    <label htmlFor="companyNIP">Login</label>
                    <input
                        type="text"
                        id="nameUserCompany"
                        value={nameUserCompany}
                        onChange={(e) => setNameUserCompany(e.target.value)}
                        className={classes.Input}
                        required
                    />
                </div>
                <button type="submit" className={classes.Button}>Zapisz</button>
            </form>
            {error && <p className={classes.Error}>{error}</p>}
            {success && <p className={classes.Success}>{success}</p>}
        </div>
    );
};

export default CompanySave;