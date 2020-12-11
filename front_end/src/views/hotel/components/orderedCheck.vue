<!--是否预订-->
<template>
    <a-checkbox @change="onChange">
        预定过
    </a-checkbox>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    computed: {
        ...mapGetters(['userId']),
    },
    methods: {
        ...mapMutations(['set_ordered']),
        ...mapActions(['getHotelByCondition']),
        onChange(e) {
            this.set_ordered(e.target.checked)
            // console.log(this.userId)
            this.getHotelByCondition({
                userId: Number(this.userId),
            })
        },
    },
}
</script>
