package com.eddicorp.examples.week1;

public class Example6Thread {

    static class CriticalSection {
        private int a = 0;

        public void add1() {
            final int readFromA = a;
            final int newA = readFromA + 1;
            a = newA;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CriticalSection cs = new CriticalSection();
        final Task task1 = new Task(cs);
        final Task task2 = new Task(cs);
        final Thread t1 = new Thread(task1);
        final Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        Thread.sleep(3000);
        System.out.println(cs.a);
    }

    static class Task implements Runnable {
        private CriticalSection cs;

        public Task(CriticalSection cs) {
            this.cs = cs;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                cs.add1();
            }
        }
    }
}
