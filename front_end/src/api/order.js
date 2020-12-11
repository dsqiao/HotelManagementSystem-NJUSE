import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order',
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}

export function executeOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/executeOrder`,
        method: 'POST',
    })
}

export function getManagedOrdersAPI(param) {
    return axios({
        url: `${api.orderPre}/${param.managerId}/managedOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}

export function updateOverTimeOrdersAPI(params) {
    return axios({
        url: `${api.orderPre}/updateOverTimeOrders`,
        method: 'GET',
        params,
    })
}

export function checkOutOrderAPI(id) {
    return axios({
        url: `${api.orderPre}/${id}/checkOutOrder`,
        method: 'POST',
    })
}
