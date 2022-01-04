<!--排序-->
<template>
    <div>
        <a-radio-group
            default-value="default"
            button-style="solid"
            style="float: left;"
            @change="handleSortChange"
        >
            <a-radio-button class="button" value="default">
                默认排序
            </a-radio-button>
            <a-radio-button class="button" value="evaluatePeople">
                按热度排序
            </a-radio-button>
            <a-radio-button class="button" value="star">
                按星级排序
            </a-radio-button>
            <a-radio-button class="button" value="score">
                按评分排序
            </a-radio-button>
        </a-radio-group>
        
    </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'sortRadio',
    data() {
        return {}
    },
    computed: {
        ...mapGetters(['userId', 'hotelList']),
    },
    methods: {
        ...mapActions([
            'sortByDefault',
            'sortByEvaluatePeople',
            'sortByScore',
            'sortByStar',
            'getHotelList',
        ]),
        handleSortChange(event) {
            const type = event.target.value
            const data = this.hotelList
            if (type === 'default') {
                // window.location.reload()
                this.sortByDefault({
                    userId: Number(this.userId),
                })
                console.log("shit")
            } else if (type === 'evaluatePeople') {
                this.sortByEvaluatePeople({
                    userId: Number(this.userId),
                })
            } else if (type === 'score') {
                this.sortByScore({
                    userId: Number(this.userId),
                })
            } else if (type === 'star') {
                this.sortByStar({
                    userId: Number(this.userId),
                })
            }
        },
        reload(event){
            window.location.reload()
        },
    },
}
</script>
<style scoped lang="less">
.button {
    margin-left: 30px;
    border-radius: 6px;
}
</style>
