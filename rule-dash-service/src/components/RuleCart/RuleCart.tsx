import React, { FC } from 'react';
import { Rule } from '../../models/Elements';
import { useDrag, useDrop } from 'react-dnd';

export const RuleCardComponent: FC<{ 
        rule: Rule; 
        index: number; 
        moveRule: (
            dragIndex: number, 
            hoverIndex: number
        ) => void }
    > = ({ rule, index, moveRule }) => {
    const [, ref] = useDrag({
      type: 'CARD',
      item: { index },
    });
  
    const [, drop] = useDrop({
      accept: 'CARD',
      hover(item: { index: number }) {
        if (item.index !== index) {
          moveRule(item.index, index);
          item.index = index;
        }
      },
    });
  
    return (
      <div
        ref={(node) => ref(drop(node))}
        style={{
          width: '95%',
          justifyContent: 'center',
          alignContent: 'center',
          alignItems: 'center',
          padding: '16px',
          margin: '12px',
          backgroundColor: '#f2f2f2',
          borderRadius: '8px',
          cursor: 'move',
          boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
          display: 'flex',
          flexDirection: 'row',
          gap: '24px',
        }}
      >
        <div style={{ fontWeight: 'bold', fontSize: '16px', width: '120px' }}>
          {rule.name}
        </div>
        
        <div style={{ fontSize: '12px', color: '#888', width: '80px' }}>
          Priority: {rule.priority}
        </div>
    
        <div style={{ fontSize: '14px', color: '#555', width: '200px' }}>
          Conditions: {rule.conditions}
        </div>
    
        <div style={{ fontSize: '12px', color: '#777', width: '100px' }}>
          Next True: {rule.nextTrue ? rule.nextTrue : 'None'}
        </div>
        
        <div style={{ fontSize: '12px', color: '#777', width: '100px' }}>
          Next False: {rule.nextFalse ? rule.nextFalse : 'None'}
        </div>
    
        <div style={{ fontSize: '12px', color: '#999', width: '150px' }}>
          User ID: <br/> {rule.userId}
        </div>
      </div>
    );
  };

