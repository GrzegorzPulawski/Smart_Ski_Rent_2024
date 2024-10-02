import React from "react";
import classes from "./AppHeader.module.css";
import {Link} from "react-router-dom";

const AppHeader = () => {
    return(
        <div className={classes.AppHeader}>
            <div className={classes.HeaderLeft}>
                <p>Mała Wypożyczalnia Nart</p>
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
                <Link to={"/rent"}>
                    <div>Wypożycz</div>
                </Link>
                <Link to={"/list"}>
                    <div>Sprzęt</div>
                </Link>
                <Link to={"/logout"}>
                    <div>Wylogowanie</div>
                </Link>
            </div>
        </div>
    );

}
export default AppHeader;