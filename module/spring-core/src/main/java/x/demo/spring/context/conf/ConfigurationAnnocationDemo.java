package x.demo.spring.context.conf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import x.demo.spring.context.conf.define.ConditionalConfiguration;
import x.demo.spring.context.conf.define.EnvConfiguration;
import x.demo.spring.context.conf.define.ProfileConfiguration;
import x.demo.spring.context.conf.define.RealConfiguration;
import x.demo.spring.context.conf.define.SimpleConfiguraiton;

@ComponentScan("x.demo.spring.context.conf.define")
public class ConfigurationAnnocationDemo {

    //打印相关信息
    public static void show(ApplicationContext context) {
        String[] beans = {"beanInSimple", "beanInBase", "beanInEnv", "beanInReal", "beanInProfile", "beanInRealOnDev",
                "beanInRealOnOnline", "beanMySql", "beanOracle"};

        for (String b : beans) {
            boolean isContains = context.containsBean(b);
            System.out.println(b + " is exist : " + isContains);
            if (isContains) {
                System.out.println(b + " : " + context.getBean(b));
            }
        }
    }

    /**
     * 通过AnnotationConfigApplicationContext 或 web 版 AnnotationConfigWebApplicationContext 直接注册配置类，驱动Bean导入容器
     * <p>
     * 特别注意：这种方式即使没好友@Configuration注解也可以使用
     */
    public static void triggerViaAnnotationConfigApplicationContext() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();) {
            context.register(SimpleConfiguraiton.class, EnvConfiguration.class, RealConfiguration.class, ProfileConfiguration.class,
                    ConditionalConfiguration.class);
            context.refresh();

            show(context);
        }
    }

    /**
     * xml 方式触发@ComponentScan使用
     */
    public static void triggerViaXml() {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext
                ("classpath:x.demo.spring.context.conf/spring-conf.xml");) {
            show(context);
        }
    }

    /**
     * 通过ComponentScan 引导Bean导入容器
     */
    public static void triggerViaComponentScan() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();) {
            context.register(ConfigurationAnnocationDemo.class);
            context.refresh();

            show(context);
        }
    }

    public static void main(String[] args) {
        ConfigurationAnnocationDemo.triggerViaAnnotationConfigApplicationContext();
    }
}
