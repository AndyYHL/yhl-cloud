# yhl-cloud
使用Eureka 注册服务 和 服务器管理配置       
网关 使用Zuul进行服务器反向代理输出，一般用户建立服务总线。  
Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡。  
Ribbon客户端组件提供一些列完善的配置项，如链接超时、重试等。维护了一个服务器列表，如果服务器实例注销，它能够自行剔除。

微服务架构   
主要功能和组成部分    
Eureka 服务注册中心   
Eureka Client 服务注册客户端  
网管Zuul   
客户端负载均衡Ribbon  
断路器Hystrix  
断路器FeignClient  
Spring Cloud Config分布式配置中心  
服务之间调用Feign  
服务跟踪Sleuth  
日志聚合Zipkin    
数据库开发持续继承工具Liquibase     
Swagger接口可视化，在线生成文档和API始终同步       
权限spring security     
微服务架构的通信方式      
   同步        
	    同步的实现方式有很多种，如Rest、SOAP、RPC等，其中SOAP是面向方法的，Rest 是面向资源的，RPC是面向接口的。       
   异步       
	    曾经比较主流的消息队列技术如JMS，现在比较常用的产品有ActiveMQ、Kafka、RabbitMQ 等，在其中Kafka用的比较多。    
管理工具JIRA           
代码质量管理工具Sonar 
