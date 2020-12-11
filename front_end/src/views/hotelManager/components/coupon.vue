<!--优惠策略-->
<template>
    <div>
        <a-modal
            :visible="couponVisible"
            title="优惠策略"
            width="900px"
            :footer="null"
            @cancel="cancel"
        >
            <div style="width:100%;text-align:right;margin:20px 0">
                <a-button type="primary" @click="addCoupon">
                    <a-icon type="plus"> </a-icon>
                </a-button>
            </div>
            <a-table :columns="columns" :data-source="couponList" bordered="">
                <a-tag color="red" slot="couponName" slot-scope="text">{{
                    text
                }}</a-tag>
            </a-table>
            <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button type="primary" @click="deleteAllCoupons"
                    ><a-icon type="delete" /> 删除所有策略</a-button
                >
            </div>
        </a-modal>
        <AddCoupon></AddCoupon>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddCoupon from './addCoupon'

const columns = [
    {
        title: '券名',
        dataIndex: 'couponName',
        scopedSlots: { customReader: 'couponName' },
    },
    {
        title: '优惠简介',
        dataIndex: 'description',
    },
    {
        title: '优惠金额',
        dataIndex: 'discountMoney',
    },
    {
        title: '优惠折扣',
        dataIndex: 'discount',
    },
    // 这里定义列表头
]
export default {
    name: 'coupon',
    data() {
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            form: this.$form.createForm(this, { name: 'coupon' }),
        }
    },
    components: {
        AddCoupon,
    },
    computed: {
        ...mapGetters(['couponVisible', 'couponList']),
    },
    methods: {
        ...mapMutations(['set_addCouponVisible', 'set_couponVisible']),
        ...mapActions(['getHotelCoupon', 'deleteHotelCoupons']),
        cancel() {
            this.set_couponVisible(false)
        },
        addCoupon() {
            this.set_addCouponVisible(true)
        },
        deleteAllCoupons() {
            this.deleteHotelCoupons()
        },
    },
}
</script>
<style scoped></style>
