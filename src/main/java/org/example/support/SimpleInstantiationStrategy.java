package org.example.support;

import org.example.exception.BeansException;
import org.example.inter.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        // 获取要实例化的类对象
        Class<?> clazz = beanDefinition.getBeanClass();
        // 如果构造函数不为 null
        if (ctor != null) {
            try {
                // 使用反射根据参数类型获取类的构造函数并实例化对象
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                // 捕获实例化过程中可能出现的异常，并抛出自定义异常 BeansException
                throw new BeansException("bean生成错误", e);
            }
        }
        // 如果构造函数为 null，返回 null
        return null;
    }
}
