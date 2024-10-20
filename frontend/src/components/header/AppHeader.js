import React from "react";
import classes from "./AppHeader.module.css";
import {Link} from "react-router-dom";

const AppHeader = () => {
    const username = localStorage.getItem('username');
    return (
        <div className={classes.AppHeader}>
            <div className={classes.HeaderLeft}>
                <Link to={"/createUser"}>
                    <div className={classes.HeaderTitle}>Smart Ski Rent</div>
                </Link>
                <div className={classes.HeaderUser}>Zalogowany jest: {username}</div>
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

