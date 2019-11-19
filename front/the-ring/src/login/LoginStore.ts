import {action, observable} from "mobx";
import {stringify} from 'querystring';
import { isNil } from "lodash";
import User from "../model/User";
import RestService from "../service/RestService";
import RestInit from "../model/api/RestInit";
import ApiResponse from "../model/api/ApiResponse";

class LoginStore {

    @observable username = '';

    @observable password = '';

    @observable isAuthenticated = false;

    @observable user = new User();

    @action
    onUsernameChange = (e: any): void => {
        this.username = e.target.value;
    };

    @action
    onPasswordChange = (e: any): void => {
        this.password = e.target.value;
    };

    @action
    tryLogin = async (): Promise<void> => {
        const restInit: RestInit = new RestInit();
        restInit.url = 'http://localhost:8080/oauth/token';
        restInit.header = {
            'Authorization': 'Basic dGhlLXJpbmc6cGFzc3dvcmQ=',
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        };
        restInit.body = stringify({
            username: this.username,
            grant_type: 'password',
            password: this.password,
        });
        restInit.method = 'POST';
        const response = await fetch(restInit.url, {
            headers: restInit.header,
            body: restInit.body,
            method: restInit.method
        });
        console.log(response);
        const responseJson = await response.json();
        console.log(responseJson);
        this.handleLoginResponse(responseJson);
    };

    @action
    handleLoginResponse = (responseJson: any): void => {
        console.log(responseJson);
        if (!isNil(responseJson.access_token)) {
            this.isAuthenticated = true;
            localStorage.setItem('token', responseJson.access_token);
            this.getUser();
        }
    };

    @action
    getUser = (): void => {
        const restInit: RestInit = new RestInit();
        restInit.url = `/api/user/${this.username}`;
        restInit.method = 'GET';
        restInit.header = {
            'Authorization': `bearer ${localStorage.getItem('token')}`,
        };
        RestService.fetch(restInit, this.handleUserResponse).catch(err => console.log(err));
    };
    handleUserResponse = (apiResponse: ApiResponse): void => {
        this.user = apiResponse.data;
    }

}

export default LoginStore;
