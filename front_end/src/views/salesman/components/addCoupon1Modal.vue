<template>
    <div>
        <a-modal
            :visible="addCoupon1Visible"
            title="添加生日优惠策略"
            @cancel="cancel"
            @ok="submit"
        ></a-modal>
    </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'

export default {
    name: 'addCoupon1Modal',
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
            form: this.$form.createForm(this, { name: 'addCoupon1Modal' }),
        }
    },
    computed: {
        ...mapGetters(['addCoupon1Visible']),
    },
    methods: {
        ...mapMutations(['set_addCoupon1Visible']),
        cancel() {
            this.set_addCoupon1Visible(false)
        },
        submit() {},
    },
}
</script>

<style scoped></style>
