<template>
    <div>
        <div id="content">
            <div id="field">
              <div id="label">User name:</div>
              <el-input v-model="user.name" placeholder="please input name..."></el-input>
            </div>
            <div id="field">
              <div id="label">Password:</div>
              <el-input v-model="user.password" placeholder="please input password..." @keyup.enter.native="login"></el-input>
            </div>
            <div>
              <input type="button" default @click="login" value="submit" style="border: solid 1px #fefef0; width:12em;margin-left:6em;"/>   
            </div>
          </div>
    </div>
</template>

<script>
import {getkey, encryptByDES, decryptByDES} from '@/assets/utils/jsencrypt'

import axios from 'axios'

export default {
  name: 'Login', 
  data(){
    return {
      user: {
        name: '', 
        password: '' , 
        secretKey: getkey()
      }
    }
  },
  created() {
    if (localStorage.token) {  // 判断当前的token是否存在 ； 登录存入的token
      console.log('已登录',localStorage.token)
      if (this.$router.query && that.$router.query.redirect) {
        let redirect = that.$router.query.redirect
        this.$router.push(redirect)
      }
      this.$router.push({
        name: "Home"
      });    
    }
  },
  methods: {

    testDes(){
      let secretKey = 'Thisisakey'
      let testencryypted = encryptByDES('passwordtoecrypt',secretKey )
      let msg = decryptByDES(testencryypted,secretKey )
      console.log('testencryypted', testencryypted)
      console.log('msg', msg)
    },

    login() {
      // Des加密测试
      this.testDes()
      let that = this
      //this.$http.post('/api'
      let user = {}
      user.secretKey= 'defaultKey'
      user.name = encryptByDES(this.user.name, user.secretKey)
      user.password = encryptByDES(this.user.password, user.secretKey)    
      console.log('解密测试：name', decryptByDES(user.name, user.secretKey))
      console.log('解密测试：passwoed', decryptByDES(user.password, user.secretKey))
      axios({
        'url': '/login',
        'method': 'post',
        'params': user
      }).then(res => {
        console.log('res:', res);
        console.log('res.data:', res.data);
        console.log('res.data.:', res.data);
        if(res.data.code=='200'){ 
          localStorage.setItem('name', that.user.name)
          console.log("name:", that.user.name)
          localStorage.setItem("token", 'mobile'+ that.phone);
          console.log('tocken', localStorage.token)
          router.push({name: "Home"});
        }else{
          this.$message.error("Fail to login")
        }
        //debugger   
      }).catch( e =>{
        console.log('error msg:', e)
      });
    }
  }
}
</script>

<style>
    body{
        background-image: url('../../assets/bkgrnd.jpg');
        background-size:100%;
        background-position-x: center;
        background-repeat: norepeat;
        text-align: center;
        align-content: center;
    }

    #content{
        width: 18em;
        height: 220px;
        margin: 18.5em auto 0em;
        background-color: rgba(247, 217, 186, 0.492);
        border: 3px solid rgba(255, 255, 255, 0);
        padding: 2em 10% 0% 10%;

    }
    #field {
        vertical-align: middle;
        height: 4em;
        display:flex;
        font-weight: bold;
    }
    #label{
        padding-top: 0.55em;
        width: 6em;
        color: rgb(0, 0, 0);
        display:left;
        font-size: 1.4;
    }
    input[type='submit'],input[type='button'], .submit{
        height: 3em;
        color: rgb(255, 255, 255);
        background-color: rgb(101, 160, 219);
        margin: 0.2em 2em 0.2em 0em;
    }
    .el-input{
      display:left;     
      width: 15em;
    }
</style>
