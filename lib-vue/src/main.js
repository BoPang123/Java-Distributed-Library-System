// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

//import './assets/styles/element-variables.scss'
//import '@/assets/styles/index.scss' // global css
//import '@/assets/styles/ruoyi.scss' // ruoyi css

import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'  // choose locale of english 
Vue.config.productionTip = false

// axios.defaults.baseURL = 'http://localhost:8000';
axios.defaults.baseURL = '/api'
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

Vue.prototype.$http = axios
window.router=router

Vue.config.productionTip = false

Vue.prototype.router = router

// Vue.use(ElementUi,{locale})
Vue.use(ElementUI, {locale})    //// choose locale 
//  路由判断登录 根据路由配置文件的参数
router.beforeEach((to, from, next) => {

  if (to.matched.some(record => record.meta.requireAuth)){  // 判断该路由是否需要登录权限
    // console.log('判断是否需要登录', JSON.stringify(to));
    if (localStorage.token) {  // 判断当前的token是否存在 ； 登录存入的token
      // console.log('已登录',localStorage.token)
      next();
    }
    else {
      // console.log('转登录界面:to.fullPath', to.fullPath)
      next({
        path: '/login',
        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  }
  else {
    next();
  }
});

/* eslint-disable no-new */
const app = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

export {
  app
}