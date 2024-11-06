import * as React from 'react';
import AuthContent from "./AuthContent";
import {request, setAuthToken} from "../axios_helper";
import LoginForm from './LoginForm'
import Buttons from './Buttons'
import WelcomeContent from "./WelcomeContent";

export default class AppContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome",


        };
    };
    login = () => {
        this.setState({componentToShow: "login", errorMessage: "" });
    };
    logout = () => {
        this.setState({componentToShow: "welcome"});
        setAuthToken(null);
    };
    showMessages = () => {
        this.setState({ componentToShow: "messages" });
    }
    onLogin = (e, username, password) => {
        e.preventDefault();
        request("POST", "/api/auth/login", {login: username, password: password}
        ).then((response)=> {
            this.setState({componentToShow: "messages"})
            setAuthToken(response.data.token);
        }).catch((error)=> {
            this.setState({componentToShow: "welcome"})
        });
    };
    onRegister = (e, firstName, lastName, username, password) => {
        e.preventDefault();
        request("POST", "/api/auth/register", {
            firstName: firstName,
            lastName: lastName,
            login: username,
            password: password}
        ).then((response)=> {
            console.log("Registration successful, token received:", response.data.token);
            this.setState({componentToShow: "messages"})
            setAuthToken(response.data.token);
        }).catch((error)=> {
            console.error("Registration error:", error);
            this.setState({componentToShow: "welcome"})
        });
    };
    render() {
        return (
            <div>
                <Buttons login={this.login} logout={this.logout}/>
                {this.state.errorMessage && <p style={{ color: "red" }}>{this.state.errorMessage}</p>}
                {this.state.componentToShow === "welcome" && <WelcomeContent/>}
                {this.state.componentToShow === "messages" && <AuthContent/> }
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister}/>}


            </div>
        )
    }
}