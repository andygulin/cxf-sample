# Spring + Apacle CXF

# 初始化数据
```sql
CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8;
	
CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(20) DEFAULT NULL,
`age` int(11) DEFAULT NULL,
`address` varchar(100) DEFAULT NULL,
`createdAt` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES(NULL,'aaa',11,'shanghai',NOW());
INSERT INTO `user` VALUES(NULL,'bbb',12,'beijing',NOW());
INSERT INTO `user` VALUES(NULL,'ccc',13,'xianggang',NOW());
```
	
	
# 访问地址
### MessageService
##### 输出文本信息
[http://localhost:8080/services/message/hello](http://localhost:8080/services/message/hello)
	
### UserService
##### 输出文本信息
[http://localhost:8080/services/user/hello](http://localhost:8080/services/user/hello)
##### 输出List&lt;User&gt;对象的XML对象
[http://localhost:8080/services/user/all.xml](http://localhost:8080/services/user/all.xml)
##### 输出List&lt;User&gt;对象的JSON信息
[http://localhost:8080/services/user/all.json](http://localhost:8080/services/user/all.json)
##### 输出id=1的User对象的XML信息
[http://localhost:8080/services/user/get/1.xml](http://localhost:8080/services/user/get/1.xml)
##### 输出id=1的User对象的JSON信息
[http://localhost:8080/services/user/get/1.json](http://localhost:8080/services/user/get/1.json)
##### POST数据
[http://localhost:8080/services/user/post](http://localhost:8080/services/user/post)
##### 请求参数
```json
{
    "name":"hahaha",
    "age":16,
    "address":"shanghai"
}
```