import axios from "axios";

const connection = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        'Content-Type': 'application/json',
    },
});
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
