import React, { FC, useState } from 'react'
import './RuleCardList.css'
import '../general.css'
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DndProvider } from 'react-dnd';
import { RuleCardComponent } from '../RuleCart/RuleCart';
import { Rule } from '../../models/Elements';

interface RuleCardListProps{
  rules: Rule[]
  setRules: (rules: Rule[])=> void,
  handleUpdateGroup: ()=> void,
  handleNotSelectedGroup: ()=> void
  setRun: (run: boolean)=>void
}

export const RuleCardListComponent: FC<RuleCardListProps> = ( { setRun, rules, setRules, handleUpdateGroup, handleNotSelectedGroup } ) => {

  const moveRule = (dragIndex: number, hoverIndex: number) => {
    const updatedRules = [...rules];
    const [removed] = updatedRules.splice(dragIndex, 1);
    updatedRules.splice(hoverIndex, 0, removed);
    const prioritizedRules = updatedRules.map((rule, index) => ({
      ...rule,
      priority: (index + 1).toString(),
    }));
    setRules(prioritizedRules);
  };

  const [selectedId, setSelectedId] = useState('');

  const handleSelectRule = (idRule: string) => {
    idRule === selectedId ? setSelectedId('') : setSelectedId(idRule)
  }
  

  return (
    <DndProvider backend={HTML5Backend}>
      <div className="card-list">
        {rules.length > 0 ? (
          <div className='list-container'>
            <div>
              {
                rules.map((rule, index) => (
                  <RuleCardComponent
                    key={rule.id}
                    rule={rule}
                    index={index}
                    moveRule={moveRule}
                    isSelected={selectedId === rule.id}
                    onSelect={()=>handleSelectRule(rule.id)}
                  />
                ))
              }
            </div>
            <div className='button-container'>
              <button onClick={() => setRun(true)} className="general-button" disabled={ selectedId !== '' }>Run</button>
              <button onClick={() => handleNotSelectedGroup()} className="general-button">Back</button>
              <button onClick={() => handleUpdateGroup()} className="general-button" disabled={ selectedId !== '' }>Update</button>
            </div>
          </div>
        ) : (
          <div>
            <p>No rules available.</p>
          </div>
        )}
      </div>
    </DndProvider>
  );
};
