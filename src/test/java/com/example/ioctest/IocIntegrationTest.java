package com.example.ioctest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestConfig.class)
public class IocIntegrationTest {
    @Autowired
    BeanFactory beanFactory;

    @Component
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Primary
    static class A {
        public A(Integer a) {
        }

        public A(String a) {
        }
    }

    @Component
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    static class A1 extends A {
        public A1() {
            super(1);
        }
    }

    @Test  // fails
    public void testInstantiatingAWithInteger() {
        beanFactory.getBean(A.class, 1);
    }

    @Test // fails
    public void testInstantiatingAWithString() {
        beanFactory.getBean(A.class, "A");
    }

    @Test // works
    public void testInstantiatingA1() {
        beanFactory.getBean(A1.class);
    }
}
