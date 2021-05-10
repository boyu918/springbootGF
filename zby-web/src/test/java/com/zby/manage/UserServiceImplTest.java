package com.zby.manage;

import com.zby.manage.controller.TestCoverageController;
import com.zby.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest(classes = {SysApplication.class})
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    @Rollback
    public void testDoServcie() {
        userService.doServcie();
    }
    @Test
    public void test(){
        String test = userService.doTest(5);
        Assert.assertEquals(test,"5");
    }
    @Test
    public void ttt(){
        TestCoverageController testCoverageController = new TestCoverageController();
        testCoverageController.aaa();
    }
}