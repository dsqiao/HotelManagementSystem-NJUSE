import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}
export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}
export function searchHotelsAPI(params){
    return axios({
        url: `${api.hotelPre}/searchHotels`,
        method:'get',
        params,
    })
}


export function getDistributedRoomsAPI(orderId){
    return axios({
        url: `${api.hotelPre}/${orderId}/getDistributedRooms`,
        method:'GET',
    })
}

export function updateRestRoomsAPI(params){
    return axios({
        url: `${api.hotelPre}/updateRestRooms`,
        method:'POST',
        params
    })
}
