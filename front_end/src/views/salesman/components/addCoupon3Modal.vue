<template>
    <div>
        <a-modal
            :visible="addCoupon3Visible"
            title="添加满减优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="submit"
        >
            <a-form
                :form="form"
                style="margin-top: 30px"
                v-bind="formItemLayout"
            >
                <a-form-item label="优惠卷名称">
                    <a-input
                        placeholder="请填写券名"
                        v-decorator="[
                            'name',
                            {
                                rules: [
                                    { required: true, message: '请填写券名' },
                                ],
                            },
                        ]"
                    ></a-input>
                </a-form-item>
                <a-form-item label="优惠简介" v-bind="formItemLayout">
                    <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠简介"
                        v-decorator="[
                            'description',
                            {
                                rules: [
                                    {
                                        required: true,
                                        message: '请填写优惠简介',
                                    },
                                ],
                            },
                        ]"
                    />
                </a-form-item>
                <a-form-item label="达标金额" v-bind="formItemLayout">
                    <a-input
                        placeholder="请填写达标金额"
                        v-decorator="[
                            'targetMoney',
                            {
                                rules: [
                                    {
                                        required: true,
                                        message: '请填写达标金额',
                                    },
                                ],
                            },
                        ]"
                    />
                </a-form-item>
                <a-form-item label="优惠金额" v-bind="formItemLayout">
                    <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="[
                            'discountMoney',
                            {
                                rules: [
                                    {
                                        required: true,
                                        message: '请填写优惠金额',
                                    },
                                ],
                            },
                        ]"
                    />
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from 'vuex'

export default {
    name: 'addCoupon3Modal',
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
            form: this.$form.createForm(this, { name: 'addCoupon3Modal' }),
        }
    },
    computed: {
        ...mapGetters(['addCoupon3Visible']),
    },
    methods: {
        ...mapActions(['addCoupon3']),
        ...mapMutations(['set_addCoupon3Visible']),
        cancel() {
            this.set_addCoupon3Visible(false)
        },
        submit(e) {
            e.preventDefault()
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        description: this.form.getFieldValue('description'),
                        status: 1,
                        name: this.form.getFieldValue('name'),
                        type: 3,
                        targetMoney: Number(
                            this.form.getFieldValue('targetMoney')
                        ),
                        discountMoney: Number(
                            this.form.getFieldValue('discountMoney')
                        ),
                        discount: null,
                        hotelId: -1,
                        NumOfRoom: null,
                        startTime: null,
                        endTime: null,
                    }
                    this.addCoupon3(data)
                    this.set_addCoupon3Visible(false)
                }
            })
        },
    },
}
</script>

<style scoped></style>
