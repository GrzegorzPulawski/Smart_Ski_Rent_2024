
import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { Alert } from 'react-bootstrap'; // Assuming you're using react-bootstrap
import DisplayRentalAgreement from './DisplayRentalAgreement'; // Import your display component
import connection from "../../axios";

function RentalAgreement() {
    const [rentingShow, setRentingShow] = useState(null);
    const [errorMessage, setErrorMessage] = useState("");
    const location = useLocation(); // Hook to get the state (passed rentingId)

    // Function to fetch rental data by ID
    const fetchRentalAgreement = (rentingId) => {
        connection
            .get("/api/rentings/show", { params: { idRenting: rentingId } })
            .then((response) => {
                console.log("OK! ", response.data);
                setRentingShow(response.data); // Pass the data to the display component
                setErrorMessage(""); // Clear previous error messages
            })
            .catch((errorResponse) => {
                console.log("Error: ", errorResponse);
                setRentingShow(null); // Clear display if error
                setErrorMessage("Failed to retrieve data. Please try again."); // Set error message
            });
    };

    useEffect(() => {
        // Pobierz ID umowy z przekazanego state i pobierz dane umowy
        const { rentingId } = location.state || {}; // Destructure with fallback to prevent error
        if (rentingId) {
            fetchRentalAgreement(rentingId);

    } else {
    setErrorMessage("No rental agreement ID provided.");
    }
        }, [location.state]);

    const companyData = JSON.parse(localStorage.getItem('companyData'));


    return (
        <div>
            {/* Wyświetl dane umowy lub komunikat o błędzie */}
            {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
            {rentingShow ? (
                <DisplayRentalAgreement renting={rentingShow} />
            ) : (
                <Alert variant="info">Loading rental agreement details...</Alert>
            )}
        </div>
    );
}

export default RentalAgreement;
