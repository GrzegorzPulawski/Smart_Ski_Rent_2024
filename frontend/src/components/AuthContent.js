import React from "react";

import {request} from '../axios_helper';

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
            <div className="row justify-around-l">
                <div className="col-4">
                    <div className="card">
                {this.state.data && this.state.data.map((line) => (
                    <li key={line}>{line}</li> // Dodany unikalny klucz 'key'
                ))}
            </div>
                </div>
            </div>
        )
    }
}