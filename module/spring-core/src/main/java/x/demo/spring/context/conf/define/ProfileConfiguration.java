package x.demo.spring.context.conf.define;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfiguration {

    @Bean()
    @Profile("dev")
    public BeanInProfile beanInRealOnDev() {
        return new BeanDev();
    }

    @Bean()
    @Profile("online")
    public BeanInProfile beanInRealOnOnline() {
        return new BeanOnline();
    }
}


class BeanInProfile {

}

class BeanDev extends BeanInProfile {

}

class BeanOnline extends BeanInProfile {

}
