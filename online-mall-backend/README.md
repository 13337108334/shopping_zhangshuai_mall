# OnlineMall-backend

#### 介绍
网上购物商城-后端
java 1.8
拦截器 interceptor-> controller-> service-> mapper 简单结构
异常类、BeanCopy类（包含一键拷贝集合）、多线程类、返回值、枚举类都封装好了 提供整套能力
后续开发极度舒适！
前端 vue+js+nodejs
后端 jdk1.8+springBoot+maven+mybatis+mysql+rabbitMQ+redis+openSearch宽表
设计模式使用：工厂+模版+策略 详见此包 com/shanzhu/em/service/orderpay/impl/AliPayServiceImpl
异步使用
    线程池ThreadPoolUtils  详见此包 com/shanzhu/em/utils
    rabbitMQ 详见此包 com/shanzhu/em/service/rabbitmq
数据库同步宽表openSearch+事务回滚+消息处理使用
    com/shanzhu/em/sync/OrderDBSyncOpenSearchHandler

一、所需环境
1、maven 3.3.3以上 需要手动配置settings.xml文件为阿里云镜像  标签别改错！标签别改错！标签别改错！
2、redis 2.0以上
3、git/github
4、mysql 5.7以上  root  123456 （默认密码root需要改为123456）
5、jdk 1.8以上
6、nodejs 16.13.2以上
7、rabbitMQ 3.13.7  Erlang 26.2.5

    mac配置rabbitmq安装文档： https://blog.csdn.net/marsjin/article/details/135938937
    rabbitmq集成springboot文档： https://blog.csdn.net/lvoelife/article/details/126622148
    修改yml文件 配置EXCHANGE/QUEUE

    环境变量配置
    export RABBIT_HOME=/usr/local/Cellar/rabbitmq/3.13.7(改为你自己的)
    export PATH=$PATH:$RABBIT_HOME/sbin
    
    rabbitmq启动命令 
    sudo rabbitmq-server -detached
    
    查看rabbitmq状态
    sudo rabbitmqctl status
    
    rabbitmq关机命令 
    sudo rabbitmqctl stop

    访问rabbitmq页面
    http://localhost:15672
    
    启动trace日志

        启动
        rabbitmq-plugins enable rabbitmq_tracing
        rabbitmqctl trace_on
        rabbitmqctl trace_on -p test-host (queue的名)

        关闭
        rabbitmq-plugins disable rabbitmq_tracing

    
    默认账号guest 
    默认密码guest

示例：
<img width="1429" alt="image" src="https://github.com/user-attachments/assets/135be990-ad9f-4678-a3b5-c2e09997050e">


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
7、com.shanzhu.em.controller.UserController#login 这是登陆的controller  (这是入参日志 controller, param loginForm )
service -> com.shanzhu.em.service.UserService#login 这是登陆的service
8、前端登陆页面已升级为黑悟空 vue 自己研究

三、推荐安装IDEA plug 市场插件： MybatiesPlus、lombok Builder Helper 、Maven Helper（项目模块右键 maven clean install）、vue.js

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

有任何不懂的问题可以v 13337108334 有偿

其他：
    依赖报错/依赖没有的问题 
        project-setting中检查项目、模块中的jdk版本
        检查pom文件引入依赖的版本号
        mvn clean install
        检查settings.xml文件 配置阿里云镜像是不是标签写错了 空行是不是多了
        将本地仓库里的文件全部删除 再重新mvn clean install
        点击idea右手边的maven的m图标    输入命令 mvn -U idea:idea
        还不行就只能去网上找依赖下载下来 再导入到idea的Project Structure - Library - 点加号 - java 选你下载下来的文件
    
