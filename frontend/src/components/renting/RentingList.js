import React, {useEffect, useState} from "react";
import {Grid} from "@mui/material";
import classes from "./RentingList.module.css"
import {Button} from "react-bootstrap";
import connection from "../../axios";
import moment from "momment";


const RentingList = () => {
    const [listRenting, setRentingList] = useState([]);

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

    return(
        <div>

            <Grid container  className={classes.TableHeader}>
                <Grid item xs={1}>Id</Grid>
                <Grid item xs={2}>Data wypożyczenia</Grid>
                <Grid item xs={2}>Data zwrotu</Grid>
                <Grid item xs={2}>Cena całkowita</Grid>
                <Grid item xs={2}>Ilość dni</Grid>
            </Grid>
            {
                listRenting.map(value => {
                    //Formatuję datę wypozyczenia
                    var dataWypo=value.dateRenting
                    var dateRentingFormat=moment(dataWypo).format('DD/MM/YY HH:MM')
                    //Formatuję datę zwrotu
                    var dataZwro=value.dateOfReturn
                    var dateOfReturnFormat=moment(dataZwro).format('DD/MM/YY HH:MM')

                    return (<Grid container className={classes.TableRow}>
                        <Grid item xs={1}>{value.idRenting}</Grid>
                        <Grid item xs={2}>{dateRentingFormat}</Grid>
                        <Grid item xs={2}>{dateOfReturnFormat}</Grid>
                        <Grid item xs={2}>{value.priceOfDuration}</Grid>
                        <Grid item xs={2}>{value.daysOfRental}</Grid>
                    </Grid>)
                })
            }
            <div>
                <Button  onClick={window.print}>drukuj</Button>
            </div>

        </div>
    );
}
export default RentingList;