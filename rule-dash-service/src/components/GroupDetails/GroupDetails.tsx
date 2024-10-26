import React, { useState } from 'react';
import { RuleCardListComponent } from '../RuleCardList/RuleCardList';
import { Group, Rule } from '../../models/Elements';
import LoadingSpinner from '../LoadingSpinner/LoadingSpinner';
import { JsonViewer } from '../JsonViewer/JsonViewer';

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

    const sampleJson = { data: "data1", items: [1, 2, 3], nested: { a: true, b: false } };

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
                />): <JsonViewer initialJsonData={sampleJson}/>
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
