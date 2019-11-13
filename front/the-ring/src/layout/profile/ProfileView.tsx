import React, {ReactElement} from "react";
import {useParams} from "react-router";

const ProfileView = (): ReactElement => {
    const {profileName} = useParams();

    return (
        <div>
            <h3>{profileName}</h3>
        </div>
    )
};

export default ProfileView;