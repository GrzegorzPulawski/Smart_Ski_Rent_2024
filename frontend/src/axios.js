import axios from "axios";

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';
const connection = axios.create({
    baseURL: apiUrl
});

// Interceptor dodający nagłówek Authorization dla Basic Auth
connection.interceptors.request.use(config => {
    // Zakodowanie danych logowania w Base64
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');

    if (username && password) {
        const encodedCredentials = btoa(`${username}:${password}`);
        config.headers['Authorization'] = `Basic ${encodedCredentials}`;
    }

    return config;
}, error => {
    return Promise.reject(error);
});



export default connection;