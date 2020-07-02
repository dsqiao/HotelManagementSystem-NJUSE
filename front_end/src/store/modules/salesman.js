// 6.16 修改了充值信用后不刷新的bug
import { getAllUserAPI, rechargeAPI } from "../../api/salesman";
import {
    getWebsiteCoupon1ListAPI,
    getWebsiteCoupon2ListAPI,
    getWebsiteCoupon3ListAPI,
    getWebsiteCoupon4ListAPI,
} from "../../api/coupon";
import {
    hotelTargetMoneyAPI,
    hotelMultipleMoneyAPI,
    hotelTimeMoneyAPI,
    hotelBirthMoneyAPI,
} from "../../api/coupon"
import { message } from 'ant-design-vue';
const salesman = {
    state: {
        userList: [],
        rechargeModalVisible: false,
        currentClient: '',
        websiteCoupon1List: [], // 网站生日特惠
        websiteCoupon2List: [], // 网站多间特惠
        websiteCoupon3List: [], // 网站满减特惠
        websiteCoupon4List: [], // 网站限时特惠
        addCoupon1Visible: false,
        addCoupon2Visible: false,
        addCoupon3Visible: false,
        addCoupon4Visible: false,
    },
    mutations: {
        set_userList: function (state, data) {
            state.userList = data;
        },
        set_rechargeModalVisible: function (state, data) {
            state.rechargeModalVisible = data;
        },
        set_currentClient: function (state, data) {
            state.currentClient = data;
        },
        set_websiteCoupon1List: function (state, data) {
            state.websiteCoupon1List = data;
        },
        set_websiteCoupon2List: function (state, data) {
            state.websiteCoupon2List = data;
        },
        set_websiteCoupon3List: function (state, data) {
            state.websiteCoupon3List = data;
        },
        set_websiteCoupon4List: function (state, data) {
            state.websiteCoupon4List = data;
        },
        set_addCoupon1Visible: function (state, data) {
            state.addCoupon1Visible = data;
        },
        set_addCoupon2Visible: function (state, data) {
            state.addCoupon2Visible = data;
        },
        set_addCoupon3Visible: function (state, data) {
            state.addCoupon3Visible = data;
        },
        set_addCoupon4Visible: function (state, data) {
            state.addCoupon4Visible = data;
        },
    },
    actions: {
        getUserList: async function({state, commit}){
            const res = await getAllUserAPI();
            if (res) {
                commit('set_userList', res);
            }
        },
        recharge: async function({state, commit, dispatch}, data) {
            const res = await rechargeAPI(data);
            if (res) {
                message.success('充值成功');
                dispatch('getUserList');
            } else {
                message.error('充值失败');
            }
        },
        getCoupon1List: async function({state, commit}) {
            const res = await getWebsiteCoupon1ListAPI();
            console.log("执行");
            if (res) {
                console.log(res);
                commit('set_websiteCoupon1List', res);
            }
        },
        getCoupon2List: async function({state, commit}) {
            const res = await getWebsiteCoupon2ListAPI();
            if (res) {
                commit('set_websiteCoupon2List', res);
            }
        },
        getCoupon3List: async function({state, commit}) {
            const res = await getWebsiteCoupon3ListAPI();
            if (res) {
                commit('set_websiteCoupon3List', res);
            }
        },
        getCoupon4List: async function({state, commit}) {
            const res = await getWebsiteCoupon4ListAPI();
            if (res) {
                commit('set_websiteCoupon4List', res);
            }
        },
        addCoupon3: async function({commit, dispatch}, data) {
            let res = await hotelTargetMoneyAPI(data);
            if (res) {
                dispatch('getCoupon3List');
                commit('set_addCoupon3Visible', false);
                message.success('添加成功');
            } else {
                message.error('添加失败');
            }
        },
        addCoupon4: async function({commit, dispatch}, data) {
            let res = await hotelTimeMoneyAPI(data);
            if (res) {
                dispatch('getCoupon4List');
                commit('set_addCoupon4Visible', false);
                message.success('添加成功');
            } else {
                message.error('添加失败');
            }
        },
    }
};
export default salesman