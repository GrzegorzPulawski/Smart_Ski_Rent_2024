import React, { useState } from "react";
import connection from "../../axios";

const DeleteClient = () => {
    const [idClient, setIdClient] = useState('');
    const [message, setMessage] = useState('');

    const handleDelete = () => {
        connection.delete(`/api/clients/delete`, { params: { idClient } })
            .then((response) => {
                setMessage(`Klient o ID ${idClient} został usunięty.`);
                console.log("Klient usunięty:", response.data);
            })
            .catch((error) => {
                console.error("Błąd usuwania klienta:", error);
                setMessage('Wystąpił błąd przy usuwaniu klienta.');
            });
    };

    return (
        <div>
            <h2>Usuń klienta</h2>
            <div>
                <label>Podaj ID</label>
            <input
                type="number"
                value={idClient}
                onChange={(e) => setIdClient(e.target.value)}
                placeholder="Wprowadź ID klienta"
            />
        </div>
            <button onClick={handleDelete}>Usuń klienta</button>

            {message && <p>{message}</p>}
        </div>
    );
};

export default DeleteClient;
