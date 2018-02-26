package x.demo.spring.context.conf.define;

import org.springframework.context.annotation.Bean;

public class BaseConfiguration {

    @Bean
    public BeanInBase beanInBase() {
        return new BeanInBase();
    }
}

class BeanInBase {

}
