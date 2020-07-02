<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                            alt="example"
                            :src="baseUrl+logoData[currentHotelInfo.name]+'.jpg'"
                            slot="cover"
                            referrerPolicy="no-referrer"
                            />
                    </a-card>
                    <div class="info">
                        <div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称：</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.bizRegion">
                            <span class="label">商圈</span>
                            <span class="value">{{ currentHotelInfo.bizRegion }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span> 
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span> 
                            <a-rate style="font-size: 15px" :value="convertStar[currentHotelInfo.hotelStar]" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span> 
                            <span class="value">{{ currentHotelInfo.description }}</span>
                        </div>
                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店详情" key="2">
                        <DetailDisplay :hotelname="currentHotelInfo.name"></DetailDisplay>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店评价" key="3">
                        <EvaluationList :evaluations="currentHotelInfo.evaluations"></EvaluationList>
                        <a-button type="primary" @click="evaluate" style="margin-top: 30px">我要评价</a-button>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList';
import DetailDisplay from './components/detailDisplay';
import EvaluationList from './components/evaluationList'
export default {
    name: 'hotelDetail',
    components: {
        RoomList,DetailDisplay,EvaluationList
    },
    data() {
        return {
            baseUrl:"https://orzorzorzorz.oss-cn-beijing.aliyuncs.com/Hotel/hotelLogo/",
            logoData:{
                "布丁酒店":"1",
                "格林豪泰酒店":"2",
                "汉庭酒店":"3",
                "佳驿酒店":"4",
                "锦江之星酒店":"5",
                "南苑e家酒店":"6",
                "如家酒店":"7",
                "速8酒店":"8",
                "维也纳酒店":"9"
            },
            convertStar:{
                "Three":3,
                "Four":4,
                "Five":5
            }
        }
    },
    computed: {
        ...mapGetters([
            'currentHotelInfo',
        ])
    },
    async mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        this.getHotelById()
    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId))
        this.getHotelById()
        next()
    },
    methods: {
        ...mapMutations([
            'set_currentHotelId',
            'set_evaluateModalVisible',
        ]),
        ...mapActions([
            'getHotelById'
        ]),
        evaluate(){
            this.set_evaluateModalVisible(true)
        }
    }
}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>
