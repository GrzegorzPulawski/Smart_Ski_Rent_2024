import axios from "axios";

const apiUrl = process.env.REACT_APP_API_URL;
const connection = axios.create({
    baseURL: apiUrl
});

// Funkcja do konfiguracji Axios z podstawowym uwierzytelnianiem
export const configureAxios = (username, password) => {
    const token = btoa(`${username}:${password}`);
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
};

// Przykład użycia w logowaniu
export const handleLogin = async (appUserName, password) => {
    try {
        // Zapamiętaj dane logowania w localStorage
        localStorage.setItem('appUserName', appUserName);
        localStorage.setItem('password', password);

        // Skonfiguruj Axios z danymi logowania
        configureAxios(appUserName, password);

        // Wykonaj zapytanie do endpointu logowania
        const response = await axios.post('/api/appusers/login'); // Upewnij się, że endpoint jest prawidłowy

        // Tutaj można dodać kod obsługujący sukces logowania, np. przechowywanie tokenu lub przekierowanie
        console.log('Zalogowano pomyślnie:', response.data);
        // Przykład: navigate('/dashboard'); // Przekierowanie do innej strony

    } catch (error) {
        console.error('Błąd logowania:', error);
        // Możesz ustawić stan błędu lub pokazać komunikat dla użytkownika
        alert('Błąd logowania. Sprawdź swoje dane.');
    }
};



export default connection;