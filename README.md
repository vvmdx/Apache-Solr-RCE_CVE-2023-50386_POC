# Apache-Solr-RCE_CVE-2023-50386_POC
Apache Solr Backup/Restore APIs RCE Poc (CVE-2023-50386)



> 参考（向dalao们致敬）：
>
> [CVE-2023-50386漏洞作者博客](https://l3yx.github.io/2024/02/10/Apache-Solr-Backup-Restore-APIs-RCE-CVE-2023-50386-%E5%88%86%E6%9E%90%E5%8F%8A%E6%8C%96%E6%8E%98%E6%80%9D%E8%B7%AF)
>
> [Java Security Manager绕过姿势](https://www.mi1k7ea.com/2020/05/03/%E6%B5%85%E6%9E%90Java%E6%B2%99%E7%AE%B1%E9%80%83%E9%80%B8/)
>
> [JDK 17下的反射保护绕过](https://pankas.top/2023/12/05/jdk17-%E5%8F%8D%E5%B0%84%E9%99%90%E5%88%B6%E7%BB%95%E8%BF%87)
>
> [JNI绕Rasp](https://javasec.org/javase/JNI/)

漏洞利用原理和思考：https://mp.weixin.qq.com/s/mO4e8aiuL56yBdOD4jy2qQ

poc是用[Pocsuite3](https://github.com/knownsec/pocsuite3)写的，直接用框架运行即可，若不使用框架，则提取其核心实现出来也可以

conf1.zip和conf2.zip可以直接使用

若需要自己测试和编译，Java的Exp都在src下面，包含了我所有测试用和能够使用的Exp

poc执行效果：

验证

![image-1](./pics/verify.png)

代码执行

![image-2](./pics/attack.png)
