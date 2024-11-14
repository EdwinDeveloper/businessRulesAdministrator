import react, { FC } from 'react'

import '../modals.css'
import '../general.css'

export const ResultRuleModal: FC = () => {
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                    <div>
                    <label>Results:</label>
                        {/* {Object.entries(rule.result).map(([key, value]) => (
                            <div className='result-element' key={key}>
                                <strong>{key}:</strong> {String(value)}
                                <button className="close-button-element">x</button>
                            </div>
                        ))} */}
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
    )
}