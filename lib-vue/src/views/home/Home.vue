<template>
<el-container>
  <el-aside width="260px" style="display:flex;">
    <el-container style="margin:0px; padding: 0%;">
      <el-header style="margin:0px; padding:0px ;">
        <Header :withBtn="false"/>
      </el-header>
      <!-- el-radio-group v-model="isCollapse" style="margin-bottom: 0px;" v-show="false">
        <el-radio-button :label="false">展开</el-radio-button>
        <el-radio-button :label="true">收起</el-radio-button>
      </el-radio-group-->

      <el-main>
        <el-menu default-active="1" class="el-menu-vertical-demo">
          <el-menu-item  index="1" @click="changePage('borrow')">
              <i class="el-icon-location"></i>
              <span slot="title">Browse/Borrow</span>
          </el-menu-item>
          <el-menu-item index="2" @click="changePage('return')">
            <i class="el-icon-menu"></i>
            <span slot="title">Return books</span>
          </el-menu-item>
          <!-- el-menu-item index="3" disabled>
            <i class="el-icon-document"></i>
            <span slot="title">Personnel management</span>
          </el-menu-item>
          <el-menu-item index="4" disabled>
            <i class="el-icon-setting"></i>
            <span slot="title">Collection Management</span>
          </el-menu-item -->
        </el-menu>
      </el-main>
    </el-container>    
  </el-aside>
  <el-main style="maring:0px;padding:0px">
    <el-container>
      <el-header style="margin:0px;padding:0px;"> <Header withBtn></Header> </el-header>
      <el-main>
        <div v-if="currentPage=='borrow'">
          <Books></Books>
        </div>  
        <div v-else>
          <ReturnBooks></ReturnBooks>
        </div>  
      </el-main>
    </el-container>
  </el-main>
</el-container>
</template>

<script>
import Books from '@/views/Books'
import Header from './Header'
import ReturnBooks from '@/views/ReturnBooks'
export default {
  components:{
    Books,
    ReturnBooks,
    Header,
  },

  data() {
    return {
      currentPage: 'borrow',

    } 
  },

  methods: {
    changePage(value){
      this.currentPage = value
      console.log('currentPage',value)
    }
  }
}
</script>

<style scoped>
  @import 'element-ui/lib/theme-chalk/index.css';

  .el-aside {
    background-color: #6d86b5;
    color: rgb(255, 255, 255);
    text-align: center;
    vertical-align:middle;
  }
  
</style>