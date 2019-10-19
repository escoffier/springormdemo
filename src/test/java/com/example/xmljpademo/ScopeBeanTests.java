package com.example.xmljpademo;

import com.example.xmljpademo.model.scopes.Person;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeBeanTests {
    private static final String NAME = "John Smith";
    private static final String NAME_OTHER = "Anna Jones";

    @Test
    public void testScopePrototype() {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("scopes.xml");
        final Person personPrototypeA = (Person) applicationContext.getBean("personPrototype");
        final Person personPrototypeB = (Person) applicationContext.getBean("personPrototype");

        personPrototypeA.setName(NAME);
        personPrototypeB.setName(NAME_OTHER);

        Assert.assertEquals(NAME, personPrototypeA.getName());
        Assert.assertEquals(NAME_OTHER, personPrototypeB.getName());

        ((AbstractApplicationContext) applicationContext).close();
    }
}
