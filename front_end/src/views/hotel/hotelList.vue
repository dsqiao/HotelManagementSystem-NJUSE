<!--酒店列表-->
<template>
    <div class="hotelList">
        <Selector id="selector"></Selector>
        <OrderedCheck id="orderedCheck"></OrderedCheck>
        <SortRadio id="sortRadio"></SortRadio>
        <a-layout style="margin-top: 20px">
            <a-layout-content style="min-width: 800px">
                <a-spin :spinning="hotelListLoading">
                    <div class="card-wrapper">
                        <HotelCard
                            :hotel="item"
                            v-for="item in hotelList"
                            :key="item.index"
                            @click.native="jumpToDetails(item.id)"
                        >
                        </HotelCard>
                        <div
                            v-for="item in emptyBox"
                            :key="item.name"
                            class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3"
                        ></div>

                        <a-pagination
                            showQuickJumper
                            :total="hotelList.totalElements"
                            :defaultCurrent="1"
                            @change="pageChange"
                        ></a-pagination>
                    </div>
                </a-spin>
            </a-layout-content>
        </a-layout>
    </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import Selector from './components/selector'
import SortRadio from './components/sortRadio'
import OrderedCheck from './components/orderedCheck'
import { mapGetters, mapActions, mapMutations } from 'vuex'

export default {
    name: 'home',
    components: {
        HotelCard,
        Selector,
        SortRadio,
        OrderedCheck,
    },
    data() {
        return {
            emptyBox: [{ name: 'box1' }, { name: 'box2' }, { name: 'box3' }],
        }
    },

    async mounted() {
        this.getHotelList()
    },
    computed: {
        ...mapGetters(['hotelList', 'hotelListLoading']),
    },
    methods: {
        ...mapMutations(['set_hotelListParams', 'set_hotelListLoading']),
        ...mapActions(['getHotelList']),

        pageChange(page, pageSize) {
            const data = {
                pageNo: page - 1,
            }
            this.set_hotelListParams(data)
            this.set_hotelListLoading(true)
            this.getHotelList()
        },
        jumpToDetails(id) {
            this.$router.push({ name: 'hotelDetail', params: { hotelId: id } })
        },
    },
}
</script>
<style scoped lang="less">
.hotelList {
    // text-align: center;
    padding: 50px 0;
    #selector {
        text-align: left;
        display: inline;
    }
    #orderedCheck {
        text-align: center;
        display: inline;
        margin-left: 50px;
    }
    #sortRadio {
        margin-left: 330px;
        display: inline;
        text-align: right;
    }
    .emptyBox {
        height: 0;
        margin: 10px 10px;
    }
    .card-wrapper {
        text-align: center;
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        flex-grow: 3;
        min-height: 800px;
    }
    .card-wrapper .card-item {
        text-align: center;
        margin: 30px;
        position: relative;
        height: 188px;
    }
}
</style>
