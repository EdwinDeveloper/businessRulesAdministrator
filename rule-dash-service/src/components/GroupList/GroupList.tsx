import React, { useState, useEffect } from 'react'
import { RuleCardListComponent } from '../RuleCardList/RuleCardList';
import { FetchCall } from '../../config/fetch';
import { GetAllGroupsOfUser } from '../../config/services';
import { GetGroupsUsersResponse } from '../../models/Elements';
import { Group, Rule } from '../../models/Elements';


export const GroupList = ()=> {

    const [groups, setGroups] = useState<Group[]>([])
    const [rules, setRules] = useState<Rule[]>([])

    const fetchGroups = async () => {
        try {
          const groups = await FetchCall<GetGroupsUsersResponse>(GetAllGroupsOfUser("7538cf7e-6621-45c4-aa5a-9d74779086a6"));
          setGroups(groups.data)
        } catch (error) {
          console.error("Error fetching groups:", error);
        }
    };

    const handleGroupClick = (group: Group) => {
        const sortedRules = group.rules.sort((a, b) => parseInt(a.priority) - parseInt(b.priority));
        setRules(sortedRules);
      };
    
    useEffect(() => {
        fetchGroups();
    }, []);

    return (
        <div>
            {groups.map((group: Group) => (
                        <div key={group.id} onClick={() => handleGroupClick(group)}>
                            <h3>{group.group_name}</h3>
                        </div>
                    )
                )
            }
            {rules.length > 0 ? (
                <RuleCardListComponent rules={rules} setRules={setRules} />
                ) : (
                <p>Please select a group to view its rules</p> // Fallback when no group is selected
            )}
    </div>
    );
}