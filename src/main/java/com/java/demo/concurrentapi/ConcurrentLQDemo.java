package com.java.demo.concurrentapi;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*You can run the program multiple times and observe that two consumer 
 * threads are not retrieving the same element from the queue.*/

public class ConcurrentLQDemo {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // Calling one producer
        executor.execute(new ProdTask(buffer));
        // Calling three consumers
        executor.execute(new ConTask(buffer));
        executor.execute(new ConTask(buffer));
        executor.execute(new ConTask(buffer));
        executor.shutdown();
    }
}

class ProdTask implements Runnable{
    Buffer buffer;
    ProdTask(Buffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        // putting just three elements
        for(int i = 0; i < 3; i++){
            buffer.put(i);
        }
    }
}

/**
 * 
 * 
 *
 */
class ConTask implements Runnable{
    Buffer buffer;
    ConTask(Buffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        try {
            // delay to make sure producer starts first
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        buffer.get();
    }    
}

//Shared class used by threads
class Buffer{
    int i;
    Queue<Integer> clQueue = new ConcurrentLinkedQueue<Integer>();
   
    //Retrieving from the queue 
    public void get(){
        System.out.println("Consumer recd - " + clQueue.poll());
    }
    // putting in the queue
    public void put(int i){
        this.i = i;
        clQueue.add(i);
        System.out.println("Putting - " + i);
    }
   
}