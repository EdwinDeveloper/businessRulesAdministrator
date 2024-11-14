import React, { FC, useState, ChangeEvent, useEffect } from 'react'
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

    const [operationSelected, setOperationSelected] = useState('')
    const [scriptCondition, setScriptCondition] = useState('')

    const [name, setName] = useState('')
    const [paramName, setParamName] = useState('')
    const [paramValue, setParamValue] = useState('')

    type Operation = '<' | '<=' | '==' | '>' | '>=';

    const handleOperation = (event: ChangeEvent<HTMLSelectElement>) => {
        const so = event.target.value as Operation;
        setOperationSelected(so)
    };

    useEffect(()=>{
        setScriptCondition(rule.conditions)
        setName(rule.name)
    }, [])

    const handleExtendScriptCondition = (operation: string) => {
        if(!(scriptCondition.endsWith('&') || scriptCondition.endsWith('|'))  && !scriptCondition.endsWith(" ")){
            setScriptCondition(scriptCondition + " " + operation )
        }else if(scriptCondition.endsWith('&')){
            setScriptCondition(scriptCondition.replace(' &', ''))
        }else if(scriptCondition.endsWith('|')){
            setScriptCondition(scriptCondition.replace(' |', ''))
        }
    }

    const handleAddScriptCondition = () => {
        
        if((scriptCondition.endsWith('&') || scriptCondition.endsWith('|')) && (paramName!=='' && paramValue!=='')){
            setScriptCondition( scriptCondition + " " + paramName + operationSelected + paramValue )
            setParamName('')
            setParamValue('')
            setOperationSelected('')
        }
    }

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
                        value={name}
                        onChange={(event: ChangeEvent<HTMLInputElement>)=>{ setName(event.target.value)  }}
                        />
                    </div>

                    <div>
                        <label>Conditions:</label>
                        <div className='select-container'>
                            <textarea
                                className='general-input'
                                name="name"
                                disabled
                                value={scriptCondition}
                            />
                            <div className='button-container'>
                                <button className="general-button-small" onClick={()=>handleExtendScriptCondition('&')}>and</button>
                                <button className="general-button-small" onClick={()=>handleExtendScriptCondition('|')}>or</button>
                            </div>
                            <div>
                                <input
                                    className='general-input-small'
                                    type="text"
                                    name="paramName"
                                    onChange={(event: ChangeEvent<HTMLInputElement>)=>{ setParamName(event.target.value)  }}
                                    value={paramName}
                                />
                                <Dropdown handleOperation={handleOperation} operationSelected={operationSelected}/>
                                <input
                                    className='general-input-small'
                                    type="text"
                                    name="paramValue"
                                    onChange={(event: ChangeEvent<HTMLInputElement>)=>{ setParamValue(event.target.value)  }}
                                    value={paramValue}
                                />
                            </div>
                            <div className='button-container'>
                                <button className="general-button-small" onClick={()=>handleAddScriptCondition()}>add</button>
                            </div>
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