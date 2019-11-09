import {action, observable} from "mobx";

class LoginStore {

    @observable username = '';

    @observable password = '';

    @action
    onUsernameChange = (e: any): void => {
        this.username = e.target.value;
    };

    @action
    onPasswordChange = (e: any): void => {
        this.password = e.target.value;
    };

    tryLogin = (): void => {
        console.log('Login tried');
        console.log(this.username);
        console.log(this.password);
    };

}

export default LoginStore;
