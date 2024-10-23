import React, { FC } from 'react';
import { RuleCardListComponent } from './components/RuleCart/RuleCardList'

const App: FC = () => {
  return (
    <div style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      alignContent: 'center'
    }}>
      <RuleCardListComponent/>
    </div>  
  );
};

export default App;
