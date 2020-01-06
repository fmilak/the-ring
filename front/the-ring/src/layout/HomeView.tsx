import {observer} from "mobx-react";
import React, {ReactElement, useContext} from "react";
import {RootStoreContext} from "../App";
import {Redirect, Route} from "react-router";

const HomeView: React.FC = observer((): ReactElement => {
    const rootStore = useContext(RootStoreContext);
    const {loginStore} = rootStore;
    if (!loginStore.isAuthenticated) {
        return (
            <Redirect to="/login" />
        )
    }
    return (
        <div>
            <h1>Home</h1>
        </div>
    )
});

export default HomeView;