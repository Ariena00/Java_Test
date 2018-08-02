package com.hxy;

/**
 * @program: Java_Test
 * @description: 消费者和生产者问题
 * @author: Hu Xiangying
 * @create: 2018-07-23 15:52
 **/

public class ThreadMain3 {
    public static void main (String [] args) {
        double c = 10.0%3.0;
        String a = "001";
        String b = "002";
        Thread thread1 = new Thread(new TestThread(a, b),"thread1");

        a = "011";
        b = "012";
        Thread thread2 = new Thread(new TestThread(a, b),"thread2");

        thread1.start();
        thread2.start();
        System.out.println("c的值为：" + c);
    }

}


class TestThread implements Runnable{
    private String param_a;
    private String param_b;

    public TestThread(String a, String b) {
        this.param_a = a;
        this.param_b = b;
    }

    @Override
    public void run() {
        System.out.println("当前正在运行的进程:" + Thread.currentThread() +" 参数值a:" + this.param_a + " 参数值b：" + this.param_b);
    }
}