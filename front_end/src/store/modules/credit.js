import { getUserCreditAPI, updateCreditAPI } from "../../api/credit";
const credit = {
    state: {
        creditList:[]
    },
    mutations: {
        set_creditList: function (state, data) {
            state.creditList = data;
        },
    },
    actions: {
        getUserCredit:async({state,commit},userId)=>{
            const res=await getUserCreditAPI(userId)
            if(res){
                commit('set_creditList',res)
            }
        },
        updateCredit:async({state,commit},params)=>{
            const res=await updateCreditAPI(params)
            if(res){

            }
        }
    }
};
export default credit