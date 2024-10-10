import React, { FC } from 'react';
import { RuleCard } from '../../models/Elements';
import { useDrag, useDrop } from 'react-dnd';

export const RuleCardComponent: FC<{ 
        card: RuleCard; 
        index: number; 
        moveCard: (
            dragIndex: number, 
            hoverIndex: number
        ) => void }
    > = ({ card, index, moveCard }) => {
    const [, ref] = useDrag({
      type: 'CARD',
      item: { index },
    });
  
    const [, drop] = useDrop({
      accept: 'CARD',
      hover(item: { index: number }) {
        if (item.index !== index) {
          moveCard(item.index, index);
          item.index = index;
        }
      },
    });
  
    return (
      <div
        ref={(node) => ref(drop(node))}
        style={{
            width: '300px',
            padding: '16px',
            margin: '12px',
            backgroundColor: '#f2f2f2',
            borderRadius: '4px',
            cursor: 'move'
        }}
      >
        {card.content}
      </div>
    );
  };

