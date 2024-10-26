import { Group } from '../models/Elements'
import { AxiosOptions } from './AxiosOptions'
import { RULE_ADMIN_URL, RULE_EVALUATOR_URL } from './apiConfig'
import { RequestEvaluator } from '../models/Elements'

const url_base = "http://localhost:8080"

export function GetAllGroupsOfUser(userId: String): AxiosOptions{
    let options: AxiosOptions = {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        },
        url: `${RULE_ADMIN_URL}/V1/Group/User/${userId}`,
        data: {}
    }
    return options
}

export function UpdateGroupRules(group: Group): AxiosOptions{
    let options: AxiosOptions = {
        method: 'put',
        headers: {
            'Content-Type': 'application/json'
        },
        url: `${RULE_ADMIN_URL}/V1/Group/update`,
        data: group
    }
    return options
}

export function RunEvaluationRules(requestGenerator: RequestEvaluator): AxiosOptions{
    let options: AxiosOptions = {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        url: `${RULE_EVALUATOR_URL}/in`,
        data: requestGenerator
    }
    return options
}