package x.demo.spring.context.conf.define;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
//导入其他的配置
@Import(BaseConfiguration.class)
//从Xml文件导入Bean定义
@ImportResource("classpath:x.demo.spring.context.conf/spring-xml-dependency.xml")
public class RealConfiguration {

    @Resource
    BeanInRealXmlDependency beanInRealXmlDependency;

    @Resource
    BeanInRealNestedDependency beanInRealNestedDependency;

    @Bean
    public BeanInReal beanInReal() {
        return new BeanInReal(beanInRealXmlDependency, beanInRealNestedDependency);
    }

    //静态内部类方式导入定义
    @Configuration
    static class NestedConfiguration {
        @Bean
        public BeanInRealNestedDependency beanInRealNestedDependency() {
            return new BeanInRealNestedDependency();
        }
    }
}

class BeanInReal {

    public BeanInReal(BeanInRealXmlDependency beanInRealXmlDependency, BeanInRealNestedDependency beanInRealNestedDependency) {
        System.out.println("beanInRealXmlDependency : " + beanInRealXmlDependency);
        System.out.println("beanInRealNestedDependency : " + beanInRealNestedDependency);
    }

}

class BeanInRealNestedDependency {

}

class BeanInRealXmlDependency {

}


