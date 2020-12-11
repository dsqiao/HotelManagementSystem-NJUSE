import { axios } from '@/utils/request'
const api = {
    evaluatePre: '/api/evaluate',
}
export function evaluateAPI(data) {
    return axios({
        url: `${api.evaluatePre}/addEvaluation`,
        method: 'POST',
        data,
    })
}

export function getEvaluationAPI(hotelId) {
    return axios({
        url: `${api.evaluatePre}/${hotelId}/getEvaluation`,
        method: 'GET',
    })
}
