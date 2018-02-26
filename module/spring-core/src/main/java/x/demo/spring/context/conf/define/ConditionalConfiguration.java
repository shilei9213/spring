package x.demo.spring.context.conf.define;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;


@Configuration
public class ConditionalConfiguration {

    @Bean
    @ConditionalOnDBDriver(driver = "com.mysql.jdbc.Driver")
    public BeanDB beanMySql() {
        return new BeanMySql();
    }


    @Bean
    @ConditionalOnDBDriver(driver = "oracle.jdbc.driver.OracleDriver")
    public BeanDB beanOracle() {
        return new BeanOracle();
    }

}

class BeanDB {

}

class BeanMySql extends BeanDB {

}

class BeanOracle extends BeanDB {

}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnDBDriverCondition.class)
@interface ConditionalOnDBDriver {

    /**
     * 驱动名称
     *
     * @return 驱动名称
     */
    String driver();
}

/**
 * 加载条件
 */
class OnDBDriverCondition implements Condition {

    /**
     * 匹配条件：某个类在class path下某个指定名字的类是否存在,
     * <p>
     * 注：其他需求可以配合maven <optional>true</optional>，使用
     *
     * @param context  条件上线文，包括beanFactory ， env，classloader 等
     * @param metadata 元数据，提供被注解的类，方法信息
     * @return true 注册到容器，false 不注册到容器
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            Map<String, Object> attributes = metadata.getAnnotationAttributes("x.demo.spring.context.conf.define.ConditionalOnDBDriver");
            if (Objects.isNull(attributes)) {
                return false;
            }
            String driver = String.valueOf(attributes.get("driver"));
            System.out.println("[debug] driver : " + driver);
            Class.forName(driver);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
