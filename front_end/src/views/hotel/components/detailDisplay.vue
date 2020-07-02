<template>
  <div class="container">
    <a-card class="info" title="酒店图片">
      <div class="DetailImage" v-for="item in images" :key="item.index">
        <img :src="baseUrl + hotelEncode[hotelname]+'/'+ item + '.jpg'" />
      </div>
    </a-card>
    <a-card class="info" title="设施信息">
      <a-card title="通用设施">通用设施内容</a-card>
      <a-card title="餐饮设施" :style="{ marginTop: '16px' }">餐饮设施内容</a-card>
      <a-card title="房间设施" :style="{ marginTop: '16px' }">房间设施内容</a-card>
    </a-card>
    <a-card class="info" title="入住须知">
      <a-card title="入离时间"></a-card>
      <a-card title="餐食政策" :style="{ marginTop: '16px' }"></a-card>
      <a-card title="宠物政策" :style="{ marginTop: '16px' }"></a-card>
      <a-card title="儿童政策" :style="{ marginTop: '16px' }"></a-card>
      <a-card title="酒店提示" :style="{ marginTop: '16px' }"></a-card>
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

