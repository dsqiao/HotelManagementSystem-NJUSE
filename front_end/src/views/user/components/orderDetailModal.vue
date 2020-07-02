<template>
    <a-modal
        :visible="orderDetailModalVisible"
        title="订单详情"
        :footer="null"
        @cancel="ok"
    >
        <a-form :form="form" v-bind="formItemLayout">
            <a-form-item label="订单号">
                <span>{{currentOrder.id}}</span>
            </a-form-item>
            <a-form-item label="酒店名">
                <span>{{currentOrder.hotelName}}</span>
            </a-form-item>
            <a-form-item label="房型">
                <span>
                    <span v-if="currentOrder.roomType === 'BigBed'">大床房</span>
                    <span v-if="currentOrder.roomType === 'DoubleBed'">双床房</span>
                    <span v-if="currentOrder.roomType === 'Family'">家庭房</span>
                    <span v-if="currentOrder.roomType === 'PresidentBed'">总统套房</span>
                </span>
            </a-form-item>
            <a-form-item label="入住日期">
                <span>{{currentOrder.checkInDate}}</span>
            </a-form-item>
            <a-form-item label="离店日期">
                <span>{{currentOrder.checkOutDate}}</span>
            </a-form-item>
            <a-form-item label="客户名">
                <span>{{currentOrder.clientName}}</span>
            </a-form-item>
            <a-form-item label="入住人数">
                <span>{{currentOrder.peopleNum}}</span>
            </a-form-item>
            <a-form-item label="有无儿童">
                <span v-if="currentOrder.haveChild">有</span>
                <span v-else>无</span>
            </a-form-item>
            <a-form-item label="总价">
                <span>¥ {{currentOrder.price}}</span>
            </a-form-item>
            <a-form-item label="房间号">
                <span v-for="item in distributedRooms" :key="item">
                    {{item}}
                </span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import {mapGetters, mapMutations,mapActions} from "vuex";

    export default {
        name: "orderDetailModal",
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 12},
                        sm: {span: 6},
                    },
                    wrapperCol: {
                        xs: {span: 24},
                        sm: {span: 16},
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'currentOrder',
                'orderDetailModalVisible',
                'distributedRooms'
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'orderDetailModal' });
            
        },
        methods: {
            ...mapMutations([
                'set_orderDetailModalVisible',
            ]),
            ok: function () {
                this.set_orderDetailModalVisible(false);
            }
        }
    }
</script>

<style scoped>

</style>