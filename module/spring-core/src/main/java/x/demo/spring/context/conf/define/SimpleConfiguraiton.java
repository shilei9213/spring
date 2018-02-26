package x.demo.spring.context.conf.define;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfiguraiton {

    @Bean
    public BeanInSimple beanInSimple() {
        return new BeanInSimple();
    }
}

class BeanInSimple {

}
