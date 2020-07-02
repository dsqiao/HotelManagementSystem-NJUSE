<template>
    <a-modal
            :visible="getMembershipModalVisible"
            title="申请会员"
            cancelText="取消"
            okText="提交"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form">
            <a-tabs>
                <a-tab-pane tab="普通会员" key="1">
                    <a-form-item v-bind="formItemLayout" label="会员类型">
                        <a-select
                                v-decorator="[
                        'memberType',
                        { rules: [{ required: false, message: '请选择会员类型'}]},
                    ]"
                                placeholder="请选择会员类型"
                        >
                            <a-select-option :value="'普通会员'">普通会员</a-select-option>
                            <a-select-option :value="'企业会员'" disabled>企业会员</a-select-option>
                        </a-select>
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="出生日期">
                        <a-date-picker
                                v-decorator="[
                            'birthday',
                            { rules: [{required: false, message: '请填写出生日期'}]}
                        ]"
                                style="width: 100%" />
                    </a-form-item>
                </a-tab-pane>
                <a-tab-pane tab="企业会员" key="2">
                    <a-form-item v-bind="formItemLayout" label="会员类型">
                        <a-select
                                v-decorator="[
                        'memberType',
                        { rules: [{ required: false, message: '请选择会员类型'}]},
                    ]"
                                placeholder="请选择会员类型"
                        >
                            <a-select-option :value="'普通会员'" disabled>普通会员</a-select-option>
                            <a-select-option :value="'企业会员'">企业会员</a-select-option>
                        </a-select>
                    </a-form-item>
                    <a-form-item v-bind="formItemLayout" label="企业名称">
                        <a-input
                                v-decorator="[
                        'companyName',
                        { rules: [{required: false, message: '请填写企业名称'}]}
                    ]"
                        />
                    </a-form-item>
                </a-tab-pane>
            </a-tabs>
        </a-form>

    </a-modal>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: 'getMembershipModal',
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
                'getMembershipModalVisible',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'getMembershipModal' });
        },
        methods: {
            ...mapMutations([
                'set_getMembershipModalVisible',
            ]),
            ...mapActions([
                'beMember',
            ]),
            cancel() {
                this.set_getMembershipModalVisible(false);
            },
            handleSubmit(e){
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, value) => {
                    if (!err) {
                        const data = {
                            memberType: this.form.getFieldValue('memberType'),
                            birthday: this.form.getFieldValue('birthday'),
                            companyName: this.form.getFieldValue('companyName'),
                        };
                        this.beMember(data);
                    }
                });
            },
        }
    }
</script>