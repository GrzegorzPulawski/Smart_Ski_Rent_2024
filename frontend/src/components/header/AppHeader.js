import React from "react";
import classes from "./AppHeader.module.css";
import {Link} from "react-router-dom";

const AppHeader = () => {
    const username = localStorage.getItem('username');

    return (
        <div className={classes.AppHeader}>
            <div className={classes.HeaderLeft}>
                <p>Smart Ski Rent</p>
                <p className={classes.HeaderUser}>Zalogowany jest: {username}</p>
            </div>
            <div className={classes.HeaderRight}>
                <Link to={"/login"}>
                    <div>Login</div>
                </Link>
                <Link to={"/"}>
                    <div>Home</div>
                </Link>
                <Link to={"/clientlist"}>
                    <div>Klient</div>
                </Link>
                <Link to={"/renting"}>
                    <div>Wypożycz</div>
                </Link>
                <Link to={"/list"}>
                    <div>Sprzęt</div>
                </Link>
                <Link to={"/dailyReport"}>
                    <div>Raporty</div>
                </Link>
            </div>
        </div>
    );
};

export default AppHeader;

