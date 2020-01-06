import React, {useContext, useEffect} from "react";
import {Button, Col, Form, Icon, Input, Row} from "antd";
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
        <div style={{marginTop: 50}}>
            <Form>
                <Row>
                    <Col span={24} style={{alignContent: 'center', justifyContent: 'center', display: 'flex', marginBottom: 25}}>
                        <label style={{fontSize: 40}}>Fakesbook</label>
                    </Col>
                </Row>
                <Row>
                    <Col span={24} style={{alignContent: 'center', justifyContent: 'center', display: 'flex'}}>
                        <Col span={8}>
                            <Form.Item>
                                <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                                       placeholder="Username" onChange={loginStore.onUsernameChange} onPressEnter={loginStore.tryLogin} />
                            </Form.Item>
                        </Col>
                    </Col>
                </Row>
                <Row>
                    <Col span={24} style={{alignContent: 'center', justifyContent: 'center', display: 'flex'}}>
                        <Col span={8}>
                            <Form.Item>
                                <Input
                                    prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
                                    type="password"
                                    placeholder="Password"
                                    onPressEnter={loginStore.tryLogin}
                                    onChange={loginStore.onPasswordChange}
                                />
                            </Form.Item>
                        </Col>
                    </Col>
                </Row>
                <Row>
                    <Col span={24} style={{alignContent: 'center', justifyContent: 'center', display: 'flex'}}>
                        <Col span={4} style={{alignContent: 'center', justifyContent: 'flex-end', display: 'flex', margin: 10}}>
                            <Form.Item>
                                <Button onClick={loginStore.tryLogin}>Log in</Button>
                            </Form.Item>
                        </Col>
                        <Col span={4} style={{alignContent: 'center', justifyContent: 'flex-start', display: 'flex', margin: 10}}>
                            <Form.Item>
                                <Button onClick={() => {
                                    history.push("/register")
                                }}>Register</Button>
                            </Form.Item>
                        </Col>
                    </Col>
                </Row>
            </Form>
        </div>

    )
});

export default LoginView;
