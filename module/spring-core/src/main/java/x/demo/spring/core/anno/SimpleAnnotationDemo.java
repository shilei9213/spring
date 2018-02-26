package x.demo.spring.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Desc {
    /**
     * 描述
     *
     * @return 描述信息
     */
    String value() default "";
}

//被注解的bean
@Desc("simple bean")
class Bean {

}

public class SimpleAnnotationDemo {

    public static void main(String[] args) {
        Desc desc = Bean.class.getAnnotation(Desc.class);
        System.out.println("bean desc : " + desc.value());
    }
}


