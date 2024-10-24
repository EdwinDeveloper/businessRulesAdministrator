import React, { useState, useEffect } from 'react';
import './GroupList.css';
import { RuleCardListComponent } from '../RuleCardList/RuleCardList';
import { FetchCall } from '../../config/fetch';
import { GetAllGroupsOfUser } from '../../config/services';
import { GetGroupsUsersResponse } from '../../models/Elements';
import { Group, Rule } from '../../models/Elements';

export const GroupList = () => {
    const [groups, setGroups] = useState<Group[]>([]);
    const [selectedGroup, setSelectedGroup] = useState<Group | null>(null); // Only null or Group
    const [rules, setRules] = useState<Rule[]>([]);

    const fetchGroups = async () => {
        try {
            const response = await FetchCall<GetGroupsUsersResponse>(GetAllGroupsOfUser("7538cf7e-6621-45c4-aa5a-9d74779086a6"));
            setGroups(response.data);
        } catch (error) {
            console.error("Error fetching groups:", error);
        }
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

    const handleUpdateGroup = () => {
        if (selectedGroup) {
            const updatedGroup = { ...selectedGroup, rules: rules };
            setSelectedGroup(updatedGroup);
        }
    };

    useEffect(() => {
        fetchGroups();
    }, []);

    return (
        <div className="group-list-component">
            {!selectedGroup ? (
                <>
                    {groups.map((group: Group) => (
                        <div
                            className="group-component"
                            key={group.id}
                            onClick={() => handleGroupClick(group)}
                        >
                            <h3>{group.group_name}</h3>
                        </div>
                    ))}
                </>
            ) : (
                <div>
                    <h2>{selectedGroup.group_name}</h2>
                    <button onClick={handleDeselectGroup}>Back to Groups</button>
                    {rules.length > 0 ? (
                        <RuleCardListComponent
                            rules={rules}
                            setRules={setRules}
                            handleUpdateGroup={handleUpdateGroup}
                            handleNotSelectedGroup={handleDeselectGroup}
                        />
                    ) : (
                        <p>No rules found for this group</p>
                    )}
                </div>
            )}
        </div>
    );
};
