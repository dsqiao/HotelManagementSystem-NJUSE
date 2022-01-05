import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue'
import BaiduMap from 'vue-baidu-map'
import 'ant-design-vue/dist/antd.css'
import store from './store'

import '@/permission'
Vue.config.productionTip = false
const options = {
    namespace: 'pro__',
    name: 'ls',
    storage: 'local',
}
Vue.use(Antd)
Vue.use(BaiduMap, {
    ak: 'ec69tBenv1WYKACGfHjguGlpKin6bOfI',
})
Vue.directive('title', {
    inserted: function(el, binding) {
        document.title = el.dataset.title
    },
})
new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount('#app')
