import React, {useEffect, useState} from "react";
import {Grid, Checkbox} from "@mui/material";
import classes from "./RentingList.module.css"
import connection from "../../axios";
import moment from "moment";
import ReturnRenting from "./ReturnRenting";
import {Alert, Button} from "react-bootstrap";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";


const RentingList = () => {
    const [listRenting, setRentingList] = useState([]);
    const [selectedRentings, setSelectedRentings] = useState([])
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [nameUserCompany, setNameUserCompany] = useState("");
    const [companyData, setCompanyData]= useState('');

    const navigate = useNavigate();

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
    // Potwierdzenie i przekierowanie do umowy
    const handleConfirmSelection = () => {
        if (selectedRentings.length > 0) {
            // Przekierowanie do komponentu RentalAgreement z ID umowy
            navigate("/rentalAgreement", { state: { rentingId: selectedRentings[0] } }); // Zakładam, że wybierasz tylko jedną umowę
        } else {
            setErrorMessage("Proszę zaznaczyć co najmniej jedną umowę.");
        }
    };
    const getCompanyData = (nameUserCompany) => {
        axios.get(`/api/company/findByUser`, { params: { nameUserCompany } })
            .then(response => {
                console.log("Dane firmy pobrane:", response.data);
                localStorage.setItem('companyData', JSON.stringify(response.data));
                setCompanyData(response.data); // Aktualizacja stanu
            })
            .catch(err => {
                console.error("Błąd pobierania danych firmy:", err);
                setErrorMessage("Wystąpił błąd podczas pobierania danych firmy.");
            });
    };

    return(
        <div>
            <div className={classes.ButtonContainer}>
                <Button
                    variant="primary" // Change the variant for a different color
                    onClick={handleConfirmSelection}
                    disabled={selectedRentings.length === 0}
                    className={`btn-lg ${classes.CustomButton}`} // Adding custom classes
                >Wydrukuj umowę wypożyczenia
                </Button>
            <ReturnRenting
                selectedRentings={selectedRentings}
                setSuccessMessage={setSuccessMessage}
                setErrorMessage={setErrorMessage}
            />
                {successMessage && <Alert variant="success" className="mt-3">{successMessage}</Alert>}
                {errorMessage && <Alert variant="danger" className="mt-3">{errorMessage}</Alert>}
            </div>
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


                    return (<Grid container className={classes.TableRow} key={value.idRenting}>
                            <Grid item xs={1}>
                                <Checkbox
                                    checked={selectedRentings.includes(value.idRenting)}
                                    onChange={() => handleCheckboxChange(value.idRenting)}
                                />
                            </Grid>
                            <Grid item xs={1} className={classes.RowCell}>{value.idRenting}</Grid>
                            <Grid item xs={1} className={classes.RowCell}>{value.lastName}</Grid>
                            <Grid item xs={1} className={classes.RowCell}>{dateRentingFormat}</Grid>
                            <Grid item xs={1} className={classes.RowCell}>{value.nameEquipment}</Grid>
                            <Grid item xs={1} className={classes.RowCell}>{dateOfReturnFormat}</Grid>
                            <Grid item xs={1} className={classes.RowCell}>{value.priceOfDuration}</Grid>
                            <Grid item xs={1} className={classes.RowCell}>{value.daysOfRental}</Grid>
                        </Grid>
                    );
                })}

            <form onSubmit={(e) => { e.preventDefault(); getCompanyData(nameUserCompany); }} className={classes.LoginForm}>
                <input
                    type="text"
                    value={nameUserCompany}
                    onChange={(e) => setNameUserCompany(e.target.value)}  // Obsługa pola nameUser
                    placeholder="Wprowadź ponownie nazwę użytkownika"
                    required
                    className={classes.InputField}
                />
                    {errorMessage && <p className={classes.ErrorText}>{errorMessage}</p>}
                    <button type="submit" className={classes.SubmitButton}>Potwierdż</button>
                    {errorMessage && <p className={classes.ErrorText}>{errorMessage}</p>}
                    </form>
        </div>
    );
}
export default RentingList;