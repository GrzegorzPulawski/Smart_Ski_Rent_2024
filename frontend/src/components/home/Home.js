import React from "react";
import {Card, Col, Row} from "react-bootstrap";
import styles from "./Home.module.css";

function Home() {
    return(
        <>        <div className={styles.ImageContainer}>
            <img src="https://picsum.photos/id/1036/1200/800" alt="Main" />
        </div>
        <Row className={styles.Row}>
            <Col className={styles.Col}>
                <Card className={styles.Card}>

                    <Card.Header className={styles.CardHeader}>Instrukcja - Smart Ski Rent</Card.Header>
                    <Card.Body>
                        <Card.Title className={styles.CardTitle}>
                            Login
                        </Card.Title>
                        <Card.Text className={styles.CardText}>
                            Logujesz się przy pomocy nazwy i hasła. Zwróć uwagę na wielkość liter.
                            Login administratora pozwala dodawać nowy sprzęt do bazy i go usuwać. Login Usera nie ma tych uprawnień
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className={styles.CardFooter}> Ważne: Wprować dane firmy,
                        które będą przypisane do Twojego loginu i będą drukować się na umowie.
                        Jak masz dwa loginy lub wiecej to dla każdego musisz wprowadzć dane firmy </Card.Footer>
                </Card>
            </Col>
            <Col className={styles.Col}>
                <Card className={styles.Card}>
                    <Card.Header className={styles.CardHeader}>Instrukcja - Smart Ski Rent </Card.Header>
                    <Card.Body>
                        <Card.Title className={styles.CardTitle}>
                            Klient
                        </Card.Title>
                        <Card.Text className={styles.CardText}>
                           Nowo dodany klient będzie Ci się wyświetlał na początku listy.
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className={styles.CardFooter}> Ważne: Pamiętaj o zapisaniu numeru telefonu.
                        Będzie on potrzebny gdybyś chciał skorzystać z wyszukiwarki klientów,
                        ponieważ wyszukujemy po nr telefonu.</Card.Footer>
                </Card>
            </Col>
            <Col className={styles.Col}>
                <Card className={styles.Card}>
                    <Card.Header className={styles.CardHeader}>Instrukcja - Smart Ski Rent  </Card.Header>
                    <Card.Body>
                        <Card.Title className={styles.CardTitle}>
                            Wypożycz
                        </Card.Title>
                        <Card.Text className={styles.CardText}>
                           Wybierz klienta z rozwijanej listy. Nowo dodany klient będzie na samej górze.
                            Zaznacz sprzęt który chcesz wypożyczyć i kliknij utwórz wypożyczenie.
                            Program po 3 sekundach przeniesie Cię do listy wypożyczeń.

                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className={styles.CardFooter}> Wskazówka: Możesz jednocześnie zaznaczyć dowolną ilość sprzętu który chcesz wypożyczyć </Card.Footer>
                </Card>
            </Col>
            <div className={styles.ImageContainer}>
                <img src="https://picsum.photos/id/177/1200/800/" alt="Main"/>
            </div>
            <Col className={styles.Col}>
                <Card className={styles.Card}>
                    <Card.Header className={styles.CardHeader}>Instrukcja - Smart Ski Rent  </Card.Header>
                    <Card.Body>
                        <Card.Title className={styles.CardTitle}>
                          Lista wypożyczeń
                        </Card.Title>
                        <Card.Text className={styles.CardText}>
                           Zaznacz wypożyczenie na podstawie którego wydrukujesz umowę z klientem.
                            Najnowsza będzie na samej górze. Po zaznaczeniu wciśnij Wyrukuj Umowę. Kasujesz za wypożyczenie przy zwrocie.
                            Tutaj też zrobisz zwrot wypożyczenia. Zaznacz wypożyczenie do zwrotu.
                            Naciśnij Zatwierdź Zwroty. Znajdź ponownie na liście zakończone wypożyczenie i tam będą dostępne informacje:
                            Data Zwrot, Kwota Do Zapłaty, Ilość Dni Wypożyczenia.
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className={styles.CardFooter}>Wskazówka: możesz zrobić zwrot wielu zaznaczonych wypożyczeń jednocześnie.
                        Wydruk umowy tylko po jednej na raz</Card.Footer>
                </Card>
            </Col>
            <Col className={styles.Col}>
                <Card className={styles.Card}>
                    <Card.Header className={styles.CardHeader}>Instrukcja - Smart Ski Rent </Card.Header>
                    <Card.Body>
                        <Card.Title className={styles.CardTitle}>
                          Sprzęt
                        </Card.Title>
                        <Card.Text className={styles.CardText}>
                           Dodawać i usuwać sprzę może tylko użytkownik z uprawnieniami ADMIN.
                            Logika wypożyczenie polega na wielokrotnym wypożyczeniu tego samego zestawu.
                            Tak powiniśmy tworzyć nazwy zestawu aby było wiadomo o co chodzi, np. Buty snowbordowe lub Narty dorosły.
                            Cena może zawierać grosze, np. 45,50.
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className={styles.CardFooter}>Wskazówka: Stwórz Narty dorosły1, Narty dorosły2, itd.
                        Myślę że do 4, aby do jednego klienta jednorazowo, tworząc umowę, przypisać 4 komplety</Card.Footer>
                </Card>
            </Col>
            <Col className={styles.Col}>
                <Card className={styles.Card}>
                    <Card.Header className={styles.CardHeader}>Instrukcja - Smart Ski Rent</Card.Header>
                    <Card.Body>
                        <Card.Title className={styles.CardTitle}>
                            Raporty
                        </Card.Title>
                        <Card.Text className={styles.CardText}>
                           Dostępny jest raport dzienny przychodów. Pamiętaj, że klient płaci, gdy zdaje sprzęt.
                            Wprowadż datę, dla której system ma wygenerować raport.
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className={styles.CardFooter}>Informacja: System nalicza pierwszą dobę po stworzeniu wypożyczenia, kolejną dobę po 24 godzinach od wypożyczenia.
                        Na przykład za 25 godzin wypożyczenia zapłacisz za dwie doby.
                        </Card.Footer>
                </Card>
            </Col>
        </Row>

        </>

    )
}
export default Home;