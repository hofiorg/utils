package org.hofi.examples;

import java.util.concurrent.*;

import static java.lang.System.out;

public class FutureExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    new FutureExample();
  }

  private FutureExample() throws ExecutionException, InterruptedException {
    long millis = System.currentTimeMillis();
    Future<String> myfuture = futureFunc();
    Thread.sleep(500);
    out.println("hello world 1");
    String value = myfuture.get();
    out.println(value);
    pool.shutdown();
    out.println(System.currentTimeMillis() - millis + " ms");
  }

  private final ExecutorService pool = Executors.newFixedThreadPool(10);

  private Future<String> futureFunc() {
    return pool.submit(() -> {
      out.println("hello world 2");
      Thread.sleep(500);
      return "hello world 3";
    });
  }
}