import React, { useState, ChangeEvent } from 'react';
import './JsonViewer.css';
import '../general.css'

interface JsonViewerProps {
    initialJsonData: Record<string, any>;
}

export const JsonViewer: React.FC<JsonViewerProps> = ({ initialJsonData }) => {
    const [jsonData, setJsonData] = useState(initialJsonData);
    const [textData, setTextData] = useState(JSON.stringify(initialJsonData, null, 2));
    const [error, setError] = useState<string | null>(null);

    const handleTextChange = (e: ChangeEvent<HTMLTextAreaElement>) => {
        const newText = e.target.value;
        setTextData(newText);

        try {
            const parsedJson = JSON.parse(newText);
            const beautifiedText = JSON.stringify(parsedJson, null, 2);
            setJsonData(parsedJson);
            setTextData(beautifiedText);
            setError(null);
        } catch (e) {
            setError("Invalid JSON format");
        }
    };

    return (
        <div className="container">
            <textarea
                className="textarea"
                value={textData}
                onChange={handleTextChange}
            />
            {error && <p className="error">{error}</p>}
            <div className="jsonViewer">
                <pre>{JSON.stringify(jsonData, null, 2)}</pre>
            </div>
            <div>
                <button onClick={()=>{}} className="general-button">Execute</button>
            </div>
        </div>
    );
};
