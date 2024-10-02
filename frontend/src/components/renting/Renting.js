import React, { useEffect, useState } from "react";
import classes from "../renting/Renting.module.css";
import { Button, Col, Container, Row } from "react-bootstrap";
import connection from "../../axios";


function Renting() {
    const [clients, setClients] = useState([]);
    const [equipment, setEquipment] = useState([]);
    const [selectedClient, setSelectedClient] = useState("");
    const [selectedEquipment, setSelectedEquipment] = useState([]);
    const[confirmationMessage, setConfiramtionMessage] = useState('');

    useEffect(() => {
        // Pobierz listę klientów
        connection.get('/api/clients')
            .then(response => setClients(response.data))
            .catch(error => console.error("Error fetching clients:", error));

        // Pobierz listę sprzętu
        connection.get('/api/equipments') // Zaktualizuj to na odpowiedni endpoint
            .then(response => setEquipment(response.data))
            .catch(error => console.error("Error fetching equipment:", error));
    }, []);

    const submit = () => {
        const createRenting = {

            idClient: selectedClient, // tutaj powinno być id klienta
            idEquipment: selectedEquipment // tutaj powinno być id sprzętu
        };

        connection.post('/api/rentings', createRenting)
            .then(response => {
                console.log(response);
                setConfiramtionMessage ("Utworzono wypożyczenie!");
                // Clear the selected client and equipment
                setSelectedClient("");
                setSelectedEquipment([]);
            })
            .catch(error => {
                console.log(error);
                setConfiramtionMessage ("Wystąpił błąd podczas tworzenia wypożyczenia!");
            });
    };
    const handleCheckboxChange = (id) => {
        setSelectedEquipment(prevSelected => {
            if (prevSelected.includes(id)) {
                // If already selected, remove it
                return prevSelected.filter(equipmentId => equipmentId !== id);
            } else {
                // Otherwise, add it to the selected array
                return [...prevSelected, id];
            }
        });
    };

    return (
        <div>
            <Container className={classes.Form}>
                <Row>
                    <Col className={classes.FormRow}>
                        <label className={'form-input-label'}>Wybierz klienta:</label>
                        <select value={selectedClient} onChange={(e) => setSelectedClient(e.target.value)} className={'form-input-field'}>
                            <option value="">Wybierz klienta</option>
                            {clients.map(client => (
                                <option key={client.idClient} value={client.idClient}>
                                    {client.lastName} {client.firstName}
                                </option>
                            ))}
                        </select>
                    </Col>
                    <Col className={classes.FormRow}>
                        <label className={'form-input-label'}>Wybierz sprzęt:</label>
                        <div className={classes.EquipmentList}>
                            {equipment.map(equip => (
                                <div key={equip.idEquipment}>
                                    <input
                                        type="checkbox"
                                        id={`equip-${equip.idEquipment}`}
                                        checked={selectedEquipment.includes(equip.idEquipment)}
                                        onChange={() => handleCheckboxChange(equip.idEquipment)}
                                    />
                                    <label htmlFor={`equip-${equip.idEquipment}`}>{equip.nameEquipment}</label>
                                </div>
                            ))}
                        </div>
                    </Col>
                </Row>

                <Row className={classes.Button}>
                    <Button variant={"light"} onClick={submit}>Zatwierdź zmiany</Button>
                    {confirmationMessage && <p>{confirmationMessage}</p>}
                </Row>
            </Container>
        </div>
    );
}

export default Renting;
