import React, {useContext, useEffect} from "react";
import {Button, Form, Icon, Input} from "antd";
import '../App.css'
import {RestServiceContext, RootStoreContext} from "../App";
import {observer} from "mobx-react";
import { Redirect, useHistory } from "react-router-dom";

const LoginView: React.FC<any> = observer(() => {
    const rootStore = useContext(RootStoreContext);
    const {loginStore} = rootStore;
    const history = useHistory();

    useEffect(() => {
        loginStore.history = history;
    }, []);

    return (
        <div>
            <Form>
                <Form.Item>
                    <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                           placeholder="Username" onChange={loginStore.onUsernameChange} onPressEnter={loginStore.tryLogin} />
                </Form.Item>
                <Form.Item>
                    <Input
                        prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
                        type="password"
                        placeholder="Password"
                        onPressEnter={loginStore.tryLogin}
                        onChange={loginStore.onPasswordChange}
                    />
                </Form.Item>
                <Form.Item>
                    <Button onClick={loginStore.tryLogin}>Log in</Button>
                </Form.Item>
                <Form.Item>
                    <Button onClick={() => {
                        history.push("/register")
                    }}>Register</Button>
                </Form.Item>
            </Form>
        </div>

    )
});

export default LoginView;
