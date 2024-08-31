# OnlineMall-backend

#### 介绍
网上购物商城-后端
java 1.8
拦截器 interceptor-> controller-> service-> mapper 简单结构
异常类、BeanCopy类（包含一键拷贝集合）、多线程类、返回值都封装好了
后续开发极度舒适
前端vue+js，后端jdk1.8+springBoot+mybaties+mysql

一、所需环境
1、maven 3.3.3以上 需要手动配置settings.xml文件为阿里云镜像  标签别改错！标签别改错！标签别改错！
2、redis 2.0以上
3、git
4、mysql 5.7以上  root  123456 （默认密码root需要改为123456）
5、jdk 1.8以上
6、nodejs 16.13.2以上


二、启动前：
yml: jdbc:mysql://localhost:3306/DB_OnlineMall?serverTimezone=GMT%2b8&useSSL=false
1、新建数据库 DB_OnlineMall
2、执行DB_OnlineMall.sql导入执行
3、将数据库sys_usr表加入新字段  passwordplus
4、启动redis、mysql、后端启动类 BackendApplication
5、前端 根目录 Terminal打开
输入 npm install
如果依赖下载不下来 就去百度搜一下换最新的淘宝镜像
最后执行npm run dev
6、前后端启动之后 看超管的数据库密码，数据库里默认存的是前端md5加密过的密码，需要手动去数据库改密码，可以debug或日志把输入加密后的密码打印出来
7、com.shanzhu.em.controller.UserController#login 这是登陆的controller  (这是入参日志 controller, param loginForm:{} )
service -> com.shanzhu.em.service.UserService#login 这是登陆的service
8、前端登陆页面已升级为黑悟空 vue 自己研究

三、推荐安装IDEA plug 市场插件： MybatiesPlus\lombok Builder Helper \Maven Helper（项目模块右键 maven clean install）\ vue.js

<img width="874" alt="image" src="https://github.com/user-attachments/assets/ebbcd0f0-81d0-40ab-b259-02dbb43852be">

<img width="731" alt="image" src="https://github.com/user-attachments/assets/7ddc555a-4a61-4137-80b1-02086a40cb42">

<img width="1418" alt="image" src="https://github.com/user-attachments/assets/d1717444-7ef0-4e99-92bf-6408d85ab2a0">

<img width="1021" alt="image" src="https://github.com/user-attachments/assets/06402a32-48d8-417d-9507-ac7cebf36022">

<img width="1397" alt="image" src="https://github.com/user-attachments/assets/c6e69534-e77e-4161-92ca-169b550ce2ef">

<img width="1383" alt="image" src="https://github.com/user-attachments/assets/14796af4-c95f-48b3-b205-e463e09ecd64">

<img width="1396" alt="image" src="https://github.com/user-attachments/assets/92774162-cb4e-4041-9ebd-842542ebca0c">



三、git命令
git pull 拉取代码
git add . 加入缓存
git commit -m "提交备注"
git push 提交