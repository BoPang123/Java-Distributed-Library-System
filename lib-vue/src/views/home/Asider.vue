<template>
<el-container style="margin:0px; padding: 0%;">
  <el-header style="margin:0px; padding:0px ;">
    <Header :withBtn="false"/>
  </el-header>
  <!-- el-radio-group v-model="isCollapse" style="margin-bottom: 0px;" v-show="false">
    <el-radio-button :label="false">展开</el-radio-button>
    <el-radio-button :label="true">收起</el-radio-button>
  </el-radio-group-->

  <el-main style="padding:0px 10px 0px 20px;">
    <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
      <el-menu-item  index="1">
          <i class="el-icon-location"></i>
          <span slot="title">Browse/Borrow</span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">Return books</span>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <i class="el-icon-document"></i>
        <span slot="title">Personnel management</span>
      </el-menu-item>
      <el-menu-item index="4" disabled>
        <i class="el-icon-setting"></i>
        <span slot="title">Collection Management</span>
      </el-menu-item>
    </el-menu>
  </el-main>
</el-container>
</template>
<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 215px;
    min-height: 400px;
  }
</style>

<script>
import Header from './Header'
  export default {
    components: {
      Header,
    },
    data() {
      return {
        isCollapse: false
      };
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>