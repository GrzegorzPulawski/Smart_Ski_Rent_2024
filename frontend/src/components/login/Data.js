import classes from './Login.module.css'; // Zakładam, że masz odpowiedni plik CSS
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Data = () => {
    const navigate = useNavigate();


    return (
        <div>
            <button
                onClick={() => navigate('/companySave')}
                className={classes.ActionButton}
            >
                Wprowadź dane firmy
            </button>
            <button
                onClick={() => navigate('/logout')} // Przekierowanie do komponentu wylogowania
                className={classes.ActionButton}
            >
                Wylogowanie
            </button>
            <div className={classes.Footer}>
                Program napisała firma Mandragora. Kontakt w celu zakupu: tel.502109609
            </div>
        </div>
    );
};

export default Data;

