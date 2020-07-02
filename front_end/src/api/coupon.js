import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}
export function hotelMultipleMoneyAPI(data) {
    return axios({
        url:`${api.couponPre}/hotelMultipleCoupon`,
        method:'POST',
        data,
    })

}
export function hotelTimeMoneyAPI(data) {
    return axios({
        url:`${api.couponPre}/hotelTimeCoupon`,
        method:'POST',
        data,
    })
}
export function hotelBirthMoneyAPI(data) {
    return axios({
        url:`${api.couponPre}/hotelBirthCoupon`,
        method:'POST',
        data,
    })
}
export function deleteHotelCouponsAPI(hotelId){
    return axios({
        url:`${api.couponPre}/deleteHotelCoupons`,
        method:'POST',
        params:{hotelId:hotelId},
    })
}
export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}
export function getWebsiteCoupon1ListAPI() {
    return axios({
        url: `${api.couponPre}/getWebsiteCoupon1List`,
        method: 'GET',
    })
}
export function getWebsiteCoupon2ListAPI() {
    return axios({
        url: `${api.couponPre}/getWebsiteCoupon2List`,
        method: 'GET',
    })
}
export function getWebsiteCoupon3ListAPI() {
    return axios({
        url: `${api.couponPre}/getWebsiteCoupon3List`,
        method: 'GET',
    })
}
export function getWebsiteCoupon4ListAPI() {
    return axios({
        url: `${api.couponPre}/getWebsiteCoupon4List`,
        method: 'GET',
    })
}