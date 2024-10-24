import { Group } from '../models/Elements'
import { AxiosOptions } from './AxiosOptions'

const url_base = "http://localhost:8080"

export function GetAllGroupsOfUser(userId: String): AxiosOptions{
    let options: AxiosOptions = {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        },
        url: `${url_base}/V1/Group/User/${userId}`,
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
        url: `${url_base}/V1/Group/update`,
        data: group
    }
    return options
}