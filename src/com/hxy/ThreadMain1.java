package com.hxy;

/**
 * @program: Java_Test
 * @description: 两种锁关键字的应用
 * @author: Hu Xiangying
 * @create: 2018-07-23 14:13
 **/

public class ThreadMain1 {
    public static void main(String [] args){
        Output output = new Output();
        while (true) {
            new Thread(() -> output.one()).start();
            new Thread(() -> output.two()).start();
        }
    }
}

class Output{
    static int i = 0, j = 0;
    static void one() {
        i ++;
        j ++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void two() {
        System.out.println("i = " + i + " j = " + j);

    }
}
