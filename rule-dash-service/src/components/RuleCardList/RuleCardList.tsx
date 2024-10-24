import React, { FC, useState, useEffect } from 'react'
import './RuleCardList.css'
import '../general.css'
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DndProvider } from 'react-dnd';
import { RuleCardComponent } from '../RuleCart/RuleCart';
import { Rule } from '../../models/Elements';

interface RuleCardList{
  rules: Rule[]
  setRules: (rules: Rule[])=> void
}

export const RuleCardListComponent: FC<RuleCardList> = ( { rules, setRules } ) => {

  //const [rules, setRules] = useState<Rule[]>(rules);

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

  const updateGroup = ()=> {

  }
  

  return (
    <DndProvider backend={HTML5Backend}>
      <div className="card-list">
        {rules.length > 0 ? (
          <div className='list-container'>
            <div>
              {
                rules.map((rule, index) => (
                  <RuleCardComponent key={rule.id} rule={rule} index={index} moveRule={moveRule} />
                ))
              }
            </div>
            <div className='button-container'>
              <button onClick={() => updateGroup()} className="general-button">Update</button>
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
