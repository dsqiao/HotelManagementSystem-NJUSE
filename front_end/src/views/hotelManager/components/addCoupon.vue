<!--添加优惠策略-->
<template>
    <a-modal
            :visible="addCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="提交"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
        <a-tabs>
            <a-tab-pane tab="生日特惠" key="1" >

                <a-form-item label="券名" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写券名"
                            v-decorator="['name', {rules: [{required: true, message: '请填写券名'}]}]"
                    />
                </a-form-item>
                <a-form-item label="优惠简介" v-bind="formItemLayout">
                    <a-input
                            type="textarea"
                            :rows="4"
                            placeholder="请填写优惠简介"
                            v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介'}] }]"
                    />
                </a-form-item>

                <a-form-item label="优惠金额" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠金额"
                            v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] } ]"
                    />
                </a-form-item>
                <a-form-item label="优惠折扣" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠折扣"
                            v-decorator="['discount', { rules: [{ required: true, message: '请填写优惠折扣' }] } ]"
                    />
                </a-form-item>
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="setType(1)"><a-icon type="select"/>
                        确定</a-button>
                </div>
            </a-tab-pane>
            <a-tab-pane tab="多间优惠" key="2">
                <a-form-item label="券名" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写券名"
                            v-decorator="['name', {rules: [{required: true, message: '请填写券名'}]}]"
                    />
                </a-form-item>
                <a-form-item label="优惠简介" v-bind="formItemLayout">
                    <a-input
                            type="textarea"
                            :rows="4"
                            placeholder="请填写优惠简介"
                            v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介'}] }]"
                    />
                </a-form-item>
                <a-form-item label="房间数量" v-bind="formItemLayout">
                    <a-input-number size="large" :min="0" :max="10000"  @change="onChange" v-decorator="['NumOfRoom', { rules: [{ required: true, message: '请填写房间数量' }]}]"/>
                </a-form-item>

                <a-form-item label="优惠金额" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠金额"
                            v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] } ]"
                    />
                </a-form-item>
                <a-form-item label="优惠折扣" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠折扣"
                            v-decorator="['discount', { rules: [{ required: true, message: '请填写优惠折扣' }] } ]"
                    />
                </a-form-item>
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="setType(2)"><a-icon type="select"/>确定</a-button>
                </div>
            </a-tab-pane>
            <a-tab-pane tab="满减优惠" key="3">
                <a-form-item label="券名" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写券名"
                            v-decorator="['name', {rules: [{required: true, message: '请填写券名'}]}]"
                    />
                </a-form-item>
                <a-form-item label="优惠简介" v-bind="formItemLayout">
                    <a-input
                            type="textarea"
                            :rows="4"
                            placeholder="请填写优惠简介"
                            v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介'}] }]"
                    />
                </a-form-item>
                <a-form-item label="达标金额" v-bind="formItemLayout" >

                    <a-input
                            placeholder="请填写达标金额"
                            v-decorator="['targetMoney', { rules: [{ required: true, message: '请填写达标金额' }] }]"
                    />

                </a-form-item>

                <a-form-item label="优惠金额" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠金额"
                            v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] } ]"
                    />
                </a-form-item>
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="setType(3)"><a-icon type="select"/>
                        确定</a-button>
                </div>
            </a-tab-pane>
            <a-tab-pane tab="限时优惠" key="4">
                <a-form-item label="券名" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写券名"
                            v-decorator="['name', {rules: [{required: true, message: '请填写券名'}]}]"
                    />
                </a-form-item>
                <a-form-item label="优惠简介" v-bind="formItemLayout">
                    <a-input
                            type="textarea"
                            :rows="4"
                            placeholder="请填写优惠简介"
                            v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介'}] }]"
                    />
                </a-form-item>
                <a-form-item label="开始日期" v-bind="formItemLayout">
                <a-date-picker
                        class="time"
                        v-decorator="['startValue', {rules: [{required: true, message: '请选择开始日期'}]}]"
                        :disabled-date="disabledStartDate"
                        show-time
                        format="YYYY-MM-DD 00:00:00"
                        placeholder="开始日期"
                        @openChange="handleStartOpenChange"
                />
                </a-form-item>
                <a-form-item label="结束日期" v-bind="formItemLayout">
                <a-date-picker
                        class="time"
                        v-decorator="['endValue', {rules: [{required: true, message: '请选择结束日期'}]}]"
                        :disabled-date="disabledEndDate"
                        show-time
                        format="YYYY-MM-DD 00:00:00"
                        placeholder="结束日期"
                        :open="endOpen"
                        @openChange="handleEndOpenChange"
                />
                </a-form-item>

                <a-form-item label="优惠金额" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠金额"
                            v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] } ]"
                    />
                </a-form-item>
                <a-form-item label="优惠折扣" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠折扣"
                            v-decorator="['discount', { rules: [{ required: true, message: '请填写优惠折扣' }] } ]"
                    />
                </a-form-item>
                <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button type="primary" @click="setType(4)"><a-icon type="select"/>
                    确定</a-button>
                </div>
            </a-tab-pane>
        </a-tabs>
        </a-form>

    </a-modal>

</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment')
    export default {
        name: 'addCouponModal',
        components: {},

        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
                form: this.$form.createForm(this, { name: 'coordinated' }),
                address:'',
                startValue: null,
                endValue: null,
                endOpen: false,
            }
        },
        computed: {
            ...mapGetters([
                'activeHotelId',
                'addCouponVisible',
                'type',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal' });
        },
        mounted() {

        },

        methods: {
            ...mapMutations([
                'set_addCouponVisible',
                'set_type'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addHotelCoupon'
            ]),
            cancel() {
                this.set_addCouponVisible(false)
            },
            onChange(value) {
                console.log('changed', value);
            },

            setType(key){
              this.set_type(key);
              console.log(this.type)

            },
            disabledStartDate(startValue) {
                const endValue = this.endValue;
                if (!startValue || !endValue) {
                    return false;
                }
                return startValue.valueOf() > endValue.valueOf();
            },
            disabledEndDate(endValue) {
                const startValue = this.startValue;
                if (!endValue || !startValue) {
                    return false;
                }
                return startValue.valueOf() >= endValue.valueOf();
            },
            handleStartOpenChange(open) {
                if (!open) {
                    this.endOpen = true;
                }
            },
            handleEndOpenChange(open) {
                this.endOpen = open;
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            description: this.form.getFieldValue('description'),
                            status: 1,
                            name: this.form.getFieldValue('name'),
                            type: this.type,
                            targetMoney: Number(this.form.getFieldValue('targetMoney')),
                            discountMoney: Number(this.form.getFieldValue('discountMoney')),
                            discount:Number(this.form.getFieldValue('discount')),
                            hotelId: Number(this.activeHotelId),
                            numOfRoom:Number(this.form.getFieldValue('NumOfRoom')),
                            startTime:moment(this.form.getFieldValue('startValue')).format("YYYY-MM-DD 00:00:00"),
                            endTime:moment(this.form.getFieldValue('endValue')).format("YYYY-MM-DD 00:00:00")
                        }
                        this.addHotelCoupon(data)
                    }
                });
            },
        }
    }
</script>
<style scoped>
.time{
    margin-left: 40px;
}
</style>