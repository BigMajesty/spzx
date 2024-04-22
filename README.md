# 声明

本项目就是尚硅谷开源的尚品甄选项目，仅用于本人学习（后端开发）。（侵删）

# 运行引导

## 后台管理系统

### 前端

前端源码：spzx-admin（前端部分不作介绍，详情可自行了解：B站尚硅谷，尚品甄选视频教程。）

==环境请自行搭建，运行之前请注意ip与端口的修改！！！==

后端服务运行在localhost:8502，前端请求路径也已修改为：localhost:8502。

如有需要，请同时修改前后端，否则，直接运行即可。

### 后端

- 后台系统的所有功能都在子模块：spzx-manager

	- 基础环境介绍
		- idea2023
		- JDK17
		- maven进行依赖管理（idea内置）
		- SpringBoot3
	- 服务ip与端口号配置（可自行更改）
	
	| 服务                        | ip                  | 端口号   | 是否必须修改                 |
	| :-------------------------- | ------------------- | -------- | ---------------------------- |
	| spzx-manager（Application） | 本地：lcoalhost     | **8502** | 否                           |
	| mysql                       | 虚拟机linux（本地） | 3306     | 是（修改为自己服务所在的ip） |
	| redis                       | 虚拟机linux（本地） | 6379     | 是（修改为自己服务所在的ip） |
	| minio                       | 本地：localhost     | 9000     | 否                           |
	
	- mysql中的数据：数据库下的db_spzx.sql
	

## 前台用户系统

### 前端

前端源码：spzx-h5（前端部分不作介绍，详情可自行了解：B站尚硅谷，尚品甄选视频教程。）

==环境请自行搭建，运行之前请注意修改请求路径！！！==

下图中的请求路径为Gateway（网关）服务所在，前台所有服务都需要通过网关进行路由转发。

![](assets/1.png)

### 后端

- SpringCloud微服务

	- nacos实现服务发现与注册

	- OpenFeign实现远程调用

	- Gateway实现路由转发与负载均衡

- 服务ip与端口（可自行更改）

	| 服务            | ip                  | 端口     | 是否必须修改                      |
	| --------------- | ------------------- | -------- | --------------------------------- |
	| nacos           | 虚拟机linux（本地） | 9848     | 是（修改为自己nacos服务所在的ip） |
	| service-order   | 本地：localhost     | 8514     | 否                                |
	| service-pay     | 本地：localhost     | 8515     | 否                                |
	| service-product | 本地：localhost     | 8521     | 否                                |
	| service-user    | 本地：localhost     | 8512     | 否                                |
	| service-cart    | 本地：localhost     | 8513     | 否                                |
	| Gateway         | 本地：localhost     | **8500** | 否                                |
	| mysql与redis    | 与后台数据库一致    |          |                                   |

