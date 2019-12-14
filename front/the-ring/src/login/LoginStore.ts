import {action, observable} from "mobx";
import {stringify} from 'querystring';
import { isNil } from "lodash";
import User from "../model/User";
import RestService from "../service/RestService";
import RestInit from "../model/api/RestInit";
import ApiResponse from "../model/api/ApiResponse";

class LoginStore {

    history: any;

    @observable username = '';

    @observable password = '';

    @observable isAuthenticated = false;

    @observable user = new User();

    @observable roles: Array<string> = new Array<string>();

    @action
    onUsernameChange = (e: any): void => {
        this.username = e.target.value;
    };

    @action
    onPasswordChange = (e: any): void => {
        this.password = e.target.value;
    };

    initRoles = (): void => {
        const restInit: RestInit = new RestInit();
        restInit.url = `/api/sign-in/roles`;
        restInit.method = 'GET';
        restInit.header = {
            'Content-Type': 'application/json'
        };
        RestService.fetch(restInit, this.handleInitRoles).catch(err => console.log(err));
    };

    handleInitRoles = (apiResponse: ApiResponse): void => {
        if (apiResponse.success) {
            this.roles = apiResponse.data
        } else {
            console.log(apiResponse.message);
        }
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
        this.user = JSON.parse(apiResponse.data);
    };

    registerUser = (user: User): void => {
        const restInit: RestInit = new RestInit();
        restInit.url = '/api/sign-in/register';
        restInit.method = 'POST';
        restInit.header = {
            'Content-Type': 'application/json'
        };
        user.role = 'ADMIN'; // todo -> remove after implementing roles
        restInit.body = JSON.stringify(user);
        RestService.fetch(restInit, this.handleRegisterResponse).catch(err => console.log(err));
    };

    handleRegisterResponse = (apiResponse: ApiResponse): void => {
        if (apiResponse.success) {
            this.history.push('/login');
        } else {
            console.log(apiResponse.data);
        }
    }

}

export default LoginStore;
