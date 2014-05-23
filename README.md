#<center>androidNetTools#
##0.简介
net tools for android
提供android前端与后端的交互功能
##1.版权说明
如果在您的使用中发现任何bug，都可以联系他们。  
*作者*：周钊平 & 崔治丞  
*联系电话*：18810505147  
*邮箱*：zhouzhaoping11@gmail.com
##2.内容说明
###分为两个个文件：
1. Variable.java　　　　前端显示的内容在数据库中的字段
2. NetThread.java　　　 提供交互接口

##3.如何使用
将这两个文件放入android的工程下，修改import就可以使用相关接口 
###step： 
1. 调用NetThread构造函数生成json请求
2. 调用BeginDeal()开始发送请求
3. 调用getActivityData()获取数据

##4.TODO
1. 增加图片上传、下载的线程，同时更新界面：以下载地址和界面的容器作为参数，直接用线程更新界面。
2. 传输utf8编码问题：可以使用utf8编码传输，也可以使用ascii编码传输（以\uXXXX形式表示一个汉字）。前者可以节省传输数据量。
3. 大小写问题：Variable.java里的属性一定要使用小写
4. 错误处理：通信过程可能出现多种错误，建议去掉"success":true这样的域，使用"return_code"，然后商量一份一致的错误列表
5. 日期/时间以ISO格式传输，预先转换为utc+8

