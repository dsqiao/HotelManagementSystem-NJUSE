<!--酒店细节-->
<template>
  <a-layout>
    <a-layout-content>
      <div class="hotelDetailCard">
        <h1>
          {{ currentHotelInfo.title }}
        </h1>
        <div class="hotel-info">
          <a-card style="width: 400px">
            <img
              alt="example"
              :src="baseUrl + currentHotelInfo.name + '.jpg'"
              slot="cover"
              referrerPolicy="no-referrer"
            />
          </a-card>
          <a-card class="info">
            <div class="info-header">
              <div class="items" id="name-item" v-if="currentHotelInfo.name">
                <!-- <span class="label">酒店名称：</span> -->
                <span class="value">{{ currentHotelInfo.name }}</span>
              </div>
              <div class="items" id="address-item">
                <span class="value" v-if="currentHotelInfo.address">
                  <a-icon type="environment" style="margin-right: 1px" />
                  {{ currentHotelInfo.address }},
                </span>
                <span class="value" v-if="currentHotelInfo.bizRegion">
                  {{ currentHotelInfo.bizRegion }}</span
                >
              </div>
            </div>
            <div class="info-detail">
              <div
                class="items"
                id="rate-item"
                v-if="currentHotelInfo.rate"
                style="margin-top: 10px"
              >
                <a-statistic
                  :value="currentHotelInfo.rate"
                  :precision="1"
                  :value-style="{
                    color: 'orange',
                    'font-size': '44px',
                    width: '80px',
                    height: '80px',
                    'border-radius': '50%',
                    'text-align': 'center',
                    border: '2px solid orange',
                    'margin-bottom': '-5px',
                  }"
                ></a-statistic>
                <span
                  style="
                    color: orange;
                    margin-left: 12px;
                    font-size: 16px;
                    font-weight: 200;
                  "
                  >很好</span
                >
              </div>
              <div
                class="items"
                style="
                  margin-top: 12px;
                  padding-top: 5px;
                  border-top: 1px solid lightgray;
                "
              >
                <span class="value"> 2018年开店 </span>
              </div>
              <div class="items">
                <span class="label"> 联系电话:</span>
                <span class="value"> 0514-83547220 </span>
              </div>
              <div id="service-items" class="items">
                <div class="service-item">
                  <a-icon type="wifi" />
                  <span>无线上网</span>
                </div>
                <div class="service-item">
                  <a-icon type="desktop" />
                  <span>有线上网</span>
                </div>
                <div class="service-item">
                  <a-icon type="car" />
                  <span>停车场</span>
                </div>
                <div class="service-item">
                  <a-icon type="coffee" />
                  <span>下午茶</span>
                </div>
              </div>
            </div>
            <!-- <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span>
                            <span class="value">{{
                                currentHotelInfo.description
                            }}</span>
                        </div> -->
          </a-card>
          <!-- <div class="infrastructure">
                        infra
                    </div> -->
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
            <EvaluationList
              :evaluations="currentHotelInfo.evaluations"
            ></EvaluationList>
            <a-button type="primary" @click="evaluate" style="margin-top: 30px"
              >我要评价</a-button
            >
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-layout-content>
  </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import RoomList from "./components/roomList";
import DetailDisplay from "./components/detailDisplay";
import EvaluationList from "./components/evaluationList";
export default {
  name: "hotelDetail",
  components: {
    RoomList,
    DetailDisplay,
    EvaluationList,
  },
  data() {
    return {
      baseUrl:
        "https://orzorzorzorz.oss-cn-beijing.aliyuncs.com/Hotel/hotelLogo/",
      logoData: {
        布丁酒店: "1",
        格林豪泰酒店: "2",
        汉庭酒店: "3",
        佳驿酒店: "4",
        锦江之星酒店: "5",
        南苑e家酒店: "6",
        如家酒店: "7",
        速8酒店: "8",
        维也纳酒店: "9",
      },
      convertStar: {
        Three: 3,
        Four: 4,
        Five: 5,
      },
    };
  },
  computed: {
    ...mapGetters(["currentHotelInfo"]),
  },
  async mounted() {
    this.set_currentHotelId(Number(this.$route.params.hotelId));
    this.getHotelById();
  },
  beforeRouteUpdate(to, from, next) {
    this.set_currentHotelId(Number(to.params.hotelId));
    this.getHotelById();
    next();
  },
  methods: {
    ...mapMutations(["set_currentHotelId", "set_evaluateModalVisible"]),
    ...mapActions(["getHotelById"]),
    evaluate() {
      this.set_evaluateModalVisible(true);
    },
  },
};
</script>
<style scoped lang="less">
.hotelDetailCard {
  padding: 50px 50px;
  img {
    height: 250px;
  }
}
.hotel-info {
  display: flex;
  //   align-items: center;
  align-items: stretch;
  .info {
    border-radius: 5px;
    padding: 0;
    display: flex;
    flex-direction: column;
    margin-left: 20px;
    .info-header {
      //   display: flex;
      margin-top: -10px;
      padding-bottom: 5px;
      border-bottom: 1px solid lightgray;
      #name-item {
        text-align: center;
        .value {
          font-size: 18px;
          font-weight: bold;
        }
      }
    }
    .info-detail {
      margin-top: 5px;
      #service-items {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr;
        font-size: 12px;
      }
      #rate-item {
        display: flex;
        align-items: center;
      }
      .service-item {
        display: grid;
        text-align: center;
        grid-template-rows: 1fr 1fr;
      }
      .items {
        margin-top: 10px;
      }
    }
  }
}
</style>
