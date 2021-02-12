package com.wjy.test;

public class Test {
    public void m1() {
        System.out.println("m1");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> new Test().m1());
        thread.start();
    }
}
