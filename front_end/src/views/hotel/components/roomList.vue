<!--房间列表-->
<template>
    <div class="room-list">
        <div class="filter"></div>
        <div class="list">
            <a-table :columns="columns" :dataSource="rooms">
                <span slot="customRest" style="text-align:center">
                    剩余可预订房间数量
                    <a-button
                        size="small"
                        style="margin-left:8px"
                        @click="datePick"
                    >
                        日期
                        <a-icon type="calendar" />
                    </a-button>
                </span>

                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="record">
                    <a-button type="primary" @click="order(record)"
                        >预定</a-button
                    >
                </span>
            </a-table>
        </div>
        <a-modal
            :visible="datePickVisible"
            title="日期选择"
            :footer="null"
            @cancel="ok"
        >
            <a-label>计划入住时间：</a-label>
            <a-date-picker
                @change="onStart"
                size="medium"
                placeholder="入住时间"
                style="margin-left: 16px"
                :defaultValue="startDate"
            />
            <br />
            <br />
            <a-label>计划离店时间：</a-label>
            <a-date-picker
                @change="onEnd"
                size="medium"
                placeholder="离店时间"
                style="margin-left:16px;"
                :defaultValue="endDate"
            />
            <br />
            <a-button
                @click="ok"
                type="primary"
                style="margin-top: 30px; margin-left: 320px"
                >查看剩余房间数量</a-button
            >
        </a-modal>
        <OrderModal></OrderModal>
    </div>
</template>
<script>
const moment = require('moment')
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
const columns = [
    {
        title: '房型',
        dataIndex: 'roomType',
        key: 'roomType',
    },
    {
        key: 'rest',
        slots: { title: 'customRest' },
        scopedSlots: { customRender: 'rest' },
        dataIndex: 'rest',
    },
    {
        title: '房间价格',
        key: 'price',
        dataIndex: 'price',
        scopedSlots: { customRender: 'price' },
    },
    {
        title: 'Action',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
]
export default {
    name: 'roomList',
    props: {
        rooms: {
            type: Array,
        },
    },
    data() {
        return {
            columns,
        }
    },
    components: {
        OrderModal,
    },
    async mounted() {
        this.updateRestRooms(Number(this.currentHotelId))
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'datePickVisible',
            'currentHotelInfo',
            'currentHotelId',
            'startDate',
            'endDate',
        ]),
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom',
            'set_datePickVisible',
            'set_startDate',
            'set_endDate',
        ]),
        ...mapActions(['updateRestRooms']),
        order(record) {
            this.set_currentOrderRoom(record)
            this.set_orderModalVisible(true)
        },
        datePick() {
            this.set_datePickVisible(true)
        },
        ok() {
            this.set_datePickVisible(false)
            this.updateRestRooms(Number(this.currentHotelId))
            this.$router.go(0)
        },
        onStart(date, dateString) {
            this.set_startDate(dateString)
        },
        onEnd(date, dateString) {
            this.set_endDate(dateString)
        },
    },
}
</script>
