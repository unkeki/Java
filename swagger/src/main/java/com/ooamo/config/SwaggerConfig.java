package com.ooamo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //打开Swagger配置
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    // http://localhost:8080/swagger-ui.html
    /**
     * 1、可以通过Swagger给一些比较难理解的属性或者接口，增加注释信息
     * 2、接口文档实时更新
     * 3、可以在线测试
     * 注意点：在正式发布的时候需要关闭他
     * 配置Swagger
     * 1、Config
     * 2、配置Docket Bean类
     * 定义swagger在生产环境中打开，在发布环境中关闭
     * 获取项目的环境，判断（environment）
     * @return
     */
    @Bean
    public Docket docket(Environment environment){

//      设置要显示Swagger的环境
        Profiles profiles = Profiles.of("dev","test");

//        通过environment.acceptsProfiles判断是否处在自己设置的环境中
        boolean b = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("ooamo") //配置api文档分组
                .enable(b) //是否启动swagger，默认是true
//                select和build是配套的
//                RequestHandlerSelectors:配置要扫描接口的方式
//                basePackage:指定扫描的包
//                any:扫描任何包
//                none: 不扫描
//                withClassAnnotation:扫描类上的注解
//                withMethodAnnotation:扫描方法上的注解
                .select()
//                path:过滤什么路径
//                ant:指定路径
//                .....
//                .paths(PathSelectors.ant("ooamo/**"))
                .apis(RequestHandlerSelectors.basePackage("com.ooamo.controller"))
                .build();
    }

//    配置swagger-ui.html网站上的信息
    public ApiInfo apiInfo(){

//        用户信息
        Contact contact = new Contact("ooamo", "", "279862291@qq.com");

        return new ApiInfo(
                "Ooamo Api Documentation",
                "没必要的事不做 必要的事尽快解决",
                "1.0",
                "自己的网站",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );

    }
}
