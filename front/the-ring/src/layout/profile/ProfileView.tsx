import React, {useContext} from "react";
import {useParams} from "react-router";
import {RootStoreContext} from "../../App";
import {Redirect, Route, useHistory} from "react-router-dom";

const ProfileView = () => {
    const {profileName} = useParams();
    const rootStore = useContext(RootStoreContext);
    const {loginStore} = rootStore;
    if (loginStore.username !== profileName) {
        return (
            <Route>
                <Redirect to="/" />
            </Route>
        )
    }
    return (
        <div>
            <h3>{profileName}</h3>
        </div>
    )
};

export default ProfileView;