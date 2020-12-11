<!--修改用户信息页面-->
<template>
    <a-modal
        :visible="modifyInfoModalVisible"
        title="修改用户信息"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="新用户名">
                <a-input
                    v-decorator="[
                        'userName',
                        {
                            rules: [
                                { required: true, message: '请输入用户名' },
                            ],
                            initialValue: modifyingUserInfo.userName,
                        },
                    ]"
                >
                </a-input>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="新用户邮箱">
                <a-input
                    v-decorator="[
                        'email',
                        {
                            rules: [
                                { required: true, message: '请输入用户邮箱' },
                            ],
                            initialValue: modifyingUserInfo.email,
                        },
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="新手机号">
                <a-input
                    v-decorator="[
                        'phoneNumber',
                        {
                            rules: [
                                { required: true, message: '请输入手机号码' },
                            ],
                            initialValue: modifyingUserInfo.phoneNumber,
                        },
                    ]"
                >
                </a-input>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="新密码">
                <a-input
                    v-decorator="[
                        'password',
                        {
                            rules: [{ required: true, message: '请输入密码' }],
                            initialValue: modifyingUserInfo.password,
                        },
                    ]"
                />
            </a-form-item>
        </a-Form>
    </a-modal>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
    name: 'modifyInfoModal',
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
    mounted() {},
    computed: {
        ...mapGetters(['modifyInfoModalVisible', 'modifyingUserInfo']),
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'modifyInfoModal' })
    },
    methods: {
        ...mapMutations(['set_modifyInfoModalVisible']),
        ...mapActions(['modifyInfo']),
        cancel() {
            this.set_modifyInfoModalVisible(false)
        },
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        email: this.form.getFieldValue('email'),
                        password: this.form.getFieldValue('password'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                    }
                    this.modifyInfo(data)
                    this.set_modifyInfoModalVisible(false)
                }
            })
        },
    },
}
</script>

<style scoped></style>
