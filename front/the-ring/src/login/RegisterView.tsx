import { observer } from "mobx-react"
import React, { ReactElement, useContext, useEffect } from "react"
import {Button, Col, Form, Icon, Input, Row, Select} from "antd"
import { RootStoreContext } from "../App"
import User from "../model/User"
import {useHistory} from "react-router-dom";

const RegisterView: React.FC = observer(
  (): ReactElement => {
    const { loginStore } = useContext(RootStoreContext);
    const user: User = new User();
    const history = useHistory();

    useEffect(() => {
      loginStore.initRoles();
      loginStore.history = history;
    }, []);

    return (
      <div style={{margin: 30}}>
        <Form>
            <Row>
                <Col span={24} style={{alignContent: 'center', justifyContent: 'center', display: 'flex', marginBottom: 25}}>
                    <label style={{fontSize: 40}}>Register</label>
                </Col>
            </Row>
            <Row style={{justifyContent: 'center', alignContent: 'center', display: 'flex'}}>
                <Col span={8}>
                    <Form.Item>
                        <Input
                            prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
                            placeholder="Username"
                            onChange={(text) => (user.username = text.target.value)}
                        />
                    </Form.Item>
                </Col>
                <Col span={1}/>
                <Col span={8}>
                    <Form.Item>
                        <Input
                            prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
                            placeholder="Name"
                            onChange={(text) => (user.name = text.target.value)}
                        />
                    </Form.Item>
                </Col>
            </Row>
            <Row style={{justifyContent: 'center', alignContent: 'center', display: 'flex'}}>
                <Col span={8}>
                    <Form.Item>
                        <Input
                            prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
                            placeholder="Surname"
                            onChange={(text) => (user.surname = text.target.value)}
                        />
                    </Form.Item>
                </Col>
                <Col span={1}/>
                <Col span={8}>
                    <Form.Item>
                        <Input
                            prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
                            placeholder="Email"
                            onChange={(text) => (user.email = text.target.value)}
                        />
                    </Form.Item>
                </Col>
            </Row>
            <Row style={{justifyContent: 'center', alignContent: 'center', display: 'flex'}}>
                <Col span={8}>
                    <Form.Item>
                        <Input
                            prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
                            placeholder="Surname"
                            onChange={(text) => (user.surname = text.target.value)}
                        />
                    </Form.Item>
                </Col>
                <Col span={1}/>
                <Col span={8}>
                    <Form.Item>
                        <Input
                            prefix={<Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />}
                            placeholder="Payment option"
                            onChange={(text) => {}}
                        />
                    </Form.Item>
                    {/* todo -> enable payment option */}
                </Col>
            </Row>
            <Row style={{justifyContent: 'center', alignContent: 'center', display: 'flex'}}>
                <Col span={1}>
                    <Form.Item>
                        <Button
                            onClick={(): void => {
                                history.goBack();
                            }}
                        >
                            Back
                        </Button>
                    </Form.Item>
                </Col>
                <Col span={1}>
                    <Form.Item>
                        <Button
                            onClick={(): void => {
                                loginStore.registerUser(user)
                            }}
                        >
                            Register
                        </Button>
                    </Form.Item>
                </Col>
            </Row>

        </Form>
      </div>
    )
  }
);

export default RegisterView
