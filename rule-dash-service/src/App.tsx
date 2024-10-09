import React, { useState } from 'react';
import { DndProvider, useDrag, useDrop } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';

interface Card {
  id: string;
  content: string;
}

// Sample initial card data
const initialCards: Card[] = [
  { id: '1', content: 'Card 1' },
  { id: '2', content: 'Card 2' },
  { id: '3', content: 'Card 3' },
  { id: '4', content: 'Card 4' }
];

// Draggable Card component
const CardComponent: React.FC<{ card: Card; index: number; moveCard: (dragIndex: number, hoverIndex: number) => void }> = ({ card, index, moveCard }) => {
  const [, ref] = useDrag({
    type: 'CARD',
    item: { index },
  });

  const [, drop] = useDrop({
    accept: 'CARD',
    hover(item: { index: number }) {
      if (item.index !== index) {
        moveCard(item.index, index);
        item.index = index; // Update the dragged item's index
      }
    },
  });

  return (
    <div
      ref={(node) => ref(drop(node))}
      style={{
        padding: '16px',
        margin: '4px',
        backgroundColor: '#f2f2f2',
        borderRadius: '4px',
        cursor: 'move'
      }}
    >
      {card.content}
    </div>
  );
};

// Main App component
const App: React.FC = () => {
  const [cards, setCards] = useState<Card[]>(initialCards);

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
          <CardComponent key={card.id} card={card} index={index} moveCard={moveCard} />
        ))}
      </div>
    </DndProvider>
  );
};

export default App;
