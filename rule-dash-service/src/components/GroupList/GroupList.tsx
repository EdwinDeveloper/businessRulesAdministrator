import { useState, useEffect } from 'react';
import './GroupList.css';
import { Group, Rule } from '../../models/Elements';
import { fetchGroupsFromApi, updateGroupRulesInApi } from '../../config/serivceImpl';
import GroupListDisplay from '../GroupListDisplay/GroupListDisplay';
import GroupDetails from '../GroupDetails/GroupDetails';

export const GroupList = () => {
    const [groups, setGroups] = useState<Group[]>([]);
    const [selectedGroup, setSelectedGroup] = useState<Group | null>(null);
    const [rules, setRules] = useState<Rule[]>([]);
    const [loading, setLoading] = useState(false);
    const [run, setRun] = useState(false)

    const fetchGroups = async () => {
        const response = await fetchGroupsFromApi();
        setGroups(response.data);
    };

    const handleGroupClick = (group: Group) => {
        const sortedRules = [...group.rules].sort((a, b) => parseInt(a.priority) - parseInt(b.priority));
        setRules(sortedRules);
        setSelectedGroup(group);
    };

    const handleDeselectGroup = () => {
        setSelectedGroup(null);
        setRules([]);
    };

    const handleUpdateGroup = async () => {
        if (selectedGroup) {
            setLoading(true);
            const updatedGroup: Group = { ...selectedGroup, rules };
            const response = await updateGroupRulesInApi(updatedGroup);
            if (response.status === 204) {
                setSelectedGroup(updatedGroup);
            }
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchGroups();
    }, []);

    return (
        <div className="group-list-component">
            {!selectedGroup ? (
                <GroupListDisplay groups={groups} onGroupClick={handleGroupClick} />
            ) : (
                <GroupDetails
                    group={selectedGroup}
                    rules={rules}
                    loading={loading}
                    onUpdateGroup={handleUpdateGroup}
                    onDeselectGroup={handleDeselectGroup}
                    setRules={setRules}
                    run={run}
                    setRun={setRun}
                />
            )}
        </div>
    );
};
