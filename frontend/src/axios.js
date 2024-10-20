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

export default connection;
