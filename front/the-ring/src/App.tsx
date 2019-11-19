import React from 'react';
import './App.css';
import RootStore from "./RootStore";
import AppRouter from "./AppRouter";
import RestService from "./service/RestService";

export const RootStoreContext = React.createContext(new RootStore());
export const RestServiceContext = React.createContext(new RestService());

const App: React.FC = () => {
  return (
      <RootStoreContext.Provider value={new RootStore()}>
          <RestServiceContext.Provider value={new RestService()}>
              <AppRouter />
          </RestServiceContext.Provider>
      </RootStoreContext.Provider>

  );
};

export default App;
