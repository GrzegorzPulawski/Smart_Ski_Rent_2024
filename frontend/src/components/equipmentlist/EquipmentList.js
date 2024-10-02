import classes from "./EquipmentList.module.css";
import React from "react";
import {Grid} from "@mui/material";
import connection from "../../axios.js";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

const EquipmentList = () => {
    const [nazwaZmiennej, setterDoKolekcji] = useState([]);
    const navigate = useNavigate(); // Hook do nawigacji

    useEffect(() => {
        const token = localStorage.getItem('token');
        console.log("Token:", token); // Dodaj to logowanie

        connection.get("/api/equipments")
            .then((response) => {
                if (Array.isArray(response.data)) {
                    setterDoKolekcji(response.data); // Ustaw dane, jeśli to tablica
                } else {
                    setterDoKolekcji(response.data.equipments); // Jeśli dane są w kluczu 'equipments'
                }
            })
            .catch((error) => {
                console.log("Błąd podczas pobierania danych:", error);
            });
    }, []);
    // Przekierowanie do komponentu FormEquipment
    const goToAddEquipment = () => {
        navigate("/formEquipment"); // Przekierowanie do formularza dodawania sprzętu
    };



    return (
        <div className={classes.Narty}>
            <h2>Lista sprzętu</h2>

            {/* Przycisk do przekierowania do formularza dodawania */}
            <button onClick={goToAddEquipment}>Add Equipment</button>

            <div className={classes.NartyTableHeader}>
                <div className="one">Id</div>
                <div className="two">Nazwa sprzętu</div>
                <div className="three">Cena</div>
                <div className="five"></div>
            </div>
            {nazwaZmiennej.map((value) => (
                <Grid container className={classes.NartyTableRow} key={value.idEquipment}>
                    <Grid item xs={2}>{value.idEquipment}</Grid>
                    <Grid item xs={2}>{value.nameEquipment}</Grid>
                    <Grid item xs={2}>{value.priceEquipment}</Grid>
                    <Grid item xs={2}></Grid>
                </Grid>
            ))}
        </div>
    );
}

export default EquipmentList;
