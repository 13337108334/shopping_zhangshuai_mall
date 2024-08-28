<!--
 * 用户登录页
 *
 * @author: ZhangDaYe
 * @Date: 2023-11-11
-->
<template>
 <div class="login-index" :style="backgroundDiv">
   <div class="videoContainer">
     <video class="fullscreenVideo" id="bgVid" playsinline="" autoplay="" muted="" loop="">
       <source src="../assets/heiwukong.mp4" type="video/mp4">
     </video>
   </div>

  <div class="login-window-index">

      <div class="title">
        <b><img src="../resource/logo.png" style="width: 40px;position: relative; top: 13px;right: 6px">
          <span style="color: #e75c09">欢迎游玩黑神话悟空</span>
        </b>
      </div>

      <div style="margin-top: 30px">

        <el-form label-width="70px">

          <el-form-item label="用户名">
            <el-input v-model.trim="user.username" aria-required="true"></el-input>
          </el-form-item>

          <el-form-item label="密码" style="margin-top: 25px">
            <el-input v-model.trim="user.password" show-password aria-required="true"></el-input>
          </el-form-item>

          <el-form-item label="注意" style="margin-top: 15px">
            <el-card> -------😻😻😻左手登陆 右手自负😻😻😻😻-------</el-card>
          </el-form-item>

          <el-form-item style="margin: 30px 80px">
            <el-button type="success" @click="onSubmit">Go!!</el-button>
            <el-button @click="$router.push('/register')">注册</el-button>
          </el-form-item>

        </el-form>
      </div>
 </div>
</div>
</template>

<script>
import md5 from 'js-md5'
export default {
  name: "Login",
  //初始化数据
  data(){
    return {
      to: '/',//登陆成功跳转的页面
      user: {},
      backgroundDiv: {
        backgroundImage:
            "url(" + require("@/resource/img/heiwukong.jpg") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "100% 100%",
      },
    }
  },
  //页面创建
  created() {
      this.to = this.$route.query.to ? this.$route.query.to : "/"
    },
  //方法
  methods: {
    //登录提交
    onSubmit() {
      if(this.user.username==='' || this.user.password===''){
        this.$message.error("账号或密码不能为空")
        return false;
      }
      this.user.password = md5(this.user.password);
      this.request.post("/login",this.user).then(res=>{
        if(res.code==='200'){
          this.$message.success({message: "登陆成功",showClose: true})
          this.$router.push(this.to)
          localStorage.setItem("user",JSON.stringify(res.data))
        }else{
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.login-index {
  background: #ffffff;
  height: 100%;
  position: relative;
}

.login-window-index {
  padding: 20px;
  width: 450px;
  height: 480px;
  background: rgba(31, 33, 27, 0.26);
  position: absolute;
  left: 45%;
  top: 50%;
  transform: translate(60%, -50%);
}

.title {
  text-align: center;
  margin: 30px auto;
  font-size: 25px;
}
</style>

.videoContainer{
position: fixed;
width: 100%;
height: 100%;
overflow: hidden;
z-index: -100;
}

.videoContainer:before{
content: "";
position: absolute;
width: 100%;
height: 100%;
display: block;
z-index: -1;
top: 0;
left: 0;
background: rgba(25,29,34,.65);
}

