<!--酒店细节展示-->
<template>
  <div class="container">
    <a-card class="info" title="酒店图片">
      <div class="DetailImage" v-for="item in images" :key="item.index">
        <img :src="baseUrl + hotelEncode[hotelname]+'/'+ item + '.jpg'" />
      </div>
    </a-card>
    <a-card class="info" title="设施信息">
      <a-card title="休闲娱乐设施">
        健身中心（酒店内）<br/>
        SPA 设施 <br/>
        足浴中心 <br/>
      </a-card>
      <a-card title="网络设施" :style="{ marginTop: '16px' }">
        房间内高速上网 <br/>
        公用区免费wifi全覆盖
      </a-card>
      <a-card title="交通设施" :style="{ marginTop: '16px' }">
        距地铁站直线距离400米 <br/>
        含有收费停车场，每小时 RMB 8元
      </a-card>
    </a-card>
    <a-card class="info" title="入住须知">
      <a-card title="餐食政策" :style="{ marginTop: '16px' }">
        酒店提供自助早餐，价格 RMB 60元/人
      </a-card>
      <a-card title="宠物政策" :style="{ marginTop: '16px' }">不可携带宠物</a-card>
      <a-card title="儿童政策" :style="{ marginTop: '16px' }">
        1. 酒店允许携带儿童入住 <br/>
        2. 不接受18岁以下客人单独入住 <br/>
        3. 1.2米以下儿童早餐免费，1.2-1.4米儿童早餐半价人民币30元/位， 1.4米以上儿童全价人民币60元/位
      </a-card>
      <a-card title="酒店提示" :style="{ marginTop: '16px' }">
        1. 温馨提示：根据文旅局规定，旨在倡导绿色出行，自2019年7月1日起，
        本市旅游住宿业将不再主动提供牙刷、梳子、浴擦、剃须刀、指甲锉、鞋擦这些一次性日用品。详询酒店前台，不便之处敬请谅解。<br/>
        2. 周末以及节假日的房价会有不同幅度的调整。
      </a-card>
    </a-card>
  </div>
</template>
<script>
export default {
  name: "DetailDisplay",
  props: {
    hotelname: String
  },
  data() {
    return {
      baseUrl:
        "https://orzorzorzorz.oss-cn-beijing.aliyuncs.com/Hotel/hotelDetail/",
      images: ["1", "2", "3", "4"],
      hotelEncode: {
        布丁酒店: "buding",
        格林豪泰酒店: "gelinhaotai",
        汉庭酒店: "hanting",
        佳驿酒店: "jiayi",
        锦江之星酒店: "jinjiangzhixing",
        南苑e家酒店: "nanyuanejia",
        如家酒店: "rujia",
        速8酒店: "su8",
        维也纳酒店: "weiyena"
      }
    };
  },
  methods: {}
};
</script>
<style scoped lang="less">
/* For demo */
.container {
  .DetailImage {
    // text-align: center;
    display: inline-block;
    text-align: center;
    width: 25%;
    img {
      border-radius: 10%;
    }
  }
  .info {
    margin-top: 15px;
    border-radius: 15px;
  }
}
</style>

