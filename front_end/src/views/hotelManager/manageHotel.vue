<!--管理酒店-->
<template>
    <div class="manageHotel-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"
                        ><a-icon type="plus" />添加酒店</a-button
                    >
                </div>
                <a-table :columns="columns1" :dataSource="hotelList" bordered>
                    <span slot="action" slot-scope="record">
                        <a-button
                            type="primary"
                            size="small"
                            @click="addRoom(record)"
                            >录入房间</a-button
                        >
                        <a-divider type="vertical"></a-divider>
                        <a-button
                            type="info"
                            size="small"
                            @click="showCoupon(record)"
                            >优惠策略</a-button
                        >
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该酒店吗？"
                            @confirm="deleteHotel(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small"
                                >删除酒店</a-button
                            >
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="订单管理" key="2">
                <a-table :columns="columns2" :dataSource="orderList" bordered>
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                        <span v-if="text == 'PresidentBed'">总统套房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">
                        <a-button size="small" @click="showOrder(record)"
                            >查看</a-button
                        >
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想执行该订单吗？"
                            @confirm="executeOrder(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button
                                type="primary"
                                size="small"
                                v-if="
                                    record.orderState === '已预订' ||
                                        record.orderState === '异常'
                                "
                                >执行</a-button
                            >
                        </a-popconfirm>
                        <a-divider
                            type="vertical"
                            v-if="
                                record.orderState === '已预订' ||
                                    record.orderState === '异常'
                            "
                        ></a-divider>

                        <a-popconfirm
                            title="确定想退房吗？"
                            @confirm="checkOut(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button
                                type="danger"
                                size="small"
                                v-if="record.orderState === '已入住'"
                                >退房</a-button
                            >
                        </a-popconfirm>
                        <a-divider
                            type="vertical"
                            v-if="record.orderState === '已入住'"
                        ></a-divider>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddHotelModal></AddHotelModal>
        <AddRoomModal></AddRoomModal>
        <Coupon></Coupon>
        <OrderDetailModal></OrderDetailModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddHotelModal from './components/addHotelModal'
import AddRoomModal from './components/addRoomModal'
import Coupon from './components/coupon'
import OrderDetailModal from '../user/components/orderDetailModal'
const moment = require('moment')
const columns1 = [
    {
        title: '酒店名',
        dataIndex: 'name',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '酒店星级',
        dataIndex: 'hotelStar',
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
]
const columns2 = [
    {
        title: '订单号',
        dataIndex: 'id',
    },
    {
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' },
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' },
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' },
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
    },
    {
        title: '最晚执行时间',
        dataIndex: 'latestTime',
    },
    {
        title: '订单状态',
        filters: [
            { text: '已预订', value: '已预订' },
            { text: '已撤销', value: '已撤销' },
            { text: '已入住', value: '已入住' },
            { text: '已退房', value: '已退房' },
            { text: '异常', value: '异常' },
        ],
        onFilter: (value, record) => record.orderState.includes(value),
        dataIndex: 'orderState',
        scopedSlots: { customRender: 'orderState' },
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
]
export default {
    name: 'manageHotel',
    data() {
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns1,
            columns2,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
        }
    },
    components: {
        AddHotelModal,
        AddRoomModal,
        Coupon,
        OrderDetailModal,
    },
    computed: {
        ...mapGetters([
            'userId',
            'orderList',
            'hotelList',
            'addHotelModalVisible',
            'addRoomModalVisible',
            'activeHotelId',
            'couponVisible',
            'addRoomParams',
        ]),
    },
    async mounted() {
        await this.set_managerId(Number(this.userId))
        await this.getManagedHotel({
            managerId: Number(this.userId),
        })
        await this.checkOverTimeOrders()
    },

    methods: {
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_activeHotelId',
            'set_activeOrderId',
            'set_managerId',
            'set_currentOrder',
            'set_orderDetailModalVisible',
        ]),
        ...mapActions([
            'getHotelList',
            'getAllOrders',
            'getHotelCoupon',
            'deleteHotelById',
            'getManagedHotel',
            'getManagedOrders',
            'executeOrderById',
            'updateManagerOverTimeOrders',
            'checkOutOrder',
            'getDistributedRooms',
        ]),

        addHotel() {
            this.set_addHotelModalVisible(true)
        },
        addRoom(record) {
            this.set_activeHotelId(record.id)
            this.set_addRoomModalVisible(true)
        },
        showCoupon(record) {
            this.set_activeHotelId(record.id)
            this.getHotelCoupon()
            this.set_couponVisible(true)
        },
        deleteHotel(record) {
            this.set_activeHotelId(record.id)
            this.deleteHotelById()
            this.getManagedHotelList({
                managerId: Number(this.userId),
            })
        },
        executeOrder(record) {
            this.set_activeOrderId(record.id)
            this.executeOrderById({
                activeOrderId: record.id,
                managerId: {
                    managerId: Number(this.userId),
                },
            })
        },
        checkOut(record) {
            this.set_activeOrderId(record.id)
            this.checkOutOrder(record.id)
        },
        checkOverTimeOrders() {
            this.updateManagerOverTimeOrders({
                userId: Number(this.userId),
                type: 'hotelManager',
            })
        },
        showOrder(record) {
            this.set_currentOrder(record)
            this.set_orderDetailModalVisible(true)
            this.getDistributedRooms(record.id)
        },
    },
}
</script>
<style scoped lang="less">
.manageHotel-wrapper {
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
.manageHotel-wrapper {
    .ant-tabs-bar {
        padding-left: 30px;
    }
}
</style>
<style lang="less"></style>
