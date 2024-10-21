import axios from "axios";

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';
const connection = axios.create({
    baseURL: apiUrl
});
console.log('NODE_ENV:', process.env.NODE_ENV);
console.log('API_URL:', process.env.REACT_APP_API_URL);

const loginUser = async (username, password) => {
    try {

        axios.defaults.headers.common['Authorization'] = 'Basic ' + btoa(`${username}:${password}`);
        const response = await axios.post('/api/appusers/login', {
            appUserName: username,
            password: password,
        });
        // Handle successful login
        console.log('Login successful:', response.data);
    } catch (error) {
        // Handle error
        if (error.response) {
            console.error('Login failed:', error.response.data);
        } else {
            console.error('An error occurred:', error.message);
        }
    }
};
export default connection;
