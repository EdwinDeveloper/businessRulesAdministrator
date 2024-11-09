import React, { useEffect, useState } from 'react';
import { RuleCardListComponent } from '../RuleCardList/RuleCardList';
import { Group, Rule } from '../../models/Elements';
import LoadingSpinner from '../LoadingSpinner/LoadingSpinner';
import { JsonViewer } from '../JsonViewer/JsonViewer';
import { v4 as uuidv4 } from 'uuid';
import { RequestEvaluator } from '../../models/Elements';

interface GroupDetailsProps {
    group: Group;
    rules: Rule[];
    loading: boolean;
    onUpdateGroup: () => void;
    onDeselectGroup: () => void;
    setRules: React.Dispatch<React.SetStateAction<Rule[]>>;
    run: boolean;
    setRun: (run: boolean)=>void
}

const GroupDetails: React.FC<GroupDetailsProps> = ({ run, setRun, group, rules, loading, onUpdateGroup, onDeselectGroup, setRules }) => {

    const [sampleJson, setSampleJson] = useState<RequestEvaluator>()
    const requestGenerator = () => {
        const newUuid = uuidv4();
        const json : RequestEvaluator= {
            traceability_id: newUuid,
            group_id: group.id,
            user: group.user_id,
            input: new Map<string, unknown>([
                ['condition1', 20],
                ["condition2", 3],
                ["condition3", 60],
                ["condition4", 45],
                ["condition", 2]
            ]),
        }
        return json
    }

    useEffect(()=>{
        setSampleJson(requestGenerator())
    }, [])

    return (
        <div>
            <h2>Group : {group.group_name}</h2>
            <h4>Flow type: {group.run_type}</h4>
            {loading ? (
                <LoadingSpinner />
            ) : rules.length > 0 ? (
                !run ?
                (<RuleCardListComponent
                    rules={rules}
                    setRules={setRules}
                    handleUpdateGroup={onUpdateGroup}
                    handleNotSelectedGroup={onDeselectGroup}
                    setRun={setRun}
                />): <JsonViewer setRun={setRun} initialJsonData={sampleJson}/>
            ) : (
                <div>
                    <p>No rules found for this group</p>
                    <button onClick={onDeselectGroup} className="general-button">Back</button>
                </div>
            )}
        </div>
    );
};

export default GroupDetails;
