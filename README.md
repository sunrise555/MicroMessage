# MicroMessage
基于Mybatis实现自动回复
## 项目来源
项目基于[慕课网](http://www.imooc.com/)上《通过自动回复机器人学Mybatis》
* [通过自动回复机器人学Mybatis---基础版](http://www.imooc.com/learn/154)
* [通过自动回复机器人学Mybatis---加强版](http://www.imooc.com/learn/260)
## 项目信息
### 开发工具
* MyEclipse+Spring+Mybatis
### 实现功能
* 前端<br>
模拟微信自动回复,用户可通过输入查询名称或查询ID即可获得相应的回复，同一个主题具有多条内容，每次随机返回其中一个
* 后台
	* 后台列表展示（分页）
	* 查询条目
	* 删除条目
	* 新增条目（一对多）
	* 修改条目（未完成）
### 访问路径
* 前端：`http://localhost:8080/MicroMessage/InitTalk.action`
* 后台：`http://localhost:8080/MicroMessage/ListShow.action`
### 结果展示
* 前端模拟微信自动回复功能<br>
![模拟微信自动回复](https://raw.githubusercontent.com/sunrise555/MicroMessage/master/image/%E6%A8%A1%E6%8B%9F%E5%BE%AE%E4%BF%A1%E8%87%AA%E5%8A%A8%E5%9B%9E%E5%A4%8D.png)
* 后台管理<br>
![后台显示](https://raw.githubusercontent.com/sunrise555/MicroMessage/master/image/%E5%90%8E%E5%8F%B0%E6%98%BE%E7%A4%BA%E9%A1%B5%E9%9D%A2.png)<br>
![后台新增](https://raw.githubusercontent.com/sunrise555/MicroMessage/master/image/%E5%90%8E%E5%8F%B0%E6%96%B0%E5%A2%9E%E9%A1%B5%E9%9D%A2.png)
