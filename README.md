# cms

html:后台管理页面,包括整体插件包<br />
officialwebsitecService：后台管理端<br/>

## 结构
### 前端：
  html <br />
  >-assets ： 各类插件包 <br />
  >-page <br/>
   >>-common ： 公共配置<br/>
   >>-error :错误页面 <br />
   >>-officialWebsite ：cms页面 <br/>
   >>-system : 系统管理页面 <br />
### 后台 ：
  officialwebsitecService/src/main/java/com/biggirlo/ <br/>
  >-base : 基础核心类与基础工具包<br/>
  >-system ： 系统管理包<br/>
  >-gw : 信息发布包<br/>

## 如何启动：
系统使用springboot，故可使用内置tomcat插件。

### 新建数据库
使用 doc/demo.sql 脚本新建mysql数据库

### 修改配置文件：
修改 /html/page/common/common.js文件中的context.config.requestHost（请求地址域名与头部） 与 context.config.hostPort （项目域名）<br/>
修改 /officialwebsitecService/src/main/resources/application.yml spring.datasource的数据库配置，改成实际数据库地址与账户秘密；修改file.targetDir，修改成实际存储文件夹路径，用来存储文件，修改file.hostPost,该配置为上传文件的访问地址 <br/>
### 启动后台：
编译工具启动 : 打开 /officialwebsitecService/src/main/java/com/biggirlo/Application.java 运行main方法 <br/>
jar 启动 ： mvn install ,编译成jar 文件， 再运行 java -jar test.jar 启动jar
  

## 系统截图：
https://blog.csdn.net/u012335601/article/details/82962945
