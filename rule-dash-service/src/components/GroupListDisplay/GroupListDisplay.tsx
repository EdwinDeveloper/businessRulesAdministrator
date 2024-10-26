import { FC } from 'react';
import { Group } from '../../models/Elements';

interface GroupListDisplayProps {
    groups: Group[];
    onGroupClick: (group: Group) => void;
}

const GroupListDisplay: FC<GroupListDisplayProps> = ({ groups, onGroupClick }) => {
    return (
        <div>
            {groups.map((group) => (
                <div
                    className="group-component"
                    key={group.id}
                    onClick={() => onGroupClick(group)}
                >
                    <h3>{group.group_name}</h3>
                </div>
            ))}
        </div>
    );
};

export default GroupListDisplay;
