import { FetchCall } from './fetch';
import { GetAllGroupsOfUser, UpdateGroupRules, RunEvaluationRules } from './services';
import { GetGroupsUsersResponse } from '../models/Elements';
import { Group, UpdateGroupUserResponse, RequestEvaluator, RunEvaluationResponse } from '../models/Elements';

export const fetchGroupsFromApi = async () => {
    return await FetchCall<GetGroupsUsersResponse>(GetAllGroupsOfUser("7538cf7e-6621-45c4-aa5a-9d74779086a6"));
};

export const updateGroupRulesInApi = async (group: Group) => {
    return await FetchCall<UpdateGroupUserResponse>(UpdateGroupRules(group));
};

export const runEvaluationApi = async (requestGenerator: RequestEvaluator) =>{
    return await FetchCall<RunEvaluationResponse>(RunEvaluationRules(requestGenerator))
}
