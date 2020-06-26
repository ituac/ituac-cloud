# ituac-cloud



### 项目简介

> Spring Cloud Hoxton.RELEASE版本搭载Spring Cloud Alibaba 2.1.1.RELEASE进行构建。



### 项目结构

```html
ituac-cloud
├── docs                   			   -- 文档及脚本
├── ituac-cloud-dependencies                       -- 项目依赖管理
├── ituac-cloud-discovery           	           -- 服务注册发现[port=8001]
├── ituac-cloud-config           	           -- 配置中心[port=8002]
├── ituac-cloud-common                             -- 公共模块
    ├── ituac-common-core        	           -- 提供微服务相关依赖包、工具类、全局异常解析等(完善中...)
    ├── ituac-common-logs        	  	   -- 日志基础服务(完善中...)
    ├── ituac-common-swagger                       -- 接口服务(完善中...)
    ├── ituac-common-job           	           -- xxx(完善中...)
    ├── ituac-common-security                      -- xxx(完善中...)
├── ituac-cloud-gateway                            -- 网关服务[port=8101]
├── ituac-cloud-auth                               -- 授权服务系统[3000]
├── ituac-cloud-upms                               -- 用户权限管理
    ├── ituac-upms-entity       	 	   -- 数据实体类
    ├── ituac-upms-service         		   -- xxx
    ├── ituac-upms-dao   			   -- 数据持久化模块
    ├── ituac-upms-admin-server    	           -- xxx
    ├── ituac-upms-portal-server   		   -- xxx
├── ituac-cloud-cms				   -- 内容服务管理
    ├── ituac-cms-entity       	 		   -- 数据实体类
    ├── ituac-cms-service         		   -- xxx
    ├── ituac-cms-dao   			   -- 数据持久化模块
    ├── ituac-cms-admin-server    	           -- xxx
    ├── ituac-cms-portal-server   		   -- xxx
├── ituac-cloud-ops                                -- 运维系统
    ├── ituac-ops-codegen   			   -- 图形代码生成器[port = 9001]
    ├── ituac-ops-zipkin   			   -- zipkin监控服务[port = 9002]
    ├── ituac-ops-monitor   			   -- SpringBootAdmin监控[port = 9001]

```




### 部署方式

- IDEA 等工具启动
- jar部署
- docker部署
- k8s部署



### 项目方式
- 暂时未更新