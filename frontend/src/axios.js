import axios from "axios";

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';
const connection = axios.create({
    baseURL: apiUrl
});
console.log('NODE_ENV:', process.env.NODE_ENV);
console.log('API_URL:', process.env.REACT_APP_API_URL);


export default connection;
