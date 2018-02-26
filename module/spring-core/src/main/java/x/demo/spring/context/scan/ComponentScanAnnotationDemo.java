package x.demo.spring.context.scan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import x.demo.spring.context.scan.pack1.Component2;
import x.demo.spring.context.scan.pack3.Component3;

@ComponentScan(basePackages = {"x.demo.spring.context.scan.pack1"},
        basePackageClasses = {Component3.class},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Component2.class))
public class ComponentScanAnnotationDemo {

    //打印相关信息
    private static void show(ApplicationContext context) {
        String[] beans = {"component1", "component2", "component3"};

        for (String b : beans) {
            boolean isContains = context.containsBean(b);
            System.out.println(b + " is exist : " + isContains);
            if (isContains) {
                System.out.println(b + " : " + context.getBean(b));
            }
        }
    }

    /**
     * 通过AnnotationConfigApplicationContext 触发@ComponentScan使用
     */
    public static void triggerViaAnnotationConfigApplicationContext() {
        //加载@ComponentScan 的包扫描
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();) {
            context.register(ComponentScanAnnotationDemo.class);
            context.refresh();

            show(context);
        }
    }

    /**
     * xml 方式触发@ComponentScan使用
     */
    public static void triggerViaXml() {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext
                ("classpath:x.demo.spring.context.scan/spring-scan.xml");) {
            show(context);
        }
    }

    public static void main(String[] args) {
        ComponentScanAnnotationDemo.triggerViaXml();
    }
}
