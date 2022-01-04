<!--搜索酒店-->
<template>
    <div class="searchHotels">
        <a-layout>
            <a-layout-content style="min-   width: 800px">
                <a-form :form="form">
                    <div class="searchFrame">
                        <a-form-item v-bind="formItemLayout">
                            <div class="label">
                                <span>地址</span>
                            </div>
                            
                            <a-input
                                class="input"
                                placeholder="地址"
                                v-decorator="[ 
                                    'address',
                                    {
                                        rules: [
                                            {
                                                required: true  ,
                                                message: '请填写地址',
                                            },
                                        ],
                                    },
                                ]"
                                allowClear
                            />
                            <span class="label">入住日期</span>
                            <a-date-picker
                                v-model="startValue"
                                :disabled-date="disabledStartDate"
                                show-time
                                format="YYYY-MM-DD 12:00:00"
                                placeholder="入住日期"
                                @openChange="handleStartOpenChange"
                            />
                            <span class="label">退房日期</span>
                            <a-date-picker
                                v-model="endValue"
                                :disabled-date="disabledEndDate"
                                show-time
                                format="YYYY-MM-DD 12:00:00"
                                placeholder="退房日期"
                                :open="endOpen"
                                @openChange="handleEndOpenChange"
                            />
                            <div class="button" style="textalign= right">
                                <a-button type="primary" @click="handleSubmits"
                                    ><a-icon type="search" />
                                    搜索
                                </a-button>
                            </div>
                        </a-form-item>
                        <a-form-item v-bind="formItemLayout">
                            <span class="label">酒店名称</span>
                            <a-input
                            class="input"
                                v-decorator="['name']"
                                placeholder="酒店名称"
                                allowClear
                            />
                            <ordered-check></ordered-check> 
                        </a-form-item>
                        
                        <a-form-item v-bind="formItemLayout">
                            <span class="label">商圈</span>
                            <a-input
                            class="input"
                                placeholder="商圈"
                                v-decorator="[
                                    'bizRegion',
                                    {
                                        rules: [
                                            {
                                                required: true,
                                                message: '请填写商圈',
                                            },
                                        ],
                                    },
                                ]"
                                allowClear
                            />
                        </a-form-item>
                        <a-form-item>
                            <span class="label">最低星级</span>
                            <a-input-number
                                class="input"
                                size="large"
                                :min="1"
                                :max="5"
                                @change="onChange"
                                v-decorator="['lowStar']"
                            />
                            <span class="label">最高星级</span>
                            <a-input-number
                                class="input"
                                size="large"
                                :min="1"
                                :max="5"
                                @change="onChange"
                                v-decorator="['highStar']"
                            />
                        </a-form-item>
                        <a-form-item>
                            <span class="label">房间数量</span>
                            <a-input-number
                                class="input"
                                size="large"
                                :min="1"
                                :max="5"
                                @change="onChange"
                                v-decorator="['num']"
                            />
                            <span class="label">房间类型</span>
                            <a-select v-decorator="['type']" class="input">
                                <a-select-option value="1"
                                    >大床房</a-select-option
                                >
                                <a-select-option value="2"
                                    >双人房</a-select-option
                                >
                                <a-select-option value="3"
                                    >家庭房</a-select-option
                                >
                                <a-select-option value="4"
                                    >总统套房</a-select-option
                                >
                            </a-select>
                        </a-form-item>
                        <a-form-item>
                            <span class="label">最低价格</span>
                            <a-input-number
                                class="input"
                                size="large"
                                :min="0"
                                :max="10000"
                                @change="onChange"
                                v-decorator="['lowPrice']"
                            />

                            <span class="label">最高价格</span>
                            <a-input-number
                                class="input"
                                size="large"
                                :min="0"
                                :max="10000"
                                @change="onChange"
                                v-decorator="['highPrice']"
                            />
                        </a-form-item>
                        
                        
                    </div>
                </a-form>
            </a-layout-content>
        </a-layout>
    </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderedCheck from './components/orderedCheck.vue'
const moment = require('moment')
export default {
    name: 'searchHotels',
    components: {OrderedCheck},
    data() {
        return {
            formItemLayout: {
                labelCol: {
                    xs: { span: 1 },
                    sm: { span: 1 },
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 22 },
                },
            },
            formLayout: 'horizontal',
            form: this.$form.createForm(this, { name: 'coordinated' }),
            address: '',
            startValue: null,
            endValue: null,
            endOpen: false,
        }
    },

    computed: {
        ...mapGetters(['searchList']),
    },
    methods: {
        ...mapMutations([
            'set_hotelList',
            'set_hotelListLoading',
            'set_searchList',
        ]),
        ...mapActions(['searchHotels']),
        onChange(value) {
            console.log('changed', value)
        },

        handleSubmits(e) {
            e.preventDefault()
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        // 这里添加接口参数
                        address: this.form.getFieldValue('address'),
                        name:
                            this.form.getFieldValue('name') != null
                                ? this.form.getFieldValue('name')
                                : 'null',
                        bizRegion: this.form.getFieldValue('bizRegion'),
                        lowStar: !isNaN(
                            Number(this.form.getFieldValue('lowStar'))
                        )
                            ? Number(this.form.getFieldValue('lowStar'))
                            : -1,
                        highStar: !isNaN(
                            Number(this.form.getFieldValue('highStar'))
                        )
                            ? Number(this.form.getFieldValue('highStar'))
                            : -1,
                        roomNum: !isNaN(Number(this.form.getFieldValue('num')))
                            ? Number(this.form.getFieldValue('num'))
                            : -1,
                        type: !isNaN(Number(this.form.getFieldValue('type')))
                            ? Number(this.form.getFieldValue('type'))
                            : -1,
                        lowPrice: !isNaN(
                            Number(this.form.getFieldValue('lowPrice'))
                        )
                            ? Number(this.form.getFieldValue('lowPrice'))
                            : -1,
                        highPrice: !isNaN(
                            Number(this.form.getFieldValue('highPrice'))
                        )
                            ? Number(this.form.getFieldValue('highPrice'))
                            : -1,
                        InDate: moment(this.startValue).format('YYYY-MM-DD'),
                        OutDate: moment(this.endValue).format('YYYY-MM-DD'),
                    }
                    this.set_searchList(data)
                    this.searchHotels()
                    this.$router.push({ name: 'hotelList' })
                }
            })
        },

        disabledStartDate(startValue) {
            const endValue = this.endValue
            if (!startValue || !endValue) {
                return false
            }
            return startValue.valueOf() > endValue.valueOf()
        },
        disabledEndDate(endValue) {
            const startValue = this.startValue
            if (!endValue || !startValue) {
                return false
            }
            return startValue.valueOf() >= endValue.valueOf()
        },
        handleStartOpenChange(open) {
            if (!open) {
                this.endOpen = true
            }
        },
        handleEndOpenChange(open) {
            this.endOpen = open
        },
    },
}
</script>
<style scoped lang="less">
.searchHotels {
    text-align: left;
    padding: 50px 0;
}
.searchFrame {
    width: 1300px;
    margin-left: 40px;
}
.ant-input-number {
    margin-right: 30px;
}

.Selection {
    width: 100px;
    margin-right: 20px;
}
.input{
    width: 200px;
    margin-right: 30px;
}
.button{
    float: right;
}
.label{
    display: inline-block;
    margin-right:10px;
    width: 80px;
    text-align: right;

}
</style>
