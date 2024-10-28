import { FC, useState, ChangeEvent, useEffect } from 'react';
import './JsonViewer.css';
import '../general.css'
import { RequestEvaluator } from '../../models/Elements';
import { runEvaluationApi } from '../../config/serivceImpl';

interface JsonViewerProps {
    initialJsonData: RequestEvaluator | undefined;
}

export const JsonViewer: FC<JsonViewerProps> = ({ initialJsonData }) => {
    const [jsonData, setJsonData] = useState<RequestEvaluator | null | undefined>(initialJsonData);
    const [textData, setTextData] = useState(JSON.stringify(
        {
            ...initialJsonData,
            input: initialJsonData?.input instanceof Map ? Object.fromEntries(initialJsonData.input) : initialJsonData?.input
        }, 
        null, 
        2
    ));
    const [error, setError] = useState<string | null>(null);
    const [jsonViewer, setJsonViewer] = useState('jsonViewer')
    const [jsonResponse, setJsonResponse] = useState<Map<string, unknown>>(new Map());

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
    }

    const RunEvaluation = async(jsonData: RequestEvaluator | undefined | null) => {
        if(jsonData !== undefined && jsonData !== null){
            const resultRules = await runEvaluationApi(jsonData);
            if(resultRules.status == 200){
                setJsonViewer('jsonViewerOk')
                setJsonResponse(resultRules.data)
                console.log("resultRules : ", resultRules.data)
                setTimeout(() => {
                    setJsonViewer('jsonViewer')
                }, 1000);
            }
        }
    }

    useEffect(()=>{

    }, [])

    return (
        <div className="container">
            <textarea
                className="textarea"
                value={textData}
                onChange={handleTextChange}
            />
            {error && <p className="error">{error}</p>}
            { jsonViewer === 'jsonViewer' ? (
                <div className="jsonViewer">
                    <pre>{JSON.stringify(jsonData, null, 2)}</pre>
                </div>
                    ) : (
                    <div className="jsonViewerOk">
                        <pre>{JSON.stringify(jsonResponse, null, 2)}</pre>
                    </div>
            )}
            <div>
                <button disabled={error!=null} onClick={()=>RunEvaluation(jsonData)} className="general-button">Execute</button>
                {/* <button disabled={error!=null} onClick={()=>RunEvaluation(jsonData)} className="general-button">Execute</button> */}
            </div>
        </div>
    );
};
