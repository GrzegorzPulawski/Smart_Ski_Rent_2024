import React from "react";
import classes from "./AppBody.module.css";
import {  Route, Routes} from "react-router-dom";
import Home from "../home/Home";
import EquipmentList from "../equipment/EquipmentList";
import Login from "../login/Login";
import LogoutButton from "../logout/LogoutButton";
import FormEquipment from "../equipment/FormEquipment";
import ClientList from "../client/ClientList";
import FormClient from "../client/FormClient";
import Renting from "../renting/Renting";
import RentingList from "../renting/RentingList";
import ReturnRenting from "../renting/ReturnRenting";
import RentalAgreement from "../renting/RentalAgreement";
import EquipmentDelete from "../equipment/EquipmentDelete";
import CompanySave from "../company/CompanySave";
import DailyRevenueReport from "../reports/DailyRevenueReport";
import DeleteClient from "../client/DeleteClient";
import CreateUser from "../user/CreateUser";
import DeleteUser from "../user/DeleteUser";
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
                <Route path={"/formClient"} element={<FormClient/>}></Route>
                <Route path={"/renting"} element={<Renting/>}></Route>
                <Route path={"/rentingList"} element={<RentingList/>}></Route>
                <Route path={"/returnRenting"} element={<ReturnRenting/>}></Route>
                <Route path={"/rentalAgreement"} element={<RentalAgreement/>}></Route>
                <Route path={"/equipmentDelete"} element={<EquipmentDelete/>}></Route>
                <Route path={"/companySave"} element={<CompanySave/>}></Route>
                <Route path={"/dailyReport"} element={<DailyRevenueReport/>}></Route>
                <Route path={"/deleteClient"} element={<DeleteClient/>}></Route>
                <Route path={"/createUser"} element={<CreateUser/>}></Route>
                <Route path={"/deleteUser"} element={<DeleteUser/>}></Route>
            </Routes>
        </div>
    );
}
export default AppBody;