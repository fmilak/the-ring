import React, {useContext} from "react";
import {Route, Router, Switch} from "react-router-dom";
import LoginView from "./login/LoginView";
import {createBrowserHistory} from 'history';
import ProfileView from "./layout/profile/ProfileView";
import HomeView from "./layout/HomeView";
import {Icon, Layout, Menu} from "antd";
import {RootStoreContext} from "./App";
import {observer} from "mobx-react";
import RegisterView from "./login/RegisterView";

const { Header, Content } = Layout;

const customHistory = createBrowserHistory();

const AppRouter = observer(() => {
    const rootStore = useContext(RootStoreContext);
    const {loginStore} = rootStore;

    return (
        <Router history={customHistory}>
            <div>
                {loginStore.isAuthenticated ? (
                    <Layout className="layout" style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
                        <Header>
                            <div className="logo" />
                            <Menu
                                theme="dark"
                                mode="horizontal"
                                defaultSelectedKeys={['1']}
                                style={{ lineHeight: '64px' }}
                            >
                                <Menu.Item key="1" onClick={() => customHistory.push("/")}><Icon type="home" />Home</Menu.Item>
                                <Menu.Item key="2" onClick={() => customHistory.push(`/${loginStore.username}`)}><Icon type="user" />{loginStore.user.name}</Menu.Item>
                                <Menu.Item key="3" onClick={() => loginStore.isAuthenticated = false}><Icon type="user" />Logout</Menu.Item>
                            </Menu>
                        </Header>
                        <Content style={{ padding: '0 50px' }}>
                            <div style={{ background: '#fff', padding: 24, minHeight: 280 }}>
                                <Switch>
                                    <Route path="/:profileName" children={<ProfileView/>} />
                                    <Route path="/">
                                        <HomeView />
                                    </Route>
                                </Switch>
                            </div>
                        </Content>
                    </Layout>
                ) : (
                    <Switch>
                        <Route path="/login">
                            <LoginView />
                        </Route>
                        <Route path="/register">
                            <RegisterView />
                        </Route>

                        <Route path="/">
                            {() => customHistory.push("/login")}
                        </Route>
                    </Switch>
                )}

            </div>
        </Router>
    )
});

export default AppRouter;