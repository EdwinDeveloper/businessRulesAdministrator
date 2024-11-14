import { FC, useState } from 'react';
import './RuleCard.css'
import '../general.css'
import { Rule } from '../../models/Elements';
import { useDrag, useDrop } from 'react-dnd';
import { RuleCardModal } from '../RuleCardModal/RuleCardModal';
import { ResultRuleModal } from '../ResultRuleModal/ResultRuleModal';

interface RuleCard{
  rule: Rule,
  index: number,
  isSelected: boolean,
  moveRule: (dragIndex: number, hoverIndex: number) => void,
  onSelect: (ruleId: string)=>void,
}

export const RuleCardComponent: FC<RuleCard> = ({ rule, index, isSelected = false, onSelect, moveRule }) => {
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

    const [openModal, setOpenModal] = useState(false)

    const handleClick = () => {
      onSelect(rule.id)
      setTimeout(() => {
        setOpenModal(true)
      }, 200);
    }
  
    return (
      <div
        ref={(node) => ref(drop(node))}
      >
        <div className={`rule-card${isSelected ? '-selected' : ''}`} onClick={()=>handleClick()}>
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
        </div>
        { isSelected &&
            <div className='button-container'>
              <button className="general-button-medium">Conditions</button>
              <button className="general-button-medium">Results</button>
            </div>
        }
        { isSelected && openModal &&
          <ResultRuleModal/>
          // <RuleCardModal rule={rule} onUpdate={()=>{}} setOpenModal={setOpenModal}/>
        }
      </div>
    );
  };

