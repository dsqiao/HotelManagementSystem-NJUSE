<!--评价酒店-->
<template>
    <a-modal
        :visible="evaluateModalVisible"
        title="评价酒店"
        cancelText="取消"
        okText="提交"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" v-bind="formItemLayout">
            <a-form-item v-bind="formItemLayout" label="评分">
                <a-rate v-decorator="['rate', { initialValue: 0 }]" />
            </a-form-item>
            <a-form-item label="评论" v-bind="formItemLayout">
                <a-textarea
                    :rows="4"
                    placeholder="请填写评论"
                    v-decorator="[
                        'evaluation',
                        { rules: [{ required: true, message: '请填写评论' }] },
                    ]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
    name: 'evaluateModal',
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
        }
    },
    computed: {
        ...mapGetters(['evaluateModalVisible', 'userId', 'currentHotelId']),
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'evaluate' })
    },
    mounted() {},
    methods: {
        ...mapMutations(['set_evaluateModalVisible']),
        ...mapActions(['addEvaluation']),
        cancel() {
            this.set_evaluateModalVisible(false)
        },
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        rate: this.form.getFieldValue('rate'),
                        evaluation: this.form.getFieldValue('evaluation'),
                        userId: Number(this.userId),
                        hotelId: Number(this.currentHotelId),
                    }
                    this.addEvaluation(data)
                    console.log(data)
                }
            })
        },
    },
}
</script>

<style scoped></style>
