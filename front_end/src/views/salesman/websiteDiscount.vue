<template>
    <div class="newStrategy-wrapper">
        <a-tabs>
            <a-tab-pane tab="满减特惠" key="3">
                <div style="width: 100%; text-align: right; margin: 20px 0">
                    <a-button type="primary" @click="addCoupon3">
                        <a-icon type="plus" />添加满减特惠
                    </a-button>
                </div>
                <a-table
                    :columns="column3"
                    :data-source="websiteCoupon3List"
                ></a-table>
            </a-tab-pane>
            <a-tab-pane tab="限时特惠" key="4">
                <div style="width: 100%; text-align: right; margin: 20px 0">
                    <a-button type="primary" @click="addCoupon4">
                        <a-icon type="plus" />添加限时优惠
                    </a-button>
                </div>
                <a-table :columns="column4" :data-source="websiteCoupon4List">
                    <span slot="discount" slot-scope="text">
                        <span>{{ text }} 折</span>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddCoupon1Modal></AddCoupon1Modal>
        <AddCoupon2Modal></AddCoupon2Modal>
        <AddCoupon3Modal></AddCoupon3Modal>
        <AddCoupon4Modal></AddCoupon4Modal>
    </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from 'vuex'
import AddCoupon1Modal from './components/addCoupon1Modal'
import AddCoupon2Modal from './components/addCoupon2Modal'
import AddCoupon3Modal from './components/addCoupon3Modal'
import AddCoupon4Modal from './components/addCoupon4Modal'

// 生日优惠
const column1 = [
    {
        title: '优惠名称',
        dataIndex: 'couponName',
    },
    {
        title: '优惠描述',
        dataIndex: 'description',
    },
]
// 多间优惠
const column2 = [
    {
        title: '优惠名称',
        dataIndex: 'couponName',
    },
    {
        title: '优惠描述',
        dataIndex: 'description',
    },
]
// 满减优惠
const column3 = [
    {
        title: '优惠名称',
        dataIndex: 'couponName',
    },
    {
        title: '优惠描述',
        dataIndex: 'description',
    },
    {
        title: '目标金额',
        dataIndex: 'targetMoney',
    },
    {
        title: '减免金额',
        dataIndex: 'discountMoney',
    },
]
// 限时优惠
const column4 = [
    {
        title: '优惠名称',
        dataIndex: 'couponName',
    },
    {
        title: '优惠描述',
        dataIndex: 'description',
    },
    {
        title: '起始时间',
        dataIndex: 'startTime',
    },
    {
        title: '结束时间',
        dataIndex: 'endTime',
    },
    {
        title: '折扣',
        dataIndex: 'discount',
        scopedSlots: { customRender: 'discount' },
    },
]
export default {
    components: {
        AddCoupon4Modal,
        AddCoupon3Modal,
        AddCoupon2Modal,
        AddCoupon1Modal,
    },
    data() {
        return {
            column1,
            column2,
            column3,
            column4,
        }
    },
    name: 'websiteDiscount',
    computed: {
        ...mapGetters([
            'websiteCoupon1List',
            'websiteCoupon2List',
            'websiteCoupon3List',
            'websiteCoupon4List',
        ]),
    },
    mounted() {
        this.getCoupon1List()
        this.getCoupon2List()
        this.getCoupon3List()
        this.getCoupon4List()
    },
    methods: {
        ...mapMutations([
            'set_addCoupon1Visible',
            'set_addCoupon2Visible',
            'set_addCoupon3Visible',
            'set_addCoupon4Visible',
        ]),
        ...mapActions([
            'getCoupon1List',
            'getCoupon2List',
            'getCoupon3List',
            'getCoupon4List',
        ]),
        // 添加生日特惠
        addCoupon1() {
            this.set_addCoupon1Visible(true)
        },

        // 添加多间特惠
        addCoupon2() {
            this.set_addCoupon2Visible(true)
        },

        // 添加满减特惠
        addCoupon3() {
            this.set_addCoupon3Visible(true)
        },

        // 添加限时特惠
        addCoupon4() {
            this.set_addCoupon4Visible(true)
        },
    },
}
</script>

<style scoped lang="less">
.newStrategy-wrapper {
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
.newStrategy-wrapper {
    .ant-tabs-bar {
        padding-left: 30px;
    }
}
</style>
