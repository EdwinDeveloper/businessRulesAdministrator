import React, { FC } from 'react';
import { GroupList } from './components/GroupList/GroupList';

const App: FC = () => {
  return (
    <div style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      alignContent: 'center'
    }}>
      <GroupList/>
    </div>  
  );
};

export default App;
