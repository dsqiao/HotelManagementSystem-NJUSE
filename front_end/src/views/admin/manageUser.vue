<!--管理user页面-->
<!-- 6.16 修改了删除网站营销人员的bug -->
<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="客户管理" key="2">
                <a-table
                        :columns="columns1"
                        :dataSource="clientList"
                        bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="确定想删除该客户吗？"
                                @confirm="deleteUsers(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除客户</a-button>
                        </a-popconfirm>
                        <a-button
                                type="primary"
                                size="small"
                                @click="modifyInfo(record)"
                                style="margin-left: 30px"
                        >
                            修改信息
                        </a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="酒店工作人员管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加酒店工作人员</a-button>
                </div>
                <a-table
                    :columns="columns2"
                    :dataSource="managerList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                            title="确定想删除该工作人员吗？"
                            @confirm="deleteUsers(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除酒店工作人员</a-button>
                        </a-popconfirm>
                        <a-button
                                type="primary"
                                size="small"
                                @click="modifyInfo(record)"
                                style="margin-left: 30px"
                        >
                            修改信息
                        </a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="网站营销人员管理" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addSalesman"><a-icon type="plus" />添加网站营销人员</a-button>
                </div>
                <a-table
                    :columns="columns2"
                    :dataSource="salesmanList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                            title="确定想删除该营销人员吗？"
                            @confirm="deleteUsers(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除营销人员</a-button>
                        </a-popconfirm>
                        <a-button
                                type="primary"
                                size="small"
                                @click="modifyInfo(record)"
                                style="margin-left: 30px"
                        >
                            修改信息
                        </a-button>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <AddSalesmanModal></AddSalesmanModal>
        <ModifyInfoModal></ModifyInfoModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import AddSalesmanModal from './components/addSalesmanModal'
import ModifyInfoModal from './components/modifyInfoModal'

const columns1 = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {  
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
];
const columns2 = [
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns1,
            columns2,
            data: [],
            form: this.$form.createForm(this, { name: 'manageUser' }),
        }
    },
    components: {
        ModifyInfoModal,
        AddSalesmanModal,
        AddManagerModal
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'addSalesmanModalVisible',
            'modifyInfoModalVisible',
            'managerList',
            'clientList',
            'salesmanList',
        ])
    },
    mounted() {
        this.getManagerList();
        this.getClientList();
        this.getSalesmanList();
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'getClientList',
            'getSalesmanList',
            'deleteUser',
        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_addSalesmanModalVisible',
            'set_modifyInfoModalVisible',
            'set_modifyingUserId',
            'set_modifyingUserInfo',
        ]),
        addManager(){
            this.set_addManagerModalVisible(true)
        },
        addSalesman(){
            this.set_addSalesmanModalVisible(true)
        },
        deleteUsers(record){
            // 删除三种类型的用户
            this.deleteUser(record);
        },
        modifyInfo(record) {
            this.set_modifyingUserInfo({
                userName: record.userName,
                email: record.email,
                password: record.password,
                phoneNumber: record.phoneNumber,
            });
            this.set_modifyingUserId(record.id);
            this.set_modifyInfoModalVisible(true)
        },
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>