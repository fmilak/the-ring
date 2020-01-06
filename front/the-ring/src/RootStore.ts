import LoginStore from "./login/LoginStore";
import HomeStore from "./layout/HomeStore";

class RootStore {
    loginStore: LoginStore = new LoginStore();
    homeStore: HomeStore = new HomeStore();
}

export default RootStore;
