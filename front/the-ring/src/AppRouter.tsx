import React from "react";
import {Link, Route, Router, Switch} from "react-router-dom";
import LoginView from "./login/LoginView";
import {createBrowserHistory} from 'history';

const customHistory = createBrowserHistory();

const AppRouter = () => {
    return (
        <Router history={customHistory}>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/login">Login</Link>
                        </li>
                        <li>
                            <Link to="/nesto">Nesto</Link>
                        </li>
                    </ul>
                </nav>

                <Switch>
                    <Route path="/login">
                        <LoginView />
                    </Route>
                    <Route path="/nesto">
                        {/*todo*/}
                    </Route>
                    <Route path="/">
                        <LoginView />
                    </Route>
                </Switch>
            </div>
        </Router>
    )
};

export default AppRouter;