package x.demo.spring.context.conf.define;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
//导入属性文件
@PropertySource("classpath:x.demo.spring.context.conf/env.properties")
public class EnvConfiguration {

    //获取整个属性文件
    @Resource
    Environment env;

    //获取属性文件中指定key配置
    @Value("${x.demo.spring.context.conf.define.env}")
    String envValue;

    @Bean
    public BeanInEnv beanInEnv() {
        System.out.println("env reads : " + env.getProperty("x.demo.spring.context.conf.define.env"));
        System.out.println("env @Value : " + envValue);

        return new BeanInEnv(envValue);
    }

}

class BeanInEnv {

    public BeanInEnv(String env) {

    }
}
