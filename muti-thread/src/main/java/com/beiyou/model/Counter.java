package com.beiyou.model;

import lombok.SneakyThrows;

public class Counter {
    @SneakyThrows
    public void printA() {
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println("A");
        }
    }
    @SneakyThrows
    public void printB() {
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println("B");
        }
    }
    @SneakyThrows
    public void printC() {
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println("C");
        }
    }
}
