<template>
    <div>
        <a-modal
                :visible="addCoupon4Visible"
                title="添加限时优惠策略"
                cancelText="取消"
                okText="确定"
                @cancel="cancel"
                @ok="submit"
        >
            <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
                <a-form-item label="优惠卷名称">
                    <a-input
                            placeholder="请填券名"
                            v-decorator="['name', {rules: [{required: true, message: '请填写券名'}]}]"
                    ></a-input>
                </a-form-item>
                <a-form-item label="优惠简介" v-bind="formItemLayout">
                    <a-input
                            type="textarea"
                            :rows="4"
                            placeholder="请填写优惠简介"
                            v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介'}] }]"
                    />
                </a-form-item>
                <a-form-item label="开始日期">
                    <a-date-picker
                            class="time"
                            v-model="startValue"
                            :disabled-date="disabledStartDate"
                            show-time
                            format="YYYY-MM-DD 12:00:00"
                            placeholder="开始日期"
                            @openChange="handleStartOpenChange"
                    />
                </a-form-item>
                <a-form-item label="结束日期">
                    <a-date-picker
                            class="time"
                            v-model="endValue"
                            :disabled-date="disabledEndDate"
                            show-time
                            format="YYYY-MM-DD 12:00:00"
                            placeholder="结束日期"
                            :open="endOpen"
                            @openChange="handleEndOpenChange"
                    />
                </a-form-item>
                <a-form-item label="优惠折扣" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写优惠折扣"
                            v-decorator="['discount', { rules: [{ required: true, message: '请填写优惠折扣' }] } ]"
                    />
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";
    const moment = require('moment');
    export default {
        name: "addCoupon4Modal",
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
                form: this.$form.createForm(this, { name: 'addCoupon4Modal' }),
                startValue: null,
                endValue: null,
                endOpen: false,
            }
        },
        computed: {
            ...mapGetters([
                'addCoupon4Visible',
            ]),
        },
        methods: {
            ...mapActions([
                'addCoupon4',
            ]),
            ...mapMutations([
                'set_addCoupon4Visible',
            ]),
            cancel() {
                this.set_addCoupon4Visible(false);
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
            submit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            description: this.form.getFieldValue('description'),
                            status: 1,
                            name: this.form.getFieldValue('name'),
                            type: 4,
                            targetMoney: 0,
                            discountMoney: 0,
                            discount: Number(this.form.getFieldValue('discount')),
                            hotelId: -1,
                            NumOfRoom: null,
                            startTime: moment(this.startValue).format("YYYY-MM-DD 12:00:00"),
                            endTime: moment(this.endValue).format("YYYY-MM-DD 12:00:00")
                        };
                        this.addCoupon4(data);
                        this.set_addCoupon4Visible(false);
                    }
                });
            }
        },
    }
</script>

<style scoped>

</style>