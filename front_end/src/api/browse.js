import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
//添加根据商圈和地址选择
export function getHotelByConditionAPI(params){
    return axios({
        url:`${api.hotelPre}/selected`,
        method:'GET',
        params,
    })
}

//默认排序
export function sortByDefaultAPI(params){
    return axios({
        url:`${api.hotelPre}/defaultSorted`,
        method:'GET',
        params
    })
}

//根据评价人数由高到低排序
export function sortByEvaluatePeopleAPI(params){
    return axios({
        url:`${api.hotelPre}/evaluationSorted`,
        method:'GET',
        params
    })
}

//根据酒店评分排序
export function sortByScoreAPI(params){
    return axios({
        url:`${api.hotelPre}/scoreSorted`,
        method:'GET',
        params
    })
}

//根据酒店星级排序
export function sortByStarAPI(params){
    return axios({
        url:`${api.hotelPre}/starSorted`,
        method:'GET',
        params
    })
}

