import React, { Component } from 'react';
import { request, setAuthToken } from "../../axios_helper";
import classes from './Login.module.css';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            active: 'login',
            firstname: "",
            lastname: "",
            login: "",
            password: "",
            error: null,
            success: null
        };
    }

    onChangeHandler = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    };

    onSubmitLogin = async (e) => {
        e.preventDefault();
        try {
            // Wysyłanie żądania logowania przy pomocy istniejącej funkcji request
            const response = await request('post', '/api/auth/login', {
                login: this.state.login,
                password: this.state.password
            });

            if (response.data.token) {
                setAuthToken(response.data.token); // Zapis tokena do localStorage
                this.setState({ success: "Zalogowano pomyślnie!", error: null });
                if (this.props.onLoginSuccess) {
                    this.props.onLoginSuccess(); // Wywołanie callbacka po udanym logowaniu
                }
            }
        } catch (error) {
            this.setState({
                error: "Błąd logowania. Sprawdź swoje dane.",
                success: null
            });
        }
    };

    render() {
        const { error, success } = this.state;

        return (
            <div>
                <form onSubmit={this.onSubmitLogin}>
                    <div>
                        <label htmlFor="login">Nazwa użytkownika:</label>
                        <input
                            type="text"
                            id="login"
                            name="login"
                            value={this.state.login}
                            onChange={this.onChangeHandler}
                            required
                        />
                    </div>
                    <div>
                        <label htmlFor="password">Hasło:</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={this.state.password}
                            onChange={this.onChangeHandler}
                            required
                        />
                    </div>
                    <button type="submit" className={classes.LoginButton}>Zaloguj się</button>
                    {error && <p className={classes.Error}>{error}</p>}
                    {success && <p className={classes.Success}>{success}</p>}
                </form>
            </div>
        );
    }
}
