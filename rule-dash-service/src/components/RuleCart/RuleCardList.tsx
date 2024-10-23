import React, { FC, useState, useEffect } from 'react'
import { RuleCard } from './../../models/Elements';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DndProvider } from 'react-dnd';
import { RuleCardComponent } from './RuleCart';
import { FetchCall } from '../../config/fetch';
import { GetAllGroupsOfUser } from '../../config/services';

const initialCards: RuleCard[] = [
    { id: '1', content: 'current >= 12 && currency > 3' },
    { id: '2', content: 'Card 2' },
    { id: '3', content: 'Card 3' },
    { id: '4', content: 'Card 4' }
  ];

export const RuleCardListComponent: FC = () => {

    const fetchGroups = async() => {
      const groups = await FetchCall(GetAllGroupsOfUser("7538cf7e-6621-45c4-aa5a-9d74779086a6"))
      console.log("groups : ", groups); 
    }

    useEffect(() => {
      fetchGroups();
  }, []);

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