package com.zby.manage.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zby
 * @time 2019/5/21 9:47
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
        @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zby.manage"))
                .paths(PathSelectors.any()).build();
        return docket;
    }

//    @Bean
//    public Docket customDocket(){
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name("accessToken").description("accessToken")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .build()
//                .globalOperationParameters(pars)
//                .apiInfo(apiInfo());
//    }

    /**
     * 创建改API的基本信息（这些基本信息会展示在文档页面中）
     * 访问地址： http://项目实际地址/swagger-ui.html
     * @return
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot APIs")
                .description("根据官方文档做")
                .version("1.0")
                .build();
    }
}
