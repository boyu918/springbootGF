package com.zby.manage.model.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-23
 * Time: 2:54 PM
 */
public class RefelctTest {
    public static void main(String[] args) {
        Class stu = ReflectStudent.class;
        ReflectStudent student = new ReflectStudent();
        stu = student.getClass();
        try {
            stu = Class.forName("com.zby.manage.model.reflect.ReflectStudent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor[] con = stu.getConstructors();
        for (Constructor c:con
             ) {
            System.out.println(c);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        con = stu.getDeclaredConstructors();
        for (Constructor c:con
                ) {
            System.out.println(c);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            Constructor c = stu.getDeclaredConstructor(String.class);
            System.out.println(c);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            Constructor c = stu.getDeclaredConstructor(int.class);
            System.out.println(c);
            c.setAccessible(true);
            Object o = c.newInstance(1);
            System.out.println(o);

            Field field = stu.getDeclaredField("age");  //field 跟构造差不多
            field.setAccessible(true);
            field.setInt(o,2);
            System.out.println(o);
            Method method = stu.getDeclaredMethod("sysoutStr",String.class);//method 跟构造也差不多
            method.setAccessible(true);
            method.invoke(o,"ddd");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
