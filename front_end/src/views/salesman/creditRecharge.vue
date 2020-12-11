<template>
    <div class="creditRecharge-wrapper">
        <a-tabs>
            <a-tab-pane tab="信用充值" key="1">
                <a-table :columns="column" :dataSource="userList" bordered>
                    <span slot="action" slot-scope="record">
                        <a-button
                            type="primary"
                            size="small"
                            @click="creditRecharge(record)"
                        >
                            充值信用
                        </a-button>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <RechargeModal></RechargeModal>
    </div>
</template>
<script>
import { mapActions, mapGetters, mapMutations } from 'vuex'
import RechargeModal from '../salesman/components/rechargeModal.vue'
const column = [
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '信用',
        dataIndex: 'credit',
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
]
export default {
    name: 'creditRecharge',
    components: {
        RechargeModal,
    },
    data() {
        return {
            formLayout: 'horizontal',
            column,
        }
    },
    computed: {
        ...mapGetters(['userList', 'rechargeModalVisible']),
    },
    async mounted() {
        this.getUserList()
    },
    methods: {
        ...mapActions(['getUserList']),
        ...mapMutations(['set_rechargeModalVisible', 'set_currentClient']),
        creditRecharge(record) {
            this.set_currentClient(record)
            this.set_rechargeModalVisible(true)
        },
    },
}
</script>
<style scoped lang="less">
.creditRecharge-wrapper {
    padding: 50px;
    .chart {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-top: 20px;
    }
}
</style>
<style lang="less">
.creditRecharge-wrapper {
    .ant-tabs-bar {
        padding-left: 30px;
    }
}
</style>
