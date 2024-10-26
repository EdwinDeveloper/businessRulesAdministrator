import React, { useState, useEffect } from 'react';
import './GroupList.css';
import { RuleCardListComponent } from '../RuleCardList/RuleCardList';
import { FetchCall } from '../../config/fetch';
import { UpdateGroupRules } from '../../config/services';
import { GetAllGroupsOfUser } from '../../config/services';
import { GetGroupsUsersResponse } from '../../models/Elements';
import { Group, Rule } from '../../models/Elements';
import { UpdateGroupUserResponse } from '../../models/Elements';
import LoadingSpinner from '../LoadingSpinner/LoadingSpinner';
import { JsonViewer } from '../JsonViewer/JsonViewer';

export const GroupList = () => {
    const [groups, setGroups] = useState<Group[]>([]);
    const [selectedGroup, setSelectedGroup] = useState<Group | null>(null);
    const [rules, setRules] = useState<Rule[]>([]);
    const [loading, setLoading] = useState<Boolean>(false)

    const sampleJson = { data: "data1", items: [1, 2, 3], nested: { a: true, b: false } };

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

    const handleUpdateGroup = async() => {
        if (selectedGroup) {
            setLoading(true)
            const updatedGroup: Group = { ...selectedGroup, rules: rules };
            setSelectedGroup(updatedGroup);
            const response = await FetchCall<UpdateGroupUserResponse>(UpdateGroupRules(updatedGroup))
            setTimeout(() => {
                if(response.status == 204){
                    setLoading(false)
                }
            }, 200);
        }
    };

    useEffect(() => {
        fetchGroups();
    }, []);

    return (
        <div className="group-list-component">
            {!selectedGroup ? (
                <div>
                    {groups.map((group: Group) => (
                        <div
                            className="group-component"
                            key={group.id}
                            onClick={() => handleGroupClick(group)}
                        >
                            <h3>{group.group_name}</h3>
                        </div>
                    ))}
                </div>
            ) : (
                <div>
                    <h2>{selectedGroup.group_name}</h2>
                    <h4>Flow type : {selectedGroup.run_type}</h4>
                    {loading ?
                        (
                                <LoadingSpinner/>
                        )
                            :
                            (
                                rules.length > 0
                                ?
                                    (
                                        <RuleCardListComponent
                                            rules={rules}
                                            setRules={setRules}
                                            handleUpdateGroup={handleUpdateGroup}
                                            handleNotSelectedGroup={handleDeselectGroup}
                                        />
                                    )
                                : 
                                    (
                                        <div>
                                            <p>No rules found for this group</p>
                                            <button onClick={() => handleDeselectGroup()} className="general-button">Back</button>
                                        </div>
                                    )
                        )
                    }
                </div>
            )}
        </div>
    );
};
