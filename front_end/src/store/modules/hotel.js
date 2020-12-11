import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    searchHotelsAPI,
    getDistributedRoomsAPI,
    updateRestRoomsAPI,
} from '@/api/hotel'
import { reserveHotelAPI } from '@/api/order'
import { orderMatchCouponsAPI } from '@/api/coupon'
import {
    getHotelByConditionAPI,
    sortByDefaultAPI,
    sortByEvaluatePeopleAPI,
    sortByScoreAPI,
    sortByStarAPI,
} from '@/api/browse'
import { getHotelListByManagerIdAPI } from '@/api/hotelManager'
import { evaluateAPI, getEvaluationAPI } from '@/api/evaluate'
import { isEmptyObject } from 'ant-design-vue/lib/vc-form/src/utils'

const moment = require('moment')

const hotel = {
    state: {
        hotelList: [],
        searchList: {
            address: 'null',
            name: 'null',
            bizRegion: 'null',
            lowStar: -1,
            highStar: -1,
            roomNum: -1,
            type: -1,
            lowPrice: -1,
            highPrice: -1,
            InDate: 'null',
            OutDate: 'null',
        },
        hotelListParams: {
            pageNo: 0,
            pageSize: 12,
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {},
        ordered: false,
        address: '全部',
        bizRegion: '全部',
        orderModalVisible: false,
        evaluateModalVisible: false,
        currentOrderRoom: {},
        orderMatchCouponList: [],
        distributedRooms: [],
        datePickVisible: false,

        startDate: moment().format('YYYY-MM-DD'),
        endDate: moment(new Date())
            .add(1, 'weeks')
            .format('YYYY-MM-DD'),
    },
    mutations: {
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_currentBizRegion: function(state, data) {
            state.bizRegion = data
        },
        set_currentAddress: function(state, data) {
            state.address = data
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },

        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_ordered: function(state, data) {
            state.ordered = data
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_searchList: function(state, data) {
            state.searchList = data
            console.log(data)
        },
        set_evaluateModalVisible: function(state, data) {
            state.evaluateModalVisible = data
        },
        set_distributedRooms: function(state, data) {
            state.distributedRooms = data
        },
        set_datePickVisible: function(state, data) {
            state.datePickVisible = data
        },
        set_startDate: function(state, data) {
            state.startDate = data
        },
        set_endDate: function(state, data) {
            state.endDate = data
        },
    },

    actions: {
        getHotelList: async ({ commit, state }) => {
            if (isEmptyObject(state.hotelList)) {
                const res = await getHotelsAPI()
                if (res) {
                    console.log(res)
                    commit('set_hotelList', res)
                    commit('set_hotelListLoading', false)
                }
            } else {
            }
        },
        getHotelById: async ({ commit, state }) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId,
            })
            if (res) {
                commit('set_currentHotelInfo', res)
            }
        },
        searchHotels: async ({ commit, state }, data) => {
            console.log(state.searchList)
            const res = await searchHotelsAPI(state.searchList)
            if (res) {
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
                if (isEmptyObject(res)) {
                    alert('没有匹配的酒店')
                }
            } else {
                commit('set_hotelList', res)
                console.log('wrong')
            }
        },
        addOrder: async ({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            console.log(res)
            if (res) {
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async ({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if (res) {
                commit('set_orderMatchCouponList', res)
                console.log(res)
            }
        },
        getHotelByCondition: async ({ state, commit }, data) => {
            const res = await getHotelByConditionAPI({
                bizRegion: state.bizRegion,
                address: state.address,
                ordered: state.ordered,
                userId: data.userId,
            })
            if (res) {
                commit('set_hotelList', res)
            }
        },
        sortByDefault: async ({ state, commit }, data) => {
            const res = await sortByDefaultAPI({
                bizRegion: state.bizRegion,
                address: state.address,
                ordered: state.ordered,
                userId: data.userId,
            })
            if (res) {
                commit('set_hotelList', res)
            }
        },
        sortByEvaluatePeople: async ({ state, commit }, data) => {
            const res = await sortByEvaluatePeopleAPI({
                bizRegion: state.bizRegion,
                address: state.address,
                ordered: state.ordered,
                userId: data.userId,
            })
            if (res) {
                commit('set_hotelList', res)
            }
        },
        sortByScore: async ({ state, commit }, data) => {
            const res = await sortByScoreAPI({
                bizRegion: state.bizRegion,
                address: state.address,
                ordered: state.ordered,
                userId: data.userId,
            })
            if (res) {
                commit('set_hotelList', res)
            }
        },
        sortByStar: async ({ state, commit }, data) => {
            const res = await sortByStarAPI({
                bizRegion: state.bizRegion,
                address: state.address,
                ordered: state.ordered,
                userId: data.userId,
            })
            if (res) {
                commit('set_hotelList', res)
            }
        },
        addEvaluation: async ({ state, commit }, data) => {
            const res = await evaluateAPI(data)
            console.log(res)
            if (res) {
                message.success('评论成功')
                commit('set_evaluateModalVisible', false)
            }
        },
        getManagedHotel: async ({ state, commit }, data) => {
            const res = await getHotelListByManagerIdAPI(data)
            // console.log(res)
            if (res) {
                console.log(res)
                commit('set_hotelList', res)
            }
        },
        getDistributedRooms: async ({ state, commit }, orderId) => {
            const res = await getDistributedRoomsAPI(orderId)
            if (res) {
                commit('set_distributedRooms', res)
                console.log(state.distributedRooms)
            }
        },
        updateRestRooms: async ({ state, commit, dispatch }, hotelId) => {
            const params = {
                hotelId: hotelId,
                checkInDate: state.startDate,
                checkOutDate: state.endDate,
            }
            const res = await updateRestRoomsAPI(params)
            if (res) {
                commit('set_currentHotelInfo', res)
            }
        },
    },
}

export default hotel
