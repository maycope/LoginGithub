# LoginGithub
github第三方验证登录

1. 若是之前没有下载运行过**miaosha**系列，先去[秒杀模块](https://github.com/maycope/Seckill)，对数据库进行构建处理。
2. 完成之后，对下载下来的源文件进入到db下在之间构建miaosha数据库下运行**githubuser.sql**，完成对于**githubuser**数据表的创建。
3. 完成之后启动mysql数据库和redis服务端（注意本项目未给redis配置密码，有密码自行加上redis密码，防止连接失败）。
4. 项目更改了端口，完成上述操作之后，访问`http://localhost:8887/login/to_login`,进入到登录页面，可以使用账号和密码登录，对于这部分参见秒杀模块的设计和博客。
5. 对于第三方登录，即参看CSDN博客，完成`id`和`secret`的创建和接入。
6. 更多登录模块，后续会在此基础上进行集成处理，欢迎给个star和关注后续的内容。
