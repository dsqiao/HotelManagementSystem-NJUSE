import {
    getManagerListAPI,
    getClientListAPI,
    getSalesmanListAPI,
    deleteUserAPI,
    addManagerAPI,
    addSalesmanAPI
} from '@/api/admin'
import { updateUserInfoAPI } from '@/api/user'
import { message } from 'ant-design-vue'
import modifyInfoModal from "../../views/admin/components/modifyInfoModal";

const admin = {
    state: {
        managerList: [

        ],
        clientList: [

        ],
        salesmanList: [

        ],
        addManagerModalVisible: false,
        addSalesmanModalVisible: false,
        modifyInfoModalVisible: false,
        addManagerParams: {
            email:'',
            password:''
        },
        modifyingUserInfo: {
            userName: '',
            email: '',
            password: '',
        },
        modifyingUserId: '',
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_clientList: function(state, data) {
            state.clientList = data
        },
        set_salesmanList: function(state, data) {
            state.salesmanList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addSalesmanModalVisible: function(state, data) {
            state.addSalesmanModalVisible = data
        },
        set_modifyInfoModalVisible: function(state, data) {
            state.modifyInfoModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        // 修改当前正在被修改信息的用户的id
        set_modifyingUserId: function(state, data) {
            state.modifyingUserId = data;
        },
        // 修改当前正在被修改信息的用户的信息
        set_modifyingUserInfo: function(state, data) {
            state.modifyingUserInfo = {
                ...state.modifyingUserInfo,
                ...data,
            }
        },
        set_addSalesmanParams: function (state, data) {
            state.addSalesmanParams = {
                ...state.addSalesmanParams,
                ...data,
            }
        }
    },
    actions: {
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI();
            if(res){
                commit('set_managerList', res)
            }
        },
        getClientList: async({ commit}) => {
            const res = await getClientListAPI();
            if (res) {
                commit('set_clientList', res);
            }
        },
        getSalesmanList: async ({commit}) => {
            const res = await getSalesmanListAPI();
            if (res) {
                commit('set_salesmanList', res);
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    userType:'',
                    email:'',
                    password:''
                });
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        addSalesman: async ({ state, commit, dispatch }) => {
            const res = await addSalesmanAPI(state.addSalesmanParams);
            if (res) {
                commit('set_addSalesmanParams', {
                    userType: '',
                    email: '',
                    password: ''
                });
                commit('set_addSalesmanModalVisible', false);
                message.success('添加成功');
                dispatch('getSalesmanList');
            } else {
                message.error('添加失败');
            }
        },
        deleteUser: async ({state, dispatch}, commit) => {
            console.log(commit);
            const res = await deleteUserAPI({
                userId: commit.id
            });
            if (res) {
                message.success('删除成功');
                // 删除之后更新页面
                dispatch('getManagerList');
                dispatch('getClientList');
                dispatch('getSalesmanList');
            } else {
                message.error('删除失败');
            }
        },
        async modifyInfo ({state, dispatch}, data) {
            // console.log(JSON.stringify(data));
            const params = {
                id: state.modifyingUserId,
                ...data,
            };
            const res = await updateUserInfoAPI(params);
            if (res) {
                message.success("修改成功");
                dispatch('getManagerList');
                dispatch('getClientList');
                dispatch('getSalesmanList');
            } else {
                message.error("修改失败");
            }
        },
    }
}
export default admin