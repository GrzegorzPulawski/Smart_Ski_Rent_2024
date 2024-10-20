import axios from "axios";

const apiUrl = process.env.NODE_ENV === 'production'
    ? process.env.REACT_APP_API_URL // Heroku URL lub inny URL produkcyjny
    : 'http://localhost:8080'; // URL do lokalnego serwera backendu

const connection = axios.create({
    baseURL: apiUrl
});

// Funkcja do konfiguracji Axios z podstawowym uwierzytelnianiem
export const configureAxios = (appUserName, password) => {
    const token = btoa(`${appUserName}:${password}`);
    connection.defaults.headers.common['Authorization'] = `Basic ${token}`;
};
export const handleLogin = async (appUserName, password) => {

    try {
        configureAxios(appUserName, password);

        const response = await connection.post('/api/appusers/login');

        const role = response.data.role;  // Pobierz rolę użytkownika
        localStorage.setItem('appUserName', appUserName);
        localStorage.setItem('password', password);
        localStorage.setItem('role', response.data.role);  // Zapisz rolę użytkownika

        console.log('Zalogowano pomyślnie:', response.data);



    } catch (error) {
        console.error('Błąd logowania:', error);
        if (error.response && error.response.status === 401) {
            alert('Niepoprawne dane logowania.');
        } else {
            alert('Błąd logowania. Sprawdź swoje dane.');
        }
    }
};

export default connection;
