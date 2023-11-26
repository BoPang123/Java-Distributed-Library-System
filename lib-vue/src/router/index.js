import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/Login'
import Home from '@/views/home/Home'
import { addBooks } from '../api/books'
import Books from '@/views/Books'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: {
      requireAuth: true,  // 判断是否需要登录
    },
    children: [{
      path: '/books',
      name: 'Books',
      component: Books,
      meta: {
        requireAuth: true,  // 判断是否需要登录
      },      
    }],
  },
  {
    path: '/',
    redirect: {
      path:'/Home'
    }
  }
]


Vue.use(Router)

// 防止连续点击多次路由报错
let routerPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}



export default new Router({
  routes
})
