#### 基于Spring Boot 1.5.10 和 Spring Cloud 1.4.3 构建
#### 参考资料
- [Microservices by Martin Fowler](https://martinfowler.com/articles/microservices.html) [（译文）](https://mp.weixin.qq.com/s?__biz=MjM5MjEwNTEzOQ==&mid=401500724&idx=1&sn=4e42fa2ffcd5732ae044fe6a387a1cc3#rd)
- [实施微服务，我们需要哪些基础框架？--- 杨波](http://www.infoq.com/cn/articles/basis-frameworkto-implement-micro-service)
- [重识微服务架构 --- 苏槐](http://www.infoq.com/cn/articles/micro-service-architecture-from-zero)
- [Spring Cloud](http://projects.spring.io/spring-cloud/)
### 微服务架构
#### 服务注册、发现、负载均衡和健康检查的三种方案
1. 集中式LB方案。优点实现简单，缺点LB单点故障
![](https://res.infoq.com/articles/basis-frameworkto-implement-micro-service/zh/resources/1125000.png)
2. 进程内LB方案。每个服务自身内置LB
![](https://res.infoq.com/articles/basis-frameworkto-implement-micro-service/zh/resources/1125001.png)
3. 主机独立LB进程方案。LB为主机上独立的进程
![](https://res.infoq.com/articles/basis-frameworkto-implement-micro-service/zh/resources/1125002.png)
#### 服务前端路由(Service Gateway)
服务网关是连接服务集群内部和外部系统的一道门
![](https://res.infoq.com/articles/basis-frameworkto-implement-micro-service/zh/resources/1125003.png)
#### 服务容错
有效的容错模式和最佳实践
1. 电路熔断器模式(Circuit Breaker Patten)

![](https://res.infoq.com/articles/basis-frameworkto-implement-micro-service/zh/resources/1125007.png)
2. 舱壁隔离模式(Bulkhead Isolation Pattern)
服务线程隔离，某个服务的延迟不影响其他服务
![](https://docs.microsoft.com/en-us/azure/architecture/patterns/_images/bulkhead-1.png)
3. 限流(Rate Limiting/Load Shedder)对服务限定并发访问量
4. 回退(fallback)
#### 运行期配置管理
微服务的动态配置
![](https://res.infoq.com/articles/basis-frameworkto-implement-micro-service/zh/resources/1125009.png)
