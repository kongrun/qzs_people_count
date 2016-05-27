package com.qzs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2016/5/14.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
public class Application
{

//    // dataSource这里使用的是Hikari,你也可以使用其他的
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig(getClass().getClassLoader().getResource("db.properties").getPath());
//        return new HikariDataSource(config);
//    }
//
//    // 用于处理编码问题
//    @Bean
//    public Filter characterEncodingFilter() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return characterEncodingFilter;
//    }
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(UserController.class, args);
//    }
@RequestMapping("/")
@ResponseBody
String home() {
    return "Hello this is qzs reporting service !";
}
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}