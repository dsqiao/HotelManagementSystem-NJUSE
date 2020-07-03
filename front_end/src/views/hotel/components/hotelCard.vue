<template>
  <a-card hoverable class="hotelCard ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
    <img
      alt="example"
      :src="baseUrl+hotel.name+'.jpg'"
      slot="cover"
      referrerPolicy="no-referrer"
    />
    <a-tooltip :title="hotel.title" placement="top">
      <a-card-meta :title="hotel.name">
      <template slot="description">
        <a-rate style="font-size: 15px" :value="convertStar[hotel.hotelStar]" disabled allowHalf/> {{hotel.rate}}分
      </template>
    </a-card-meta>
    </a-tooltip>
  </a-card>
</template>
<script>
import {mapGetters,mapMutations,mapActions} from 'vuex'
export default {
  name:'',
  props: {
    hotel: {}
  },
  data() {
    return{
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
          'Three':3,
          'Four':4,
          'Five':5
        }
    }
  },
}
</script>
<style scoped lang="less">
    .hotelCard {
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;
        img {
          height: 250px;
        }
    }
</style>

<style lang="less">
.hotelCard{
  .ant-card-body{
    padding: 12px
  }
}

.ant-card-hoverable:hover {
  box-shadow: 0 2px 8px rgba(0,0,0,0.4)
}

</style>