<template>
  <div class="header">
    <span style="font-size:1.25em; color: #ffffff;" v-if="!withBtn">Options</span>
    <span v-else style="font-size:1.5em; color: #ffffff;"> Library management system
      <el-button style="float:right; background-color: ;" @click="loginOut">
        Log out
      </el-button>
    </span>
  </div>
</template>

<script>
export default {
  props: {
    withBtn: {
      type: Boolean,
      default: true,
    }
  },
  components:{
  },
  created() {
    console.log('withBtn', this.withBtn)
  },
  methods: {
    loginOut() {
      localStorage.removeItem('token')
      this.$router.push({
        path: '/login'
      })
    }
  }
}
</script>
<style scoped>
.header {
  background-image: url('../../assets/prairie.png');
    background-color: #18bfbc;
    color: #333;
    text-align: center;
    padding: 0.75em 0em;
    border-style: none;
    height:2.8em;
  }
</style>