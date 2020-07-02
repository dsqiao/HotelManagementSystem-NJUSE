<template>
    <a-modal
        :visible="rechargeModalVisible"
        title="信用充值"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" v-bind="formItemLayout">
            <a-form-item v-bind="formItemLayout" label="用户名">
                <span>{{currentClient.userName}}</span>
            </a-form-item>
            <a-form-item label="邮箱">
                <span>{{currentClient.email}}</span>
            </a-form-item>
            <a-form-item label="充值信用值">
                <a-input-number
                    v-decorator="[
                        'credit',
                        {
                            rules: [{required: true, message: '请填写信用值'}],
                            initialValue: 0,
                        }
                    ]"
                ></a-input-number>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: "rechargeModal",
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
                'currentClient',
                'rechargeModalVisible',
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'recharge'});
        },
        methods: {
            ...mapMutations([
                'set_rechargeModalVisible',
            ]),
            ...mapActions([
                'recharge',
            ]),
            cancel: function () {
                this.set_rechargeModalVisible(false);
            },
            handleSubmit: function (e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, value) => {
                    if (!err) {
                        this.recharge({
                            email: this.currentClient.email,
                            credit: this.form.getFieldValue('credit'),
                        });

                        this.set_rechargeModalVisible(false);
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>