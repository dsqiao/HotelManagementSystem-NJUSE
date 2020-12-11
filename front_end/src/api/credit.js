import { axios } from '@/utils/request'

const api = {
    creditPre: '/api/credit',
}
export function getUserCreditAPI(userId) {
    return axios({
        url: `${api.creditPre}/${userId}/userCredit`,
        method: 'GET',
    })
}
export function updateCreditAPI(params) {
    return axios({
        url: `${api.creditPre}/updateCredit`,
        method: 'POST',
        params,
    })
}
export function getUserInfoAPI(id) {
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET',
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data,
    })
}
export function beMemberAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/beMember`,
        method: 'POST',
        data,
    })
}
