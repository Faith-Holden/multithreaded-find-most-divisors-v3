package solution;

import java.lang.annotation.Target;
import java.util.Scanner;
import java.util.concurrent.*;

public class MultiThreadFindMostDivisorsV3 {
    static LinkedBlockingQueue<MultiThreadFindMostDivisorsV3.SectionMaxes> resultQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args){
        final int UPPER_LIMIT = 100000;
        final int TASK_NUM = 200;
        int numWithMostDivisors = -1;
        int mostDivisors = -1;

        //make thread pool
        Scanner scanner = new Scanner(System.in);
        int numOfThreads=-1;
        while(numOfThreads<1||numOfThreads>25){
            System.out.println("How many threads would you like to add to the pool? Please choose a number between 1 and 25.");
            numOfThreads = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Starting the calculation...");
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        int increment = UPPER_LIMIT/TASK_NUM;
        for(int i = 0; i < TASK_NUM; i++){
            MaxesTask task = new MaxesTask((i*increment)+1, (i+1)*increment);
            Future<SectionMaxes> sectionMaxes = executor.submit(task);
            try{
                int divMax = sectionMaxes.get().divisorMax;
                if(divMax>mostDivisors){
                    mostDivisors = divMax;
                    numWithMostDivisors = sectionMaxes.get().numMax;
                }
            }catch (Exception e){
            }
        }
        executor.shutdown();
        long endTime = System.currentTimeMillis();
        long computationTime = endTime-startTime;

        System.out.println("The number between 1 and " + UPPER_LIMIT + " with the most divisors is " + numWithMostDivisors + ".");
        System.out.println("It has " + mostDivisors + " divisors.");
        System.out.println("Computation time for " + numOfThreads + " threads was " + computationTime + " milliseconds.");
    }
    private static class MaxesTask implements Callable<SectionMaxes>{
        int maxNum;
        int minNum;
        MaxesTask(int minNum, int maxNum){
            this.maxNum = maxNum;
            this.minNum = minNum;
        }
        public SectionMaxes call() {
            int numWithMax = -1;
            int maxDivisors = -1;
            for (int i=minNum; i<=maxNum; i++){
                int divisorCounter = 0;
                for (int j = 1; j<=i; j++){
                    if(i%j==0){
                        divisorCounter++;
                    }
                }
                if(divisorCounter>maxDivisors){
                    maxDivisors = divisorCounter;
                    numWithMax = i;
                }
            }
            return new SectionMaxes(numWithMax, maxDivisors);
        }
    }

    private static class SectionMaxes{
        int numMax;
        int divisorMax;
        SectionMaxes(int numMax, int divisorMax){
            this.numMax=numMax;
            this.divisorMax=divisorMax;
        }
    }
}
