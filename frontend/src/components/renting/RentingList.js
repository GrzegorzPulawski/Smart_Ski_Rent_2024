import React, {useEffect, useState} from "react";
import {Grid, Checkbox} from "@mui/material";
import classes from "./RentingList.module.css"
import connection from "../../axios";
import moment from "moment";
import ReturnRenting from "./ReturnRenting";
import {Alert} from "react-bootstrap";


const RentingList = () => {
    const [listRenting, setRentingList] = useState([]);
    const [selectedRentings, setSelectedRentings] = useState([])
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");


    useEffect(()=>{
        connection.get("/api/rentings")
            .then((response)=>{
                console.log(response);
                setRentingList(response.data)
            })
            .catch((error)=>{
                console.log(error);
            });
    },[]);
    // Handle selecting/unselecting rentals
    const handleCheckboxChange = (idRenting) => {
        setSelectedRentings((prevSelected) => {
            if (prevSelected.includes(idRenting)) {
                return prevSelected.filter(id => id !== idRenting); // Unselect
            } else {
                return [...prevSelected, idRenting]; // Select
            }
        });
    };

    return(
        <div>
            <Grid container  className={classes.TableHeader}>
                <Grid item xs={1} className={classes.HeaderCell}>Select</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Id</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Nazwisko</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Data wypożyczenia</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Sprzęt</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Data zwrotu</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Cena całkowita</Grid>
                <Grid item xs={1} className={classes.HeaderCell}>Ilość dni</Grid>
            </Grid>
            {
                listRenting.map(value => {
                    //Formatuję datę wypozyczenia
                    var dataWypo = value.dateRenting;
                    var dateRentingFormat = moment(dataWypo).format('DD/MM/YY HH:mm'); // Corrected to 'mm' for minutes

                    // Formatuję datę zwrotu
                    var dataZwro = value.dateOfReturn;
                    var dateOfReturnFormat = moment(dataZwro).format('DD/MM/YY HH:mm'); // Corrected to 'mm' for minutes


                    return (<Grid container  className={classes.TableRow} key={value.idRenting}>
                            <Grid item xs={1}>
                                <Checkbox
                                    checked={selectedRentings.includes(value.idRenting)}
                                    onChange={() => handleCheckboxChange(value.idRenting)}
                                />
                            </Grid>
                        <Grid item xs={1}className={classes.RowCell}>{value.idRenting}</Grid>
                        <Grid item xs={1}className={classes.RowCell}>{value.lastName}</Grid>
                        <Grid item xs={1}className={classes.RowCell}>{dateRentingFormat}</Grid>
                        <Grid item xs={1}className={classes.RowCell}>{value.nameEquipment}</Grid>
                        <Grid item xs={1}className={classes.RowCell}>{dateOfReturnFormat}</Grid>
                        <Grid item xs={1}className={classes.RowCell}>{value.priceOfDuration}</Grid>
                        <Grid item xs={1}className={classes.RowCell}>{value.daysOfRental}</Grid>
                    </Grid>)
                })
            }
            {successMessage && <Alert variant="success" className="mt-3">{successMessage}</Alert>}
            {errorMessage && <Alert variant="danger" className="mt-3">{errorMessage}</Alert>}
            <ReturnRenting selectedRentings={selectedRentings} setSuccessMessage={setSuccessMessage} setErrorMessage={setErrorMessage} />


        </div>
    );
}
export default RentingList;