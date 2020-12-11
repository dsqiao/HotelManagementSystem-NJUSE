<!--选择城市，商圈-->
<template>
    <div>
        <a-select
            class="selector"
            style="width: 120px"
            placeholder="请选择城市"
            @change="handleAddrChange"
        >
            <a-select-option v-for="address in addresses" :key="address">
                {{ address }}
            </a-select-option>
        </a-select>
        <a-select
            class="selector"
            style="width: 120px"
            placeholder="请选择商圈"
            @change="handleBizRChange"
        >
            <a-select-option v-for="bizRegion in bizRegions" :key="bizRegion">
                {{ bizRegion }}
            </a-select-option>
        </a-select>
    </div>
</template>

<script>
const addresses = ['全部', '南京', '北京', '上海']
const bizRegions = ['全部', 'XiDan', 'XinJieKou', 'WanDa']
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'Selector',
    props: {
        selector: {},
    },
    data() {
        return {
            addresses,
            address: '',
            bizRegions,
            bizRegion: '',
        }
    },
    computed: {
        ...mapGetters(['hotelList', 'hotelListLoading', 'userId']),
    },

    methods: {
        ...mapMutations(['set_currentBizRegion', 'set_currentAddress']),
        ...mapActions(['getHotelByCondition']),
        handleAddrChange(value) {
            this.set_currentAddress(value)
            this.getHotelByCondition({
                userId: Number(this.userId),
            })
        },
        handleBizRChange(value) {
            this.set_currentBizRegion(value)
            this.getHotelByCondition({
                userId: Number(this.userId),
            })
        },
    },
}
</script>
<style scoped lang="less">
.selector {
    margin-left: 25px;
}
</style>
