import React, { FC, useState } from 'react'
import { RuleCard } from './../../models/Elements';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DndProvider } from 'react-dnd';
import { RuleCardComponent } from './RuleCart';

const initialCards: RuleCard[] = [
    { id: '1', content: 'current >= 12 && currency > 3' },
    { id: '2', content: 'Card 2' },
    { id: '3', content: 'Card 3' },
    { id: '4', content: 'Card 4' }
  ];

export const RuleCardListComponent: FC = () => {

    const [cards, setCards] = useState<RuleCard[]>(initialCards);

    const moveCard = (dragIndex: number, hoverIndex: number) => {
        const updatedCards = [...cards];
        const [removed] = updatedCards.splice(dragIndex, 1);
        updatedCards.splice(hoverIndex, 0, removed);
        setCards(updatedCards);
      };

      return (
        <DndProvider backend={HTML5Backend}>
          <div className="card-list" style={{ width: '250px', minHeight: '300px' }}>
            {cards.map((card, index) => (
              <RuleCardComponent key={card.id} card={card} index={index} moveCard={moveCard} />
            ))}
          </div>
        </DndProvider>
      );
}