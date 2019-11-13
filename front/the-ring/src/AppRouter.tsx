import React from "react";
import {Route, Router, Switch} from "react-router-dom";
import LoginView from "./login/LoginView";
import {createBrowserHistory} from 'history';
import ProfileView from "./layout/profile/ProfileView";
import HomeView from "./layout/HomeView";
import {Layout, Menu} from "antd";

const { Header, Content } = Layout;

const customHistory = createBrowserHistory();

const AppRouter = () => {
    return (
        <Router history={customHistory}>
            <div>
                <Layout className="layout">
                    <Header>
                        <div className="logo" />
                        <Menu
                            theme="dark"
                            mode="horizontal"
                            defaultSelectedKeys={['1']}
                            style={{ lineHeight: '64px' }}
                        >
                            <Menu.Item key="1" onClick={() => customHistory.push("/")}>Home</Menu.Item>
                            <Menu.Item key="2" onClick={() => customHistory.push("/login")}>Login</Menu.Item>
                            <Menu.Item key="3" onClick={() => customHistory.push(`/filip`)}>Profile</Menu.Item>
                        </Menu>
                    </Header>
                    <Content style={{ padding: '0 50px' }}>
                        <Switch>
                            <Route path="/login">
                                <LoginView />
                            </Route>
                            <Route path="/:profileName" children={<ProfileView/>} />
                            <Route path="/">
                                <HomeView />
                            </Route>
                        </Switch>
                    </Content>
                </Layout>
            </div>
        </Router>
    )
};

export default AppRouter;