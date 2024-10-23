import { AxiosOptions } from './AxiosOptions'

const url_base = "http://localhost:8080"

export function getAllGroupsOfUser(userId: String): AxiosOptions{
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