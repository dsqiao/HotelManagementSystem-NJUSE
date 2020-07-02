import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelListByManagerIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.managerId}/managedHotel`,
        method: 'GET',
    })
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}

export function deleteHotelAPI(param){
    return axios({
        url:`${api.hotelPre}/${param.hotelId}/deleteHotel`,
        method:'POST',
    })
}
