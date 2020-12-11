<template>
    <div>
        <a-modal
            :visible="addCoupon2Visible"
            title="添加多间优惠策略"
            @cancel="cancel"
            @ok="submit"
        ></a-modal>
    </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'

export default {
    name: 'addCoupon2Modal',
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
            form: this.$form.createForm(this, { name: 'addCoupon2Modal' }),
        }
    },
    computed: {
        ...mapGetters(['addCoupon2Visible']),
    },
    methods: {
        ...mapMutations(['set_addCoupon2Visible']),
        cancel() {
            this.set_addCoupon2Visible(false)
        },
        submit() {},
    },
}
</script>

<style scoped></style>
