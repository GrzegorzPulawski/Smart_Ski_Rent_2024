import React from "react";
import classes from "./AppBody.module.css";
import {  Route, Routes} from "react-router-dom";
import Home from "../home/Home";
import EquipmentList from "../equipmentlist/EquipmentList";
import Login from "../login/Login";
import LogoutButton from "../logout/LogoutButton";
import FormEquipment from "../formequipment/FormEquipment";


const AppBody = () => {

    return(
        <div className={classes.AppBody}>
            <Routes>
                <Route path={"/"} element={<Home/>}></Route>
                <Route path={"/login"} element={<Login/>}></Route>
                <Route path={"/list"} element={<EquipmentList/>}> </Route>
                <Route path={"/clientlist"} element={<ClientList/>}> </Route>
                <Route path={"/logout"} element={<LogoutButton/>}></Route>
                <Route path={"/formequipment"} element={<FormEquipment/>}></Route>
            </Routes>
        </div>
    );
}
export default AppBody;