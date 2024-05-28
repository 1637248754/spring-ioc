package org.example;

import org.example.dto.UserDto;
import org.example.support.*;
import org.example.service.UserService;

public class Main {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDto", new BeanDefinition(UserDto.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userDto", new BeanReference("userDto")));
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));

        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        // 2.获取 Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println("查询用户信息: " +
                userService.getUserDto().queryUserName(
                        userService.getUid()
                )
        );
    }
}