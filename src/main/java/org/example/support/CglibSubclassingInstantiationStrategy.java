package org.example.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.example.inter.InstantiationStrategy;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        // 创建 Enhancer 对象，这是 Cglib 的核心类，用于生成代理类
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类，即我们希望生成子类的目标类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置回调，这里使用 NoOp，即什么都不做的回调
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                // 重写 hashCode 方法，调用父类的 hashCode 方法
                return super.hashCode();
            }
        });
        // 如果构造函数为 null，调用 enhancer.create() 创建无参构造函数的代理对象
        if (null == ctor) {
            return enhancer.create();
        }
        // 如果构造函数不为 null，调用 enhancer.create() 并传入参数类型和参数值，创建带参构造函数的代理对象
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}

