import React from "react";

import { request} from '../axios_helper';

export default class AuthContent extends  React.Component{

    constructor(props) {
        super(props);
        this.state = {
            data : []
        };
    };
    componentDidMount() {
        request(
            "GET",
            "/messages",
            {}
        ).then((response)=>{
            this.setState({data: response.data});
        })
    .catch((error) => {
            console.error("Błąd podczas pobierania wiadomości:", error);
        });
    };
    render() {
        return (
            <div>
                {this.state.data && this.state.data.map((line, index) => (
                    <p key={index}>{line}</p> // Dodany unikalny klucz 'key'
                ))}
            </div>
        )
    }
}