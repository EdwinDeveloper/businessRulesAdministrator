import React, { FC, useState, ChangeEvent } from 'react'
import { Rule } from '../../models/Elements';
import { Dropdown } from '../Dropdown/Dropdown';

import './RuleCardModal.css'
import '../general.css'

interface RuleCardModalProps{
    rule: Rule;
    onUpdate: (rule: Rule)=>void;
    setOpenModal: (close: boolean)=>void;
}

export const RuleCardModal: FC<RuleCardModalProps> = ( { rule, onUpdate, setOpenModal } ) => {

    const [formData, setFormData] = useState({
        name: rule.name,
        priority: rule.priority,
        conditions: rule.conditions,
        nextTrue: rule.nextTrue || '',
        nextFalse: rule.nextFalse || ''
      });

      const [operationSelected, setOperationSelected] = useState('')

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    }

    type Operation = '<' | '<=' | '==' | '>' | '>=';

    const handleOperation = (event: ChangeEvent<HTMLSelectElement>) => {
        const so = event.target.value as Operation;
        setOperationSelected(so)
      };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-button" onClick={()=>setOpenModal(false)}>×</button>
                <div>
                    <div>
                        <label>Name:</label>
                        <input
                        className='general-input'
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        />
                    </div>

                    <div>
                        <label>Conditions:</label>
                        <div className='select-container'>
                            <input
                                className='general-input-small'
                                type="text"
                                name="name"
                            />
                            <Dropdown handleOperation={handleOperation} operationSelected={operationSelected}/>
                            <input
                                className='general-input-small'
                                type="text"
                                name="name"
                            />
                        </div>
                    </div>

                    <div>
                        <label>Results:</label>
                        {Object.entries(rule.result).map(([key, value]) => (
                            <div className='result-element' key={key}>
                                <strong>{key}:</strong> {String(value)}
                                <button className="close-button-element">x</button>
                            </div>
                        ))}
                    </div>

                    <div className='button-container'>
                            <input
                                className='general-input-small'
                                type="text"
                                name="name"
                            />
                            <input
                                className='general-input-small'
                                type="text"
                                name="name"
                            />
                    </div>

                    <div className='button-container'>
                        <button className="general-button">add</button>
                        <button className="general-button">update</button>
                    </div>
                </div>
            </div>
        </div>
    )
}