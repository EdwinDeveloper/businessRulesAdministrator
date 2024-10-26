import { FC, useState, ChangeEvent } from 'react';
import './JsonViewer.css';
import '../general.css'
import { RequestEvaluator } from '../../models/Elements';

interface JsonViewerProps {
    initialJsonData: RequestEvaluator | undefined;
}

export const JsonViewer: FC<JsonViewerProps> = ({ initialJsonData }) => {
    const [jsonData, setJsonData] = useState(initialJsonData);
    const [textData, setTextData] = useState(JSON.stringify(
        {
            ...initialJsonData,
            input: initialJsonData?.input instanceof Map ? Object.fromEntries(initialJsonData.input) : initialJsonData?.input
        }, 
        null, 
        2
    ));
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
                <button disabled={error!=null} onClick={()=>{}} className="general-button">Execute</button>
            </div>
        </div>
    );
};
