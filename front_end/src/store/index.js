import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import hotel from './modules/hotel'
import hotelManager from './modules/hotelManager'
import salesman from './modules/salesman'
import admin from './modules/admin'
import credit from './modules/credit'
import getters from './getters'
Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        hotel,
        user,
        hotelManager,
        admin,
        salesman,
        credit,
    },
    state: {},
    mutations: {},
    actions: {},
    getters,
})
