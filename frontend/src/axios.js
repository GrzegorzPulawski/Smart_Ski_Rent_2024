import axios from "axios";

const connection = axios.create({
    baseURL: "http://localhost:8080"
});
const addAuthToken = (config) => {
    const token = localStorage.getItem('token'); // Odczyt tokenu z localStorage
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`; // Dodaj token do nagłówka Authorization
    }
    return config; // Zwróć zmodyfikowaną konfigurację
};

// Rejestracja interceptora
connection.interceptors.request.use(addAuthToken, error => {
    return Promise.reject(error); // Obsługuje błędy w interceptorze
});


export default connection;