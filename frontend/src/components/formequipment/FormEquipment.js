import React from "react";
import classes from "./FormEquipment.module.css";
import connection from "../../axios";
import {useState} from "react";

function FormEquipment() {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [confirmationMessage, setConfirmationMessage] = useState(''); // Nowy stan dla potwierdzenia

    const submitEquipment = () => {
        console.log(name + " " + price);

        let createEquipment = {
            'nameEquipment': name,
            'priceEquipment': price
        };

        connection.post("/api/equipments", createEquipment)
            .then((response) => {
                console.log("Odpowiedź serwera:", response);
                setConfirmationMessage("Sprzęt został pomyślnie dodany!"); // Ustawiamy komunikat sukcesu
                setName(''); // Resetujemy pola po udanym dodaniu
                setPrice('');
            })
            .catch((error) => {
                console.log("Błąd:", error);
                setConfirmationMessage("Wystąpił błąd podczas dodawania sprzętu.");
            });
    };

    return (
        <div className={classes.FormNarty}>
            <div className={"container"}>
                <label>Nazwa Sprzętu</label>
                <input
                    id={'input-name'}
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <label>Cena sprzętu</label>
                <input
                    id={'input-price'}
                    type="number"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                />
            </div>
            <button className={classes.Confirm} onClick={submitEquipment}>Zatwierdź</button>
            {/* Wyświetlanie komunikatu potwierdzenia */}
            {confirmationMessage && <p>{confirmationMessage}</p>}
        </div>
    );
}

export default FormEquipment;