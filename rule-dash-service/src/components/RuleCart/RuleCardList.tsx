import React, { FC, useState, useEffect } from 'react'
import { Rule } from './../../models/Elements';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DndProvider } from 'react-dnd';
import { RuleCardComponent } from './RuleCart';
import { FetchCall } from '../../config/fetch';
import { GetAllGroupsOfUser } from '../../config/services';
import { GetGroupsUsersResponse } from './../../models/Elements';

export const RuleCardListComponent: FC = () => {
  const [rules, setRules] = useState<Rule[]>([]);

  const fetchGroups = async () => {
    try {
      const groups = await FetchCall<GetGroupsUsersResponse>(GetAllGroupsOfUser("7538cf7e-6621-45c4-aa5a-9d74779086a6"));
      const sortedRules = groups.data[0].rules.sort((a, b) => parseInt(a.priority) - parseInt(b.priority));
      setRules(sortedRules);
    } catch (error) {
      console.error("Error fetching groups:", error);
    }
  };

  useEffect(() => {
    fetchGroups();
  }, []);

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
  

  return (
    <DndProvider backend={HTML5Backend}>
      <div className="card-list" style={{
        justifyContent: 'center',
        alignItems: 'center',
        alignContent: 'center',
        backgroundColor: 'red',
        width: '70%',
        minHeight: '80vh'
      }}>
        {rules.length > 0 ? (
          rules.map((rule, index) => (
            <RuleCardComponent key={rule.id} rule={rule} index={index} moveRule={moveRule} />
          ))
        ) : (
          <p>No rules available.</p>
        )}
      </div>
    </DndProvider>
  );
};
