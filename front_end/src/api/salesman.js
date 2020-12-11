import { axios } from '@/utils/request'
const api = {
    salesmanPre: 'api/salesman',
}
export function getAllUserAPI() {
    return axios({
        url: `${api.salesmanPre}/getAllUser`,
        method: 'POST',
    })
}
export function rechargeAPI(data) {
    return axios({
        url: `${api.salesmanPre}/recharge`,
        method: 'POST',
        data,
    })
}
