package com.hxy;

/**
 * @program: Java_Test
 * @description: 两种锁关键字的应用
 * @author: Hu Xiangying
 * @create: 2018-07-23 14:28
 **/

public class ThreadMain2 {
    public static void main(String [] args) {
        Name people = new Name();
        new Thread() {
            @Override
            public void run() {
                people.print("zhangsan");
            }

        }.start();
        new Thread() {
            @Override
            public void run() {
                people.print("lisi");
            }

        }.start();
    }
}

class Name {
    public void print(String name) {
        synchronized (this) {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}