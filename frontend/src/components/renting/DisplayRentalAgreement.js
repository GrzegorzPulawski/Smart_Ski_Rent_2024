import React from "react";
import { Grid } from "@mui/material";
import moment from "moment";
import classes from "./DisplayRentalAgreement.module.css";
import {Button} from "react-bootstrap";

function DisplayRentalAgreement({ renting }) {
    // Format the renting date
    const dateRentingFormat = moment(renting.dateRenting).format('DD/MM/YY HH:mm');

    return (
        <Grid container className={classes.TableRow}>
            <Grid item xs={12} justifyContent={"center"}>
                <h4>Umowa wypożyczenia o nr: {renting.idRenting}</h4>
            </Grid>
            <Grid item xs={12}>Została zawarta z datą: {dateRentingFormat} godzina.</Grid>
            <Grid item xs={12}>Od firmy: Mandragora Grzegorz Puławski NIP 9531028160:</Grid>
            <Grid item xs={12}>Pani/Pan: {renting.firstName || "N/A"} {renting.lastName || "N/A"}</Grid>
            <Grid item xs={12}>Nr telefonu kontaktowego: {renting.phoneNumber}</Grid>
            <Grid item xs={12}>Nr dowodu: {renting.identityCard}</Grid>
            <Grid item xs={12}>Wypożycza komplet zimowy o nazwie : {renting.nameEquipment}</Grid>
            <Grid item xs={12}>W cenie za dobę: {renting.priceEquipment} zł</Grid>
            <Grid item xs={12}>
                Klient zobowiązuję się zwrócić sprzęt w stanie niepogorszonym. W przypadku uszkodzenia, kradzieży zobowiązuje się do pokrycia kosztów odtworzenia.
            </Grid>
            <Grid item xs={12} justifyContent={"flex-end"}>CZYTELNY PODPIS KLIENTA:</Grid>
            {/* Przycisk drukowania */}
            <div className={classes.ButtonPrint}>
                <Button variant="dark" onClick={() => window.print()}>
                    Drukuj umowę
                </Button>
            </div>
        </Grid>

    );
}

export default DisplayRentalAgreement;
