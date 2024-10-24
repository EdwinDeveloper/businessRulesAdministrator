export interface GetGroupsUsersResponse extends DefaultResponse{
  data: Group[]
}

export interface Rule {
    id: string;
    userId: string;
    conditions: string;
    name: string;
    priority: string;
    nextTrue: string;
    nextFalse: string;
}

export interface Group{
  user_id: string;
  id: string;
  group_name: string;
  run_type: string;
  rules: Rule[];
}

export interface DefaultResponse {
  status: number
}