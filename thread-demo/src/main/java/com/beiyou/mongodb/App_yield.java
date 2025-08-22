package com.beiyou.mongodb;


/*
* Thread.yield() 方法是Java中的一个静态方法，
* 用于暂停当前正在执行的线程，以便让其他具有相同或更高优先级的线程有机会运行。
* 它是一种线程调度方法，用来提高线程执行的公平性和效率。
当调用Thread.yield()时，当前线程会让出CPU，并重新进入就绪状态，允许其他线程执行。
* 具体来说，线程从运行状态转移到就绪状态，等待再次被调度执行。
* */

 //在上述示例中，有一个生产者线程（ProducerThread）和一个消费者线程（ConsumerThread）。
// 每个线程都会打印出它们正在处理的项目。在每次循环迭代中，线程会调用Thread.yield()方法，
// 以便让其他线程有机会运行。
//
//当我们运行上述代码时，你会发现生产者和消费者线程会交替打印输出(大概率)，
// 而不是一个线程完全运行完后再切换到另一个线程。也就是说，
// Thread.yield()方法帮助确保不会出现一个线程长时间占用CPU的情况，而是让其他线程适当地有机会运行。
//
//需要注意的是，Thread.yield()方法的调用不会保证其他具有相同或更高优先级的线程一定会执行，
// 它只是一个暗示给线程调度器的建议。具体的线程调度行为取决于操作系统和JVM的实现。
//谦虚的让出时间片

public class App_yield {


    public static void main(String[] args)  {

        Thread producter = new ProducerThead();
        Thread consumer = new ConsumerThead();

        producter.start();

        consumer.start();

    }
  private  static  class  ProducerThead extends  Thread {
      @Override
      public void run() {
          for (int i = 0; i < 5; i++) {
              System.out.println(" Producer  producting item : " + i);
              Thread.yield();
          }
      }
  }
    private  static  class  ConsumerThead extends  Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(" Consumer  Consuming item : " + i);
                Thread.yield();
            }
        }
    }
}
