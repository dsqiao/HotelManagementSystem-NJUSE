<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">

                <div class="avatar-wrapper">
                    <img :src="userInfo.avatarUrl" class="avatar" alt="avatar"/>
                    <span style="margin-top: 25px; font-size: 20px">{{ userInfo.userName }}</span>
                </div>

                <a-form :form="form" style="margin-top: 30px">

                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写用户名"
                                v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写邮箱"
                                v-decorator="['email', {rules: [{required: true, message: '请输入邮箱'}]}]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.email }}</span>
                    </a-form-item>

                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写手机号"
                                v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="userInfo.userType === 'Client'">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="会员等级" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1}">
                        <span>{{ userInfo.memberType }}</span>
                    </a-form-item>
                    <a-form-item label="出生日期" :label-col="{span:3}" :wrapper-col="{ span : 8, offset : 1}"
                                 v-if="userInfo.memberType === '普通会员' ">
                        <span>{{ userInfo.birthday }}</span>
                    </a-form-item>
                    <a-form-item label="企业名称" :label-col="{span:3}" :wrapper-col="{span:8, offset:1}"
                                 v-if="userInfo.memberType === '企业会员'">
                        <span>{{userInfo.companyName}}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                                placeholder="请输入新密码"
                                v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                        />
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 0}" v-else>
                        <a-upload
                                name="avatar"
                                :show-upload-list="false"
                                :before-upload="beforeUpload"
                        >
                            <a-button type="primary"> <a-icon type="upload" />更换头像</a-button>
                        </a-upload>
                        <a-button type="primary" @click="modifyInfo" style="margin-left: 30px">
                            <a-icon type="edit"/>
                            修改信息
                        </a-button>
                        <a-button type="primary" style="margin-left: 30px" @click="getMembership" v-if="userInfo.userType === 'Client'&&userInfo.memberType === '非会员'">
                            <a-icon type="team"/>
                            成为会员
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2" v-if="userInfo.userType === 'Client'">
                <a-table
                        :columns="columns"
                        :dataSource="userOrderList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text === 'BigBed'">大床房</span>
                        <span v-if="text === 'DoubleBed'">双床房</span>
                        <span v-if="text === 'Family'">家庭房</span>
                        <span v-if="text === 'PresidentBed'">总统套房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrder(record)">查看</a-button>
                        <a-divider type="vertical" v-if="record.orderState === '已预订'"></a-divider>
                        <a-popconfirm
                                title="你确定撤销该笔订单吗？"
                                @confirm="confirmCancelOrder(record.id)"
                                @cancel="cancelCancelOrder"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState === '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>

                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="信用记录" key="3" v-if="userInfo.userType === 'Client'">
                <a-table
                        :columns="columns2"
                        :dataSource="creditList"
                        bordered
                ></a-table>
            </a-tab-pane>
        </a-tabs>
        <GetMembershipModal></GetMembershipModal>
        <OrderDetailModal></OrderDetailModal>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import GetMembershipModal from './components/getMembershipModal'
    import OrderDetailModal from './components/orderDetailModal'
    import OSS from 'ali-oss';
    const columns = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            dataIndex: 'hotelName',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: { customRender: 'roomType' }
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: { customRender: 'checkInDate' }
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: { customRender: 'checkOutDate' }
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' },{text:'已退房',value:'已退房'},{text:'异常',value:'异常'}],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: { customRender: 'orderState' }
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },

    ];

    const columns2 = [
        {
            title: '用户编号',
            dataIndex: 'userId',
        },
        {
            title: '操作',
            dataIndex: 'action',
        },
        {
            title: '信用变化',
            dataIndex: 'change',
        },
        {
            title: '当前信用',
            dataIndex: 'current',
        },
        {
            title: '时间',
            dataIndex: 'time',
        },
    ];

    function getBase64(img, callback) {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result));
        reader.readAsDataURL(img);
    }
    export default {
        name: 'info',
        data() {
            return {
                modify: false,
                loading: false,
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, { name: 'coordinated' }),
                columns2
            }
        },
        components: {
            OrderDetailModal,
            GetMembershipModal
        },
        computed: {
            ...mapGetters([
                'userId',
                'userInfo',
                'userOrderList',
                'getMembershipModalVisible',
                'creditList'
            ]),

        },
        mounted() {
            this.getUserInfo();
            this.checkOverTimeOrders();
            this.getCreditList();
        },
        methods: {
            ...mapMutations([
                'set_getMembershipModalVisible',
                'set_currentOrder',
                'set_orderDetailModalVisible',
            ]),
            ...mapActions([
                'getUserInfo',
                'getUserOrders',
                'updateUserInfo',
                'updateAvatarUrl',
                'cancelOrder',
                'updateUserOverTimeOrders',
                'getUserCredit',
                'getDistributedRooms',
            ]),
            handleChange(info) {
                if (info.file.status === "uploading") {
                    this.loading = true;
                    return;
                }
                if (info.file.status === "done"){
                    getBase64(info.file.originFileObj, imageUrl => {
                        this.imageUrl = imageUrl;
                        this.loading = false;
                    })
                }
            },
            beforeUpload(file) {
                // 要求图片为jpg或者png格式，大小不超过2MB
                const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
                if (!isJpgOrPng) {
                    this.$message.error('仅支持jpg和png格式图片');
                    return false;
                }
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$message.error('图片尺寸过大，仅支持2MB下的图片');
                    return false;
                }

                this.file = file;
                const client = new OSS({
                    region: 'oss-cn-beijing',
                    accessKeyId: 'LTAI4Fz3wnMfJHgMo2oz1GqS',
                    accessKeySecret: 'zX0zMibWnMEfSm48pNuUuYRrW6VRry',
                    bucket: 'orzorzorzorz',
                });
                const Name = this.file.name;
                const suffix = Name.substr(Name.indexOf('.'));       // 文件后缀，.png或.jpg
                const filename = 'UserAvatar/' + Date.parse(new Date()) + suffix;      // 组成新文件名,UserAvatar为目录名
                client.multipartUpload(filename, this.file).then(res => {   // 上传
                    console.log('上传成功');
                    console.log(res.res.requestUrls[0]);
                    let url = res.res.requestUrls[0];
                    //url = url.substr(0, url.indexOf('?'));
                    console.log("url:" + url);
                    const data = {
                        avatarUrl: url,
                        userName: this.userInfo.userName,
                        email: this.userInfo.email,
                        phoneNumber: this.userInfo.phoneNumber,
                        password: this.userInfo.password,
                    };
                    this.updateAvatarUrl(data);
                }).catch(err => {
                    console.log('上传失败：' + err)
                });

                // return false 表示手动上传 return true 表示自动上传
                return true;
            },
            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            avatarUrl: this.userInfo.avatarUrl,
                            userName: this.form.getFieldValue('userName'),
                            email: this.form.getFieldValue('email'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            password: this.form.getFieldValue('password')
                        }
                        this.updateUserInfo(data).then(()=>{
                            this.modify = false
                        })
                    }
                });
            },
            modifyInfo() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'userName': this.userInfo.userName,
                        'email': this.userInfo.email,
                        'phoneNumber': this.userInfo.phoneNumber,
                    })
                }, 0)
                this.modify = true
            },
            // 用户点击成为会员
            getMembership(){
                console.log("用户点击成为会员");
                this.set_getMembershipModalVisible(true)
            },

            cancelModify() {
                this.modify = false
            },
            confirmCancelOrder(orderId){
                this.cancelOrder(orderId)
            },
            cancelCancelOrder() {

            },
            checkOverTimeOrders(){
                this.updateUserOverTimeOrders({
                    userId:Number(this.userId),
                    type:"user"
                })
            },
            showOrder(record){
                this.set_currentOrder(record);
                this.set_orderDetailModalVisible(true);
                this.getDistributedRooms(record.id)
            },
            getCreditList(){
                this.getUserCredit(Number(this.userId))
            }
        }
    }
</script>
<style scoped lang="less">
    .info-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    .avatar-wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 350px;
        margin-top: 30px;
    }
    .avatar {
        width: 70px;
        height: 70px;
        border-radius: 50%;
    }
</style>