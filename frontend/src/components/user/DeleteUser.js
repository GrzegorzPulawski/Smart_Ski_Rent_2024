import React, { useState } from 'react';
import connection from "../../axios"; // Importowanie instancji Axios

const DeleteUser = () => {
    const [idUser, setIdUser] = useState('');  // Przechowuje ID użytkownika do usunięcia
    const [error, setError] = useState('');  // Przechowuje informacje o błędach
    const [successMessage, setSuccessMessage] = useState('');  // Przechowuje komunikaty o sukcesie

    const handleDelete = async (event) => {
        event.preventDefault();  // Zapobiega domyślnemu odświeżeniu strony

        try {
            // Wysyłanie żądania DELETE na endpoint
            await connection.delete(`/api/appuser/devel/delete/${idUser}`);

            // Ustawienie komunikatu o sukcesie, jeśli użytkownik zostanie pomyślnie usunięty
            setSuccessMessage(`User with ID ${idUser} was successfully deleted.`);
            setError('');  // Wyczyść ewentualne błędy
            setIdUser('');  // Wyczyść pole ID po udanym usunięciu
        } catch (err) {
            console.error("Błąd podczas usuwania użytkownika:", err);
            // Ustawienie komunikatu o błędzie, jeśli coś poszło nie tak
            if (err.response) {
                setError(err.response.data.message || 'Wystąpił błąd podczas usuwania użytkownika.');
            } else {
                setError('Wystąpił błąd. Spróbuj ponownie.');
            }
        }
    };

    return (
        <div>
            <h2>Usuń Użytkownika</h2>
            <form onSubmit={handleDelete}>
                <div>
                    <label>Podaj ID Użytkownika:</label>
                    <input
                        type="number"
                        value={idUser}
                        onChange={(e) => setIdUser(e.target.value)}  // Aktualizacja wartości idUser
                        required
                    />
                </div>
                <button type="submit">Usuń Użytkownika</button>
            </form>
            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}  {/* Komunikat o sukcesie */}
            {error && <p style={{ color: 'red' }}>{error}</p>}  {/* Komunikat o błędzie */}
        </div>
    );
};

export default DeleteUser;
