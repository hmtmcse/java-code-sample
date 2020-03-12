package com.hmtmcse.test;

public class ThreadOperation {

    private final Object waitObj = new Object();

    public void localTask() throws InterruptedException {
        for (int x = 0; x < 15; x++){
            System.out.println("Task " + x);
            Thread.sleep(1000);
        }
        System.out.println("Task Completed");
    }

    public void task() throws InterruptedException {
        localTask();
        waitUntilFinish(true);
    }

    private void waitUntilFinish(Boolean isFinish) {
        synchronized (waitObj) {
            try {
                if (isFinish) {
                    waitObj.notify();
                } else {
                    waitObj.wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public void perform() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    task();
                } catch (InterruptedException e) {
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        waitUntilFinish(false);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadOperation threadOperation = new ThreadOperation();
        threadOperation.perform();
        System.out.println("All Task done");
        System.out.println("Started Local");
        threadOperation.localTask();
    }

}
