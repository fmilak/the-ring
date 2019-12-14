import { observer } from "mobx-react"
import React, { ReactElement, useContext, useEffect } from "react"
import { Button, Form, Icon, Input, Select } from "antd"
import { RootStoreContext } from "../App"
import User from "../model/User"

const RegisterView: React.FC = observer(
  (): ReactElement => {
    const { loginStore } = useContext(RootStoreContext);
    const user: User = new User();

    useEffect(() => {
      loginStore.initRoles()
    }, []);

    return (
      <div>
        <Form>
          <Form.Item>
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Username"
              onChange={(text) => (user.username = text.target.value)}
            />
          </Form.Item>
          <Form.Item>
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Name"
              onChange={(text) => (user.name = text.target.value)}
            />
          </Form.Item>
          <Form.Item>
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Surname"
              onChange={(text) => (user.surname = text.target.value)}
            />
          </Form.Item>
          <Form.Item>
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Email"
              onChange={(text) => (user.email = text.target.value)}
            />
          </Form.Item>
          <Form.Item>
            <Input
              prefix={<Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />}
              type="password"
              placeholder="Password"
              onChange={(text) => (user.password = text.target.value)}
            />
          </Form.Item>
          {/* todo -> enable payment option */}
          <Form.Item>
            <Button
              onClick={(): void => {
                loginStore.registerUser(user)
              }}
            >
              Register
            </Button>
          </Form.Item>
        </Form>
      </div>
    )
  }
)

export default RegisterView
