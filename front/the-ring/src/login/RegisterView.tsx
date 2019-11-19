import {observer} from "mobx-react";
import React, {ReactElement, useContext} from "react";
import {RootStoreContext} from "../App";
import {Button, Form, Icon, Input} from "antd";
import {Redirect} from "react-router";
import User from "../model/User";

const RegisterView: React.FC = observer((): ReactElement => {
    const {loginStore} = useContext(RootStoreContext);
    const user: User = new User();

    return (
        <div>
            <Form>
                <Form.Item>
                    <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                           placeholder="Username" onChange={text => user.username = text.target.value} />
                </Form.Item>
                <Form.Item>
                    <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                           placeholder="Name" onChange={text => user.name = text.target.value} />
                </Form.Item>
                <Form.Item>
                    <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                           placeholder="Surname" onChange={text => user.surname = text.target.value} />
                </Form.Item>
                <Form.Item>
                    <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                           placeholder="Email" onChange={text => user.email = text.target.value} />
                </Form.Item>
                <Form.Item>
                    <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                           placeholder="Role" onChange={text => user.role = text.target.value /* todo -> change */} />
                </Form.Item>
                <Form.Item>
                    <Input
                        prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
                        type="password"
                        placeholder="Password"
                        onChange={text => user.password = text.target.value}
                    />
                </Form.Item>
                <Form.Item>
                    <Button onClick={(): void => {
                        loginStore.registerUser(user)
                    }}>Register</Button>
                </Form.Item>
            </Form>
        </div>
    )
});

export default RegisterView;