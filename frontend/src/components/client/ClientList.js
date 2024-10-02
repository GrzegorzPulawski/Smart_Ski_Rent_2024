import connection from "../../axios";
import {useState, useEffect} from "react";
import {Col, Container, Row} from "react-bootstrap";
import React from "react";

const ClientList =  () => {
    const [clientList, setterClientList] = useState([]);

    useEffect(() =>{
        connection.get("/api/clients")
            .then((response)=>{
                console.log(response);
                setterClientList(response.data)
            })
            .catch((error)=>{
                console.error("Błąd podczas pobierania klientów",error);
            });
    },[]);

    return(
        <div>
            <h2>Lista klientów</h2>

            <Container className={classes.FormRow}>
                <Row>
                    <Col>Id</Col>
                    <Col>Imię</Col>
                    <Col>Nazwisko</Col>
                    <Col>Numer dowodu</Col>
                    <Col>Numer telefonu</Col>
                </Row>
            </Container>
            {clientList.map(value => (
                <Container key={value.idClient} className={classes.FormRow}>
                    <Row>
                        <Col md={3}>{value.idClient}</Col>
                        <Col md={3}>{value.firstName}</Col>
                        <Col md={3}>{value.lastName}</Col>
                        <Col md={3}>{value.identityCard}</Col>
                        <Col md={3}>{value.phoneNumber}</Col>
                    </Row>
                </Container>
                ))}
        </div>
    );
}
export default ClientList;