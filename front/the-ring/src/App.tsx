import React from 'react';
import './App.css';
import LoginView from "./login/LoginView";
import RootStore from "./RootStore";

export const RootStoreContext = React.createContext(new RootStore());

const App: React.FC = () => {
  return (
      <RootStoreContext.Provider value={new RootStore()}>
        <LoginView />
      </RootStoreContext.Provider>

  );
};

export default App;
