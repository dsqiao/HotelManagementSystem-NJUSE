import {
    addRoomAPI,
    addHotelAPI,
    deleteHotelAPI,
} from '@/api/hotelManager'
import {
    getAllOrdersAPI,
    getManagedOrdersAPI,
    executeOrderAPI,
    updateOverTimeOrdersAPI,
    checkOutOrderAPI
} from '@/api/order'
import {
    hotelAllCouponsAPI,
    hotelTargetMoneyAPI,
    hotelMultipleMoneyAPI,
    hotelTimeMoneyAPI,
    hotelBirthMoneyAPI,
    deleteHotelCouponsAPI
} from '@/api/coupon'
import { message } from 'ant-design-vue'

const hotelManager = {
    state: {
        orderList: [],
        managerId:0,
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
        },
        addHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        activeHotelId: 0,
        couponList: [],
        activeOrderId:0,
        type:0
    },
    mutations: {
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        },
        set_activeOrderId: function(state,data){
            state.activeOrderId=data
        },
        set_managerId:function(state,data){
            state.managerId=data
        },
        set_type:function(state,data){
            state.type=data
        }
    },
    actions: {
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        getManagedOrders:async({state,commit},data)=>{
            const res=await getManagedOrdersAPI(data)
            if(res){
                commit('set_orderList',res)
            }
        },
        addHotel: async({ state, dispatch, commit },data) => {
            const res = await addHotelAPI(state.addHotelParams)
            if(res){
                dispatch('getHotelList')
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        deleteHotelById:async ({state,dispatch,commit})=>{
            const res=await deleteHotelAPI({
                hotelId:state.activeHotelId
            })
            if(res){
                message.success('删除成功')
                dispatch('getHotelList')
            }else{
                message.error('删除失败')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams)
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                })
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId);
            console.log(res);
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList', res)
            }
        },
        deleteHotelCoupons: async({state,commit})=>{
            const res=await deleteHotelCouponsAPI(state.activeHotelId);
            console.log(res)
            if(res){
                commit('set_couponList',res)
            }
        },
        addHotelCoupon: async({ commit, dispatch }, data) => {
            let res;
            if(data.type===3) { res = await hotelTargetMoneyAPI(data);}
            else if(data.type===4){res= await hotelTimeMoneyAPI(data);}
            else if(data.type===2){
                res=await  hotelMultipleMoneyAPI(data);
            }
            else if(data.type===1){
                res=await hotelBirthMoneyAPI(data);
            }
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                commit('set_addCouponVisible', false)
                commit('set_couponVisible', true)
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        executeOrderById: async ({commit},param)=>{
            const res=await executeOrderAPI(param.activeOrderId)
            if(res){
                message.success('执行成功')
                commit('set_orderList',res)
            }
        } ,
        updateManagerOverTimeOrders:async({state,commit,dispatch},param)=>{
            const res=await updateOverTimeOrdersAPI(param)
            console.log(res)
            if(res){
                commit('set_orderList',res)
            }
        },
        checkOutOrder:async({state,dispatch},id)=>{
            const res=await checkOutOrderAPI(id)
            if(res){
                dispatch('getManagedOrders')
                message.success('退房成功')
            }
        }
    }
}
export default hotelManager