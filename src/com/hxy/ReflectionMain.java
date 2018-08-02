package com.hxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: Java_Test
 * @description: 学习java反射知识
 * @author: Hu Xiangying
 * @create: 2018-07-30 18:44
 **/

public class ReflectionMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*
        //第一种方式获取Class对象
        Student student = new Student("Linda", 15);
        Class myClass = student.getClass();
        System.out.println(myClass.getName());

        //第二种方式获取Class对象
        Class<Student> myClass1 = Student.class;
        System.out.println(myClass == myClass1);

        //第三种方式获取Class对象
        Class myClass2 = null;
        try {
            myClass2 = Class.forName("com.hxy.Student");
            System.out.println(myClass == myClass2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

        /*
        Class clazz = Class.forName("com.hxy.Student");
        System.out.println("=========获取所有公有构造方法==============");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor cons : conArray) {
            System.out.println(cons);
        }

        System.out.println("=========获取所有的构造方法================");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor cons : conArray) {
            System.out.println(cons);
        }

        System.out.println("=========获取公有的、无参数的构造方法================");
        Constructor con = clazz.getConstructor(null);
        System.out.println("con = " + con);

        System.out.println("=========获取私有构造方法================= ");
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        System.out.println("constructor = " + constructor);
        */

        Class clazz = Class.forName("com.hxy.Student");
        System.out.println("=========获取所有的公有字段================");
        Field[] fieldArray =  clazz.getFields();
        for (Field field : fieldArray) {
            System.out.println(field);
        }

        System.out.println("=========获取所有的字段================");
        fieldArray =  clazz.getDeclaredFields();
        for (Field field : fieldArray) {
            System.out.println(field);
        }

        System.out.println("=========获取公有字段并调用================");
        Field field = clazz.getField("name");
        System.out.println(field);
        Object obj = clazz.getConstructor().newInstance(); //这个方法不是很懂
        field.set(obj, "Chanderler");
        Student student = (Student)obj;
        System.out.println(student);

        System.out.println("=========获取私有字段并调用================");
        field = clazz.getDeclaredField("number");
        System.out.println(field);
        field.setAccessible(true);//暴力反射，解除私有限定
        field.set(obj, "18888889999");
        System.out.println("验证电话：" + student);
    }
}

class Student {
    public String name;
    public int age;
    private String number;

    public Student() {
        System.out.println("调用了共有、无参的构造方法。");
    }

    public Student(String name) {
        System.out.println("姓名：" + name);
    }

    public Student(String name, int age) {
        System.out.println("姓名：" + name + "年龄" + age);
    }

    protected Student(boolean n){
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age){
        System.out.println("私有的构造方法 年龄："+ age);
    }

    @Override
    public String toString() {
        return "Student[name: " + name + ", age: " + age +  ", number: " + number + "]";
    }
}