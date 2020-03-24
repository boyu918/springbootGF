package com.zby.manage.model.reflect;

import lombok.Data;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-23
 * Time: 2:53 PM
 */
@Data
public class ReflectStudent {
    String name;
     private int age;
    ReflectStudent(){
    }
    public ReflectStudent(String name){
        this.name = name;
    }
    private ReflectStudent(int age){
        this.age = age;
    }
    private void sysoutStr(String str){
        System.out.println(str);
    }
}
