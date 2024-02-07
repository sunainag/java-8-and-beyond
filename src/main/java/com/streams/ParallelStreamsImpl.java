package com.streams;

import java.util.stream.IntStream;

public class ParallelStreamsImpl {

    public static void main(String[] args) {
        //Divide code into multiple streams executed in parallel on separate cores and final result is combi of individual outcomes

        //Iterate 100 records and compare streams and parallel streams
        //Result:
        //Stream took time: 127.. Also order of execution is in order like 123456....
        //Parallel stream took time: 9 ...order of execution is NOT in order like 62636465666...
        long start = System.currentTimeMillis();
        //IntStream.range(1, 100).forEach(System.out::print);
        long end = System.currentTimeMillis();

        System.out.println("\nStream took time: "+ (end-start));
        System.out.println("==================================");

        start = System.currentTimeMillis();
        //IntStream.range(1, 100).parallel().forEach(System.out::print);
        end = System.currentTimeMillis();

        System.out.println("\nParallel stream took time: "+ (end-start));

        //Check core processing:
//        Thread: main : 1
//        Thread: main : 2
//        Thread: main : 3
//        Thread: main : 4
//        Thread: main : 5
//        Thread: main : 6
//        Thread: main : 7
//        Thread: main : 8
//        Thread: main : 9
//                ==================================
//        Thread: main : 6
//        Thread: main : 5
//        Thread: ForkJoinPool.commonPool-worker-2 : 8
//        Thread: ForkJoinPool.commonPool-worker-1 : 3
//        Thread: main : 7
//        Thread: ForkJoinPool.commonPool-worker-2 : 9
//        Thread: ForkJoinPool.commonPool-worker-1 : 4
//        Thread: ForkJoinPool.commonPool-worker-2 : 1
//        Thread: main : 2
        IntStream.range(1, 10).forEach(x->System.out.println("Thread: "+Thread.currentThread().getName()+" : "+x));
        System.out.println("==================================");

        IntStream.range(1, 10).parallel().forEach(x->System.out.println("Thread: "+Thread.currentThread().getName()+" : "+x));

    }
}
