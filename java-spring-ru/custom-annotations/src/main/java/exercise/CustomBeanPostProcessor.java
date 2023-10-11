package exercise;

import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> annotatedBeans = new HashMap<>();
    private Map<String, String> logLevels = new HashMap<>();

    private final static Logger LOGGER = LoggerFactory.getLogger(BeanPostProcessor.class);



    @Override
    // Метод вызывается перед инициализацией бина
    // Принимет сам бин и имя бина
    // Должен вернуть бин
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
                //field.setAccessible(true);
                String level = bean.getClass().getAnnotation(Inspect.class).value();
                annotatedBeans.put(beanName, bean.getClass());
                logLevels.put(beanName, level);
                System.out.println("!!!!!!!!postProcessBeforeInitialization  bean =  " + beanName + ", level = " + level);
            }

        return bean;
    }

    @Override
    // Метод вызывается после инициализацией бина
    // Должен вернуть бин
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!annotatedBeans.containsKey(beanName)) {
            return bean;
        }

        Class beanClass = annotatedBeans.get(beanName);
        String level = logLevels.get(beanName);

        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
            beanClass.getInterfaces(),
                (proxy, method, args) -> {
                    String message = "Was called method: " + method.getName() + "()" + " with arguments: " + Arrays.toString(args);
                        // Вернуть значение, которое будет обработано как результат метода value
                    //System.out.println("!!!!!!!!  method.getName()  =  " + method.getName()  + ",  Arrays.toString(args) = " +  Arrays.toString(args));
                    if (level.equals("info")) {
                        LOGGER.info(message);
                    } else {
                        LOGGER.debug(message);
                    }
                        return method.invoke(bean, args);
                }
            );
    }


}
// END
