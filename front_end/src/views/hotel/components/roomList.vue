<template>
  <div class="room-list">
    <div class="filter"></div>
    <div class="list">
      <a-table :columns="columns" :dataSource="rooms">
        <span slot="customRest" style="text-align:center">
          剩余可预订房间数量
          <a-button size="small" style="margin-left:8px" @click="datePick">
              日期
            <a-icon type="calendar" />
          </a-button>
        </span>

        <span slot="price" slot-scope="text">
          <span>￥{{ text }}</span>
        </span>
        <span slot="action" slot-scope=" record">
          <a-button type="primary" @click="order(record)">预定</a-button>
        </span>
      </a-table>
    </div>
    <a-modal :visible="datePickVisible" title="日期选择" :footer="null" @cancel="ok">
        <a-date-picker  @change="onStart" size="small" placeholder="入住时间"
          :defaultValue="startDate" 
        />
        <a-date-picker  @change="onEnd" size="small" placeholder="退房时间" style="margin-left:16px"
          :defaultValue="endDate"
        />
    </a-modal>
    <OrderModal></OrderModal>
  </div>
</template>
<script>
const moment = require("moment");
import { mapGetters, mapActions, mapMutations } from "vuex";
import OrderModal from "./orderModal";
const columns = [
  {
    title: "房型",
    dataIndex: "roomType",
    key: "roomType"
  },
  {
    key: "rest",
    slots: { title: "customRest" },
    scopedSlots: { customRender: "rest" },
    dataIndex:"rest"
  },
  {
    title: "房间价格",
    key: "price",
    dataIndex: "price",
    scopedSlots: { customRender: "price" }
  },
  {
    title: "Action",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];
export default {
  name: "roomList",
  props: {
    rooms: {
      type: Array
    }
  },
  data() {
    return {
      columns,
    };
  },
  components: {
    OrderModal
  },
  async mounted(){
    this.updateRestRooms(Number(this.currentHotelId))
  },
  computed: {
    ...mapGetters(["orderModalVisible",
                  "datePickVisible",
                  "currentHotelInfo",
                  "currentHotelId",
                  "startDate",
                  "endDate"]),
  },
  methods: {
    ...mapMutations([
      "set_orderModalVisible",
      "set_currentOrderRoom",
      "set_datePickVisible",
      'set_startDate',
      'set_endDate'
    ]),
    ...mapActions([
      "updateRestRooms"
    ]),
    order(record) {
      this.set_currentOrderRoom(record);
      this.set_orderModalVisible(true);
    },
    datePick() {
      this.set_datePickVisible(true);
    },
    ok(){
        this.set_datePickVisible(false);
        this.updateRestRooms(Number(this.currentHotelId))
        this.$router.go(0)
    },
    onStart(date,dateString){
      this.set_startDate(dateString)
      this.today=dateString
    },
    onEnd(date,dateString){
      this.set_endDate(dateString);
      this.end=dateString
    }
  }
};
</script>